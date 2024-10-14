package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.excepcion.TorneoExistente;

public interface RepositorioAdmin {

    // Torneos
    void guardarTorneo(Torneo torneo);
    void eliminarPartido(Partido partido);
    List<Torneo> obtenerTorneos();
    Torneo buscarTorneoPorNombre(String nombre);
    Torneo obtenerTorneoPorId(Long id);













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


    Cancha obtenerCanchaPorId(Long id);


    void eliminarCancha(Cancha cancha);


    Arbitro guardarArbitro(Arbitro arbitro);


    List<Arbitro> obtenerArbitros();

    Arbitro obtenerArbitroPorId(Long id);

    void eliminarArbitro(Arbitro arbitro);

    Partido guardarPartido(Partido partido);

    List<Partido> obtenerPartidos();

    Partido obtenerPartidoPorId(Long id);


    List<Partido> obtenerPartidosPorTorneo(Torneo torneo);

    Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase);





    void eliminarTorneo(Torneo torneo);
}