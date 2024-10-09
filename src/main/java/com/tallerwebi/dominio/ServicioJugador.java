package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;

import java.util.List;

public interface ServicioJugador {
    void guardarJugador(Jugador jugador) throws JugadorExistente, JugadorInexistente;
    void eliminarJugador(Long jugadorId) throws JugadorInexistente;
    Boolean actualizarJugador(Long jugadorId) throws JugadorInexistente;
    Jugador buscarJugador(Long jugadorId) throws JugadorInexistente;

    List<Jugador> obtenerJugadores();
}
