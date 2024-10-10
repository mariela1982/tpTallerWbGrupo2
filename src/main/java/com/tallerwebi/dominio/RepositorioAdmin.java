package com.tallerwebi.dominio;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface RepositorioAdmin {

//    Cancha guardarCancha(Cancha cancha);
//    List<Cancha> obtenerCanchas();
//    Cancha obtenerCanchaPorId(Integer id);
//    void eliminarCancha(Cancha cancha);
//
//    Arbitro guardarArbitro(Arbitro arbitro);
//    List<Arbitro> obtenerArbitros();
//    Arbitro obtenerArbitroPorId(Integer id);
//    void eliminarArbitro(Arbitro arbitro);

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

    List<Torneo> obtenerTorneos();

    Torneo obtenerTorneoPorId(Long id);

    Torneo guardarTorneo(Torneo torneo);


    void eliminarTorneo(Torneo torneo);
}