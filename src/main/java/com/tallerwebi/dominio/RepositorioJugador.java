package com.tallerwebi.dominio;


import java.util.List;

public interface RepositorioJugador {
    void guardar(Jugador jugador);
    public List<Jugador> obtenerTodos();
}
