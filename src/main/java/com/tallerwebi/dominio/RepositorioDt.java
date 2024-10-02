package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioDt {

    List<Torneo> obtenerTorneos();
    Torneo obtenerTorneoPorId(Long id);


    void guardarEquipo(Equipo equipo);
    List<Equipo> obtenerEquipos();
    Equipo obtenerEquipoPorId(Long id);
    void eliminarEquipo(Equipo equipo);

    void guardarJugador(Jugador jugador);
    List<Jugador> obtenerJugadores();
    Jugador obtenerJugadorPorId(Long id);
    void eliminarJugador(Jugador jugador);
}
