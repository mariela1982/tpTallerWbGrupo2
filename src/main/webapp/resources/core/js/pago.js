

// Inicializar Mercado Pago al comienzo del script
const mp = new MercadoPago("TEST-4c9e3fb2-ceac-40fe-bb45-55258520ecb1", {locale: "es-AR"});



document.getElementById("mercadoPago").addEventListener('click', async () => {
    if (verificarCupos()) {
        iniciarPago();
    }
});

function verificarCupos() {
    var cuposDisponible = parseInt(document.getElementById('cuposDisponibles').textContent);
    var usuario = document.getElementById('logueado');

    if (cuposDisponible === 0) {
        alert("No Hay cupos disponibles");
        return false;
    }
    return true;
}

async function iniciarPago() {
    try {

        const nombreTorneo = document.getElementById('nombreTorneoValor').textContent;
        const valorTorneo = document.getElementById('valorTorneoValor').textContent;
     //   const torneoId = document.querySelector("input[name='torneoId']").value;

        console.log("Nombre del torneo:", nombreTorneo);
        console.log("Valor del torneo:", valorTorneo);

        const orderData = {
            title: nombreTorneo,
            quantity: 1,
            price: valorTorneo
        };

        const response = await fetch("../api/mp/crear_preferencia", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(orderData),
        });

        const preference = await response.json();

        // Llama a la función para crear el botón con el ID de preferencia
        createCheckoutButton(preference.id);
    } catch (error) {
        alert("Error: " + error.message);
    }
}

// Define la función createCheckoutButton antes de usarla
const createCheckoutButton = (preferenceId) => {
    const bricksBuilder = mp.bricks();

    const renderComponent = async () => {
        // Evita crear más de un botón de Mercado Pago
        if (window.checkoutButton) window.checkoutButton.unmount();

        window.checkoutButton = await bricksBuilder.create("wallet", "wallet_container", {
            initialization: {
                preferenceId: preferenceId,
            }
        });
    };

    renderComponent();
};



