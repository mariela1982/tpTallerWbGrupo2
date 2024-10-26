package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

public interface ServicioAdmin {

    // Torneos
    void guardarTorneo(Torneo torneo) throws TorneoExistente;
    void eliminarTorneo(Torneo torneo);
    void actualizarTorneo(Torneo torneo);
    List<Torneo> obtenerTorneos();
    List<Torneo> obtenerTorneosPorPartidoDeBsAs(PartidosDeBsAs partido);
    Torneo buscarTorneoPorId(Long id);

    // Canchas
    void guardarCancha(Cancha nombre);
    void elimiarCancha(Cancha nombre);
    List<Cancha> obtenerCanchas();
    Cancha obtenerCanchaPorId(Long id);

    // Arbitros
    void guardarArbitro(Arbitro nombre);
    void eliminarArbitro(Arbitro nombre);
    List<Arbitro> obtenerArbitros();
    Arbitro obtenerArbitroPorId(Long id);

    // Partidos
    void guardarPartido(Partido partido);
    List<Partido> obtenerPartidos();
    List<Partido> obtenerPartidosPorTorneo(Torneo torneo);
    Partido obtenerPartidoPorId(Long id);
    Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase);

}
