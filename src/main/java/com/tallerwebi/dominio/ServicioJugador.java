package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;

import java.util.List;

public interface ServicioJugador {
    void guardarJugador(Jugador jugador) throws JugadorExistente, JugadorInexistente;
    void eliminarJugador(int jugadorId) throws JugadorInexistente;
    Boolean actualizarJugador(int jugadorId) throws JugadorInexistente;
    Jugador buscarJugador(int jugadorId) throws JugadorInexistente;

    List<Jugador> obtenerJugadores();
}
