package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;

public interface RepositorioJugador {
    Jugador crearJugador(Jugador j);
    void eliminarJugador(Jugador j);
    Jugador buscarJugador(Long idJugador);
    List<Jugador> buscarJugadores();

    List<Jugador> buscarJugadoresPorEquipo(Equipo equipo);
    List<Jugador> buscarJugadoresConSancion();
}
