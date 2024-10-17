
    document.addEventListener('DOMContentLoaded', function() {
    const cuotasDiv = document.getElementById('cuotasDiv');
    const radioCredito = document.getElementById('credito');
    const radioDebito = document.getElementById('debito');
    const radioSaldo = document.getElementById('saldo');

    // Función para mostrar o esconder el div de cuotas
    function toggleCuotasDiv() {
    if (radioCredito.checked) {
    cuotasDiv.style.display = 'block'; // Muestra el div si seleccionas "Tarjeta de Crédito"
} else {
    cuotasDiv.style.display = 'none'; // Oculta el div si seleccionas otro tipo de pago
}
}

    // Escucha los cambios en los radio buttons
    radioCredito.addEventListener('change', toggleCuotasDiv);
    radioDebito.addEventListener('change', toggleCuotasDiv);
    radioSaldo.addEventListener('change', toggleCuotasDiv);

    // Llamar a la función al cargar la página para asegurar que el estado inicial sea correcto
    toggleCuotasDiv();
});

    // Copiar el valor del total al abrir el modal
    document.querySelector('[data-bs-target="#modalPago"]').addEventListener('click', function () {
        document.getElementById('totalPagar').innerText = document.getElementById('total-final').innerText;
        actualizarCuotas(); // Actualizar cuotas al abrir el modal
    });

    // Mostrar u ocultar campos según la opción de pago seleccionada
    const tipoTarjetaInputs = document.querySelectorAll('input[name="tipoTarjeta"]');
    tipoTarjetaInputs.forEach(function (input) {
        input.addEventListener('change', function () {
            const tarjetaFields = document.querySelectorAll('#numeroTarjeta, #nombreTitular, #fechaVencimiento, #codigoSeguridad');
            if (this.value === 'saldo') {
                tarjetaFields.forEach(function (field) {
                    field.closest('.mb-3').style.display = 'none';
                });
                mostrarSaldoActual(5000); // Harcodeado, sustituir con el saldo real
            } else {
                tarjetaFields.forEach(function (field) {
                    field.closest('.mb-3').style.display = 'block';
                });
                ocultarSaldoActual();
            }

            // Si se selecciona tarjeta de crédito, actualizar las opciones de cuotas
            if (this.value === 'credito') {
                actualizarCuotas();
                document.getElementById('cuotasDiv').style.display = 'block';
            } else {
                document.getElementById('cuotasDiv').style.display = 'none';
            }
        });
    });

    document.getElementById('formPago').addEventListener('submit', function (event) {
        event.preventDefault();
        const errorMessages = document.getElementById('errorMessages');
        errorMessages.style.display = 'none';
        errorMessages.innerHTML = '';

        const numeroTarjeta = document.getElementById('numeroTarjeta');
        const nombreTitular = document.getElementById('nombreTitular');
        const fechaVencimiento = document.getElementById('fechaVencimiento');
        const codigoSeguridad = document.getElementById('codigoSeguridad');
        const domicilio = document.getElementById('domicilio');
        const codigoPostal = document.getElementById('codigoPostal');
        const tipoTarjeta = document.querySelector('input[name="tipoTarjeta"]:checked').value;

        const saldoActual = 5000; // Harcodeado, sustituir con el saldo real

        // Función para mostrar mensajes de error
        function showError(message) {
            errorMessages.style.display = 'block';
            errorMessages.innerHTML += '<p>' + message + '</p>';
        }

        // Validaciones del formulario
        if (tipoTarjeta === 'saldo') {
            if (totalPagar > saldoActual) {
                showError("Saldo insuficiente.");
                return false;
            }
        } else {
            if (!numeroTarjeta.value.match(/^\d{16}$/)) {
                showError("El numero de tarjeta debe tener 16 digitos.");
                return false;
            }
            if (!nombreTitular.value.match(/^[a-zA-Z\s]+$/)) {
                showError("El nombre del titular debe contener solo letras y espacios.");
                return false;
            }
            if (!codigoSeguridad.value.match(/^\d{3}$/)) {
                showError("El codigo de seguridad debe tener 3 digitos.");
                return false;
            }

            // Validar fecha de vencimiento
            const fechaVencimientoVal = new Date(fechaVencimiento.value);
            const fechaActual = new Date();
            fechaActual.setMonth(fechaActual.getMonth() + 1); // Ajustar a primer día del próximo mes
            fechaActual.setDate(1);

            if (fechaVencimientoVal < fechaActual) {
                showError("La tarjeta esta vencida. Por favor, ingrese otra tarjeta.");
                return false;
            }
        }

        if (!domicilio.value.match(/^[a-zA-Z0-9\s]+$/)) {
            showError("El domicilio no debe contener caracteres especiales.");
            return false;
        }
        if (!codigoPostal.value.match(/^\d{4}$/)) {
            showError("El codigo postal debe tener 4 digitos.");
            return false;
        }

        // Mostrar mensaje de éxito en el modal
        document.getElementById('mensajeExito').style.display = 'block';
        setTimeout(function () {
            window.location.href = '/spring/directorTecnico'; // Redirigir a la página principal

    });



    function actualizarCuotas() {
        var total = parseFloat(document.getElementById('total-final').innerText);
        var cuotasSelect = document.getElementById('cuotasSelect');

        cuotasSelect.innerHTML = ''; // Limpiar opciones existentes

        var opcionesCuotas = [
            {cuotas: 1, texto: '1 cuota de $' + total.toFixed(2)},
            {cuotas: 3, texto: '3 cuotas de $' + (total / 3).toFixed(2)},
            {cuotas: 6, texto: '6 cuotas de $' + (total / 6).toFixed(2)},
            {cuotas: 12, texto: '12 cuotas de $' + (total / 12).toFixed(2)}
        ];

        opcionesCuotas.forEach(function (opcion) {
            var option = document.createElement('option');
            option.value = opcion.cuotas;
            option.textContent = opcion.texto;
            cuotasSelect.appendChild(option);
        });
    }



    function ocultarSaldoActual() {
        const saldoActual = document.getElementById('saldoActual');
        if (saldoActual) {
            saldoActual.style.display = 'none'; // Asegúrate de ocultar el elemento si está visible
        } else {
            console.error('Elemento saldoActual no encontrado en el DOM.');
        }
    }
});