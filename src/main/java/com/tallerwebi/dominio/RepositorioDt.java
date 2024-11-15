package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Torneo;

public interface RepositorioDt {

    List<Torneo> obtenerTorneos();
    Torneo obtenerTorneoPorId(Long id);


    void guardarEquipo(Equipo equipo);
    List<Equipo> obtenerEquipos();
    Equipo obtenerEquipoPorId(Long id);
    void eliminarEquipo(Equipo equipo);

    void guardarJugador(Jugador jugador);
    List<Jugador> obtenerJugadores(Integer id);
    Jugador obtenerJugadorPorId(Long id);
    void eliminarJugador(Jugador jugador);
}
