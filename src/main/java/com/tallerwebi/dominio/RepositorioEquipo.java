package com.tallerwebi.dominio;

public interface RepositorioEquipo {
    Equipo buscar(String nombre);

    Equipo guardar(Equipo equipo);

    Equipo editar(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo);

    Torneo  agregarTorneo(Torneo torneo, String nombreEquipo);

    Jugador buscarJugador(int idJugador, Equipo equipo);
}
