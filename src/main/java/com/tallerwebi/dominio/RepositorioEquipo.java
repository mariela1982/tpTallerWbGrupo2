package com.tallerwebi.dominio;

import java.util.List;

import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Torneo;

public interface RepositorioEquipo {
    Equipo buscarEquipoPorNombre(String nombre);
    Equipo buscarPorID(Long id);

    Equipo guardar(Equipo equipo);

    Equipo editar(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo);

    Torneo  agregarTorneo(Torneo torneo, String nombreEquipo);

    Jugador buscarJugador(Long idJugador, Equipo equipo);

    List<Equipo> buscarEquipos();

    void eliminar(Equipo equipo);

    Equipo buscarPorDt(Long dni);
}
