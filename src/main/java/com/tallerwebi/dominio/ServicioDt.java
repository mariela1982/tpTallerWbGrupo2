package com.tallerwebi.dominio;

public interface ServicioDt {
    void guardarEquipo(Equipo nombre);
    void eliminarEquipo(Equipo nombre);

    void guardarJugador(Jugador nombre);
    void eliminarJugador(Jugador nombre);
}
