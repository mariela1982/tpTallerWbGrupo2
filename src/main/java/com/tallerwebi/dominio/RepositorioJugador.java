package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Usuario;

public interface RepositorioJugador {
    Jugador crearJugador(Jugador j);
    void eliminarJugador(Jugador j);
    Jugador buscarJugador(Long idJugador);
    List<Jugador> buscarJugadores();

    List<Jugador> buscarJugadoresPorEquipo(Equipo equipo);
    List<Jugador> buscarJugadoresConSancion();
    List<Usuario> obtenerUsuariosJugadores();

    void agregarEquipo(Long jugador, Equipo equipo);

    List<Jugador> buscarJugadoresPorDt(Integer id);
}
