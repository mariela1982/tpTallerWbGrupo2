package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioJugador {
    void agregarJugador(Jugador j);
    void eliminarJugador(Jugador j);
    Jugador buscarJugador(int nombre);
    List<Jugador> buscarJugadores();

}
