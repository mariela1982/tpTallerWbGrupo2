
let localidadesPorPartido = /*[[${localidadesPorPartido}]]*/ {};
function cargarLocalidades() {

    const partidoSelect = document.getElementById('partidosDeBsAs');
    const localidadSelect = document.getElementById('localidades');
    const partidoSeleccionado = partidoSelect.value;

    localidadSelect.innerHTML = '<option value="" disabled selected>Selecciona una localidad</option>'; // para poder limpiar luego de cada eleccion las  localidades anteriores
    if (partidoSeleccionado) {
        const localidades = localidadesPorPartido[partidoSeleccionado];
        localidades.forEach(localidad => {
            const option = document.createElement("option");
            option.value = localidad;
            option.textContent = localidad;
            localidadSelect.appendChild(option);
        });
    }

}
// fetch(`/localidades?partido=${partidoSeleccionado}`)
//     .then(response => response.json())
//     .then(localidades => {
//         localidades.forEach(localidad => {
//             const option = document.createElement("option");
//             option.value = localidad;
//             option.textContent = localidad;
//             localidadSelect.appendChild(option);
//         });