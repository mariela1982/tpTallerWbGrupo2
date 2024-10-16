package com.tallerwebi.infraestructura;

import java.util.List;

import javax.transaction.Transactional;

import com.tallerwebi.dominio.entidades.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

@Repository("repositorioAdmin")
@SuppressWarnings({ "deprecation", "unchecked" })
public class RepositorioAdminImpl implements RepositorioAdmin {
    @Autowired
    private SessionFactory sessionFactory;

    // TORNEOS
    @Transactional
    @Override
    public void guardarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        //session.merge(torneo);
        session.saveOrUpdate(torneo);

        for (Equipo equipo : torneo.getEquipos()) {
            equipo.setTorneo(torneo);
            session.saveOrUpdate(equipo);
        }
    }

    @Transactional
    @Override
    public void eliminarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(torneo);
    }

    @Transactional
    @Override
    public List<Torneo> obtenerTorneos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT t FROM Torneo t").list();
    }

    @Transactional
    @Override
    public Torneo buscarTorneoPorNombre(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return (Torneo) session.createCriteria(Torneo.class).add(Restrictions.eq("nombre", nombre)).uniqueResult();
    }

    @Override
    @Transactional
    public Torneo obtenerTorneoPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Torneo) session.get(Torneo.class, id);
    }

    // CANCHAS
    @Transactional
    @Override
    public void guardarCancha(Cancha cancha) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cancha);
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

    // ARBITROS
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

    // PARTIDOS
    @Transactional
    @Override
    public void guardarPartido(Partido partido) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(partido);
    }

    @Transactional
    @Override
    public List<Partido> obtenerPartidos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Partido.class).list();
    }

    @Transactional
    @Override
    public Partido obtenerPartidoPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Partido) session.get(Partido.class, id);
    }

    @Transactional
    @Override
    public void eliminarPartido(Partido partido) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(partido);
    }

    @Transactional
    @Override
    public List<Partido> obtenerPartidosPorTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT p FROM Partido p WHERE p.torneo = :torneo")
                .setParameter("torneo", torneo).list();
    }

    @Transactional
    @Override
    public Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase) {
        // Devuelve el primer partido que no tiene equipo visitante que se encuentre
        final Session session = sessionFactory.getCurrentSession();
        return (Partido) session.createCriteria(Partido.class).add(Restrictions.isNull("equipoVisitante"))
                .add(Restrictions.eq("fase", fase)).add(Restrictions.eq("torneo", torneo)).uniqueResult();
    }
}
