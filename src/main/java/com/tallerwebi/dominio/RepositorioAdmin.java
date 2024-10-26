package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

public interface RepositorioAdmin {

    // Torneos
    void guardarTorneo(Torneo torneo);
    void eliminarTorneo(Torneo torneo);
    List<Torneo> obtenerTorneos();
    List<Torneo> obtenerTorneosPorPartidoDeBsAs(PartidosDeBsAs partido);
    Torneo buscarTorneoPorNombre(String nombre);
    Torneo obtenerTorneoPorId(Long id);

    // Canchas
    void guardarCancha(Cancha cancha);
    void eliminarCancha(Cancha cancha);
    List<Cancha> obtenerCanchas();
    Cancha obtenerCanchaPorId(Long id);

    // Arbitros
    void guardarArbitro(Arbitro arbitro);
    void eliminarArbitro(Arbitro arbitro);
    List<Arbitro> obtenerArbitros();
    Arbitro obtenerArbitroPorId(Long id);

    // Partidos
    void guardarPartido(Partido partido);
    void eliminarPartido(Partido partido);
    List<Partido> obtenerPartidos();
    List<Partido> obtenerPartidosPorTorneo(Torneo torneo);
    Partido obtenerPartidoPorId(Long id);
    Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase);
}