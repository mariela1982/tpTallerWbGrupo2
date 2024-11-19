package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;

import java.util.List;

public interface ServicioDt {
    void guardarEquipo(Equipo nombre);
    void eliminarEquipo(Equipo nombre);


    List<Jugador> obtenerJugadores(Integer id);

    void guardarJugadorCreadoPorDt(Jugador jugador);
}
