package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;

import java.util.List;

public interface ServicioJugador {
    void guardarJugador(Jugador jugador) throws JugadorExistente, JugadorInexistente;
    void eliminarJugador(Integer jugadorId) throws JugadorInexistente;
    Boolean actualizarJugador(Integer jugadorId) throws JugadorInexistente;
    Jugador buscarJugador(Integer jugadorId) throws JugadorInexistente;

    List<Jugador> obtenerJugadores();
}
