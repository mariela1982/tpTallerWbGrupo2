package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioEquipo {
    Equipo buscar(String nombre);
    Equipo buscarPorID(Long id);

    Equipo guardar(Equipo equipo);

    Equipo editar(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo);

    Torneo  agregarTorneo(Torneo torneo, String nombreEquipo);

    Jugador buscarJugador(Long idJugador, Equipo equipo);

    List<Equipo> buscarEquipos();

    void eliminar(Equipo equipo);
}
