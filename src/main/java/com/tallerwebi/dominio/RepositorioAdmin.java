package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioAdmin {
    Torneo guardarTorneo(Torneo torneo);
    List<Torneo> obtenerTorneos();
    Torneo obtenerTorneoPorId(Long id);
    void eliminarTorneo(Torneo torneo);

    Equipo guardarEquipo(Equipo equipo);
    List<Equipo> obtenerEquipos();
    Equipo obtenerEquipoPorId(Long id);
    void eliminarEquipo(Equipo equipo);

    Jugador guardarJugador(Jugador jugador);
    List<Jugador> obtenerJugadores();
    Jugador obtenerJugadorPorId(Long id);
    void eliminarJugador(Jugador jugador);

    Cancha guardarCancha(Cancha cancha);
    List<Cancha> obtenerCanchas();
    Cancha obtenerCanchaPorId(Long id);
    void eliminarCancha(Cancha cancha);

    Arbitro guardarArbitro(Arbitro arbitro);
    List<Arbitro> obtenerArbitros();
    Arbitro obtenerArbitroPorId(Long id);
    void eliminarArbitro(Arbitro arbitro);

}