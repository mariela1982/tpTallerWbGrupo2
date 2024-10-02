package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioDt;
import com.tallerwebi.dominio.Torneo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioDt")

public class RepositorioDtImpl implements RepositorioDt {

        @Autowired
        private SessionFactory sessionFactory;

    @Override
    public List<Torneo> obtenerTorneos() {
        return List.of();
    }

    @Override
    public Torneo obtenerTorneoPorId(Long id) {
        return null;
    }

    @Override
    public void guardarEquipo(Equipo equipo) {

    }

    @Override
    public List<Equipo> obtenerEquipos() {
        return List.of();
    }

    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarEquipo(Equipo equipo) {

    }

    @Override
    public void guardarJugador(Jugador jugador) {

    }

    @Override
    public List<Jugador> obtenerJugadores() {
        return List.of();
    }

    @Override
    public Jugador obtenerJugadorPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarJugador(Jugador jugador) {

    }
}
