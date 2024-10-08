package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioJugador {
    Jugador crearJugador(Jugador j);
    void eliminarJugador(Jugador j);
    Jugador buscarJugador(Integer nombre);
    List<Jugador> buscarJugadores();

}
