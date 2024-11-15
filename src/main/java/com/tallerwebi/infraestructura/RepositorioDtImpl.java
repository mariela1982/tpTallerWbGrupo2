package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioDt;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Torneo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("repositorioDt")

public class RepositorioDtImpl implements RepositorioDt {

        @Autowired
        private SessionFactory sessionFactory;

        public RepositorioDtImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

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
    public List<Jugador> obtenerJugadores(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
        TypedQuery <Jugador> query = session.createQuery("from Jugador j where j.directorTecnico.id = :idDt", Jugador.class);
        query.setParameter("idDt", id);
        return query.getResultList();
    }



    @Override
    public Jugador obtenerJugadorPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarJugador(Jugador jugador) {

    }
}
