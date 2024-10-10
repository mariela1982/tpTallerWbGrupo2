package com.tallerwebi.dominio;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RepositorioAdmin {
    Torneo guardarTorneo(Torneo torneo);
    List<Torneo> obtenerTorneos();
    Torneo obtenerTorneoPorId(Integer id);
    void eliminarTorneo(Torneo torneo);

//    Equipo guardarEquipo(Equipo equipo);
//    List<Equipo> obtenerEquipos();
//    Equipo obtenerEquipoPorId(Integer id);
//    void eliminarEquipo(Equipo equipo);

<<<<<<< HEAD
    Jugador guardarJugador(Jugador jugador);
    List<Jugador> obtenerJugadores();
    Jugador obtenerJugadorPorId(Long id);
    void eliminarJugador(Jugador jugador);
    List<Jugador> obtenerJugadoresPorEquipo(Equipo equipo);
    List<Jugador> obtenerJugadoresConSancion();
=======
//    Jugador guardarJugador(Jugador jugador);
//    List<Jugador> obtenerJugadores();
//    Jugador obtenerJugadorPorId(Integer id);
//    void eliminarJugador(Jugador jugador);
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b

    Cancha guardarCancha(Cancha cancha);
    List<Cancha> obtenerCanchas();
    Cancha obtenerCanchaPorId(Integer id);
    void eliminarCancha(Cancha cancha);

    Arbitro guardarArbitro(Arbitro arbitro);
    List<Arbitro> obtenerArbitros();
    Arbitro obtenerArbitroPorId(Integer id);
    void eliminarArbitro(Arbitro arbitro);

    Partido guardarPartido(Partido partido);
    List<Partido> obtenerPartidos();
    Partido obtenerPartidoPorId(Long id);
    void eliminarPartido(Partido partido);
    List<Partido> obtenerPartidosPorTorneo(Torneo torneo);
    Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase);
}