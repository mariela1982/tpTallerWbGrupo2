package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.EquipoExistente;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;

import java.util.List;

public interface ServicioEquipo {

    Equipo buscarEquipoPorNombre(String nombre) throws EquipoInexistente;


    Boolean guardarEquipo(Equipo equipo) throws EquipoExistente;

    Boolean editarEquipo(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo) throws EquipoInexistente;

    Jugador buscarJugador(int id_jugador,Equipo equipo) throws JugadorInexistente;

    Boolean agregarTorneo(Torneo torneo, String nombreEquipo) throws EquipoInexistente;

    List<Equipo> obtenerEquipos();

    Equipo buscarEquipoPorId(Integer id) throws EquipoInexistente;

    void eliminarEquipo(Equipo equipo);
}
