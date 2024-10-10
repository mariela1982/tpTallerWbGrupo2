package com.tallerwebi.infraestructura;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.Arbitro;
import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Partido;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.Torneo;

@Repository("repositorioAdmin")
@SuppressWarnings({ "deprecation", "unchecked" })
public class RepositorioAdminImpl implements RepositorioAdmin {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Torneo guardarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(torneo);
        return torneo;
    }

    @Transactional
    @Override
    public List<Torneo> obtenerTorneos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT t FROM Torneo t").list();
    }

    @Transactional
    @Override
    public Torneo obtenerTorneoPorId(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Torneo) session.get(Torneo.class, id);
    }

    @Transactional
    @Override
    public void eliminarTorneo(Torneo torneo) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(torneo);
    }
//
//    @Override
//    public Equipo guardarEquipo(Equipo equipo) {
//        return null;
//    }

//    @Transactional
//    @Override
//    public Equipo guardarEquipo(Equipo equipo) {
//        final Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(equipo);
//        return equipo;
//    }

<<<<<<< HEAD
    @Transactional
    @Override
    public List<Equipo> obtenerEquipos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT e FROM Equipo e").list();
    }
=======
//    @Transactional
//    @Override
//    public List<Equipo> obtenerEquipos() {
//        final Session session = sessionFactory.getCurrentSession();
//        return session.createCriteria(Equipo.class).list();
//    }
//
//    @Transactional
//    @Override
//    public Equipo obtenerEquipoPorId(Integer id) {
//        final Session session = sessionFactory.getCurrentSession();
//        return (Equipo) session.get(Equipo.class, id);
//    }
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b

//    @Transactional
//    @Override
//    public void eliminarEquipo(Equipo equipo) {
//        final Session session = sessionFactory.getCurrentSession();
//        session.delete(equipo);
//    }

//    @Transactional
//    @Override
//    public Jugador guardarJugador(Jugador jugador) {
//        final Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(jugador);
//        return jugador;
//    }

//    @Transactional
//    @Override
//    public List<Jugador> obtenerJugadores() {
//        final Session session = sessionFactory.getCurrentSession();
//        return session.createCriteria(Jugador.class).list();
//    }

//    @Transactional
//    @Override
//    public Jugador obtenerJugadorPorId(Long id) {
//        final Session session = sessionFactory.getCurrentSession();
//        return (Jugador) session.get(Jugador.class, id);
//    }

//    @Transactional
//    @Override
//    public void eliminarJugador(Jugador jugador) {
//        final Session session = sessionFactory.getCurrentSession();
//        session.delete(jugador);
//    }

    @Transactional
    @Override
    public List<Jugador> obtenerJugadoresPorEquipo(Equipo equipo) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Jugador.class).add(Restrictions.eq("equipo", equipo)).list();
    }

    @Transactional
    @Override
    public List<Jugador> obtenerJugadoresConSancion() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Jugador.class).add(Restrictions.isNotNull("sancion")).list();
    }

    @Transactional
    @Override
    public Cancha guardarCancha(Cancha nombre) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(nombre);
        return nombre;
    }

    @Transactional
    @Override
    public List<Cancha> obtenerCanchas() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cancha.class).list();
    }


    @Transactional
    @Override
    public Cancha obtenerCanchaPorId(Integer id) {
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
    public Arbitro guardarArbitro(Arbitro arbitro) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(arbitro);
        return arbitro;
    }

    @Transactional
    @Override
    public List<Arbitro> obtenerArbitros() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Arbitro.class).list();
    }



    @Transactional
    @Override
    public Arbitro obtenerArbitroPorId(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Arbitro) session.get(Arbitro.class, id);
    }

    @Transactional
    @Override
    public void eliminarArbitro(Arbitro arbitro) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(arbitro);
    }

    @Transactional
    @Override
    public Partido guardarPartido(Partido partido) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(partido);
        return partido;
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
        return session.createQuery("SELECT DISTINCT p FROM Partido p WHERE p.torneo = :torneo").setParameter("torneo", torneo).list();
    }

    @Transactional
    @Override
    public Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase) {
        // Devuelve el primer partido que no tiene equipo visitante que se encuentre en
        // la fase indicada y pertenezca al torneo indicado
        final Session session = sessionFactory.getCurrentSession();
        return (Partido) session.createCriteria(Partido.class).add(Restrictions.isNull("equipoVisitante"))
                .add(Restrictions.eq("fase", fase)).add(Restrictions.eq("torneo", torneo)).uniqueResult();
    }
}
