package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioAdmin {

    void guardarTorneo(Torneo nombre);
    void eliminarTorneo(Torneo nombre);

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

    List<Arbitro> obtenerArbitros();

    List<Torneo> obtenerTorneos();
    Torneo obtenerTorneo(Long id);




}
