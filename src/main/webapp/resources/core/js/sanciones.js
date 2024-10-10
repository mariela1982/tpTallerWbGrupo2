document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("equipo").addEventListener("change", function () {
        var equipoId = this.value;
        var jugadorSelect = document.getElementById("jugador");

        // Limpiar el select de jugadores
        jugadorSelect.innerHTML =
            '<option value="" disabled selected>Selecciona un jugador</option>';

        // Si no hay equipo seleccionado, no hacemos nada
        if (!equipoId) return;

        // Petición AJAX para obtener los jugadores del equipo seleccionado
        fetch("/spring/admin/sanciones/jugadores/" + equipoId)
            .then((response) => response.json())
            .then((data) => {
                // Añadir los jugadores obtenidos al select
                data.forEach(function (jugador) {
                    var option = document.createElement("option");
                    option.value = jugador.id;
                    option.text = jugador.nombre + " " + jugador.apellido;
                    jugadorSelect.appendChild(option);
                });
            })
            .catch((error) => console.error("Error:", error));
    });
});
