package com.tallerwebi.infraestructura;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.Arbitro;
import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.Torneo;

@Repository("repositorioAdmin")
@SuppressWarnings({ "deprecation", "unchecked" })
public class RepositorioAdminImpl implements RepositorioAdmin {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void guardarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(torneo);
    }

    @Transactional
    @Override
    public List<Torneo> obtenerTorneos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Torneo.class).list();
    }

    @Transactional
    @Override
    public Torneo obtenerTorneoPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Torneo) session.get(Torneo.class, id);
    }

    @Transactional
    @Override
    public void eliminarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(torneo);
    }

    @Transactional
    @Override
    public void guardarEquipo(Equipo equipo) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(equipo);
    }

    @Transactional
    @Override
    public List<Equipo> obtenerEquipos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Equipo.class).list();
    }

    @Transactional
    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Equipo) session.get(Equipo.class, id);
    }

    @Transactional
    @Override
    public void eliminarEquipo(Equipo equipo) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(equipo);
    }

    @Transactional
    @Override
    public void guardarJugador(Jugador jugador) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(jugador);
    }

    @Transactional
    @Override
    public List<Jugador> obtenerJugadores() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Jugador.class).list();
    }

    @Transactional
    @Override
    public Jugador obtenerJugadorPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Jugador) session.get(Jugador.class, id);
    }

    @Transactional
    @Override
    public void eliminarJugador(Jugador jugador) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(jugador);
    }

    @Transactional
    @Override
    public void guardarCancha(Cancha nombre) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(nombre);
    }

    @Transactional
    @Override
    public List<Cancha> obtenerCanchas() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cancha.class).list();
    }

    @Transactional
    @Override
    public Cancha obtenerCanchaPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Cancha) session.get(Cancha.class, id);
    }

    @Transactional
    @Override
    public void eliminarCancha(Cancha cancha) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(cancha);
    }

    @Transactional
    @Override
    public void guardarArbitro(Arbitro arbitro) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(arbitro);
    }

    @Transactional
    @Override
    public List<Arbitro> obtenerArbitros() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Arbitro.class).list();
    }

    @Transactional
    @Override
    public Arbitro obtenerArbitroPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Arbitro) session.get(Arbitro.class, id);
    }

    @Transactional
    @Override
    public void eliminarArbitro(Arbitro arbitro) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(arbitro);
    }

}
