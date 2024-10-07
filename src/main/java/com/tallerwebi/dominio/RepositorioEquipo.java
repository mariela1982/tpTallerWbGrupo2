package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioEquipo {
    Equipo buscar(String nombre);
    Equipo buscarPorID(Integer id);

    Equipo guardar(Equipo equipo);

    Equipo editar(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo);

    Torneo  agregarTorneo(Torneo torneo, String nombreEquipo);

    Jugador buscarJugador(int idJugador, Equipo equipo);

    List<Equipo> buscarEquipos();

    void eliminar(Equipo equipo);
}
