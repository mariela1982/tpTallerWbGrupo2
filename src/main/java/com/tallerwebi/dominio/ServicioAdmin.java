package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

public interface ServicioAdmin {

    void guardarTorneo(Torneo torneo) throws TorneoExistente;
    void eliminarTorneo(Torneo torneo);
    void actualizarTorneo(Torneo torneo);
    List<Torneo> obtenerTorneos();
    Torneo buscarTorneoPorId(Long id);

//    void guardarEquipo(Equipo nombre);
//    void eliminarEquipo(Equipo nombre);
//
//    void guardarJugador(Jugador nombre);
//    void eliminarJugador(Jugador nombre);

    void guardarCancha(Cancha nombre);
    void elimiarCancha(Cancha nombre);

    void guardarArbitro(Arbitro nombre);
    void eliminarArbitro(Arbitro nombre);

    List<Cancha> obtenerCanchas();
    Cancha obtenerCanchaPorId(Long id);

    List<Arbitro> obtenerArbitros();
    Arbitro obtenerArbitroPorId(Long id);


    void guardarPartido(Partido partido);
    List<Partido> obtenerPartidos();
    List<Partido> obtenerPartidosPorTorneo(Torneo torneo);
    Partido obtenerPartidoPorId(Long id);
    Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase);



}
