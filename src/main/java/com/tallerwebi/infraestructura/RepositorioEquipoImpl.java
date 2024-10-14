package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Torneo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioEquipo;

import java.util.List;

@Repository("repositorioEquipo")
@Transactional
public class RepositorioEquipoImpl implements RepositorioEquipo {

    private  SessionFactory sessionFactory;

    @Autowired
    public RepositorioEquipoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Equipo guardar(Equipo equipo) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(equipo);
        return equipo;
    }

    @Override
    public Equipo buscarEquipoPorNombre(String nombre) {

        Session session = sessionFactory.getCurrentSession();
        return (Equipo)session.createCriteria(Equipo.class)
                .add(Restrictions.eq("nombre",nombre))
                .uniqueResult();


    }

    @Override
    public Equipo buscarPorID(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return (Equipo)session.get(Equipo.class, id);
    }

    @Override
    public Equipo editar(Jugador nuevo, Jugador viejo, Equipo equipo) {
        Session sesion= sessionFactory.getCurrentSession();
        Jugador jugadorAeliminar = buscarJugador(viejo.getId(),equipo);

        if (jugadorAeliminar != null) {
            equipo.getJugadores().remove(jugadorAeliminar);
            equipo.getJugadores().add(nuevo);
            sesion.saveOrUpdate(equipo);
            return equipo;
        }
        return null;

    }


    @Override
    public Torneo agregarTorneo(Torneo torneo, String nombreEquipo) {
        Session sesion= sessionFactory.getCurrentSession();
        Equipo equipo = sesion.get(Equipo.class, nombreEquipo);
        if (equipo != null) {
            equipo.setTorneo(torneo);
            sesion.saveOrUpdate(equipo);
            return torneo;
        }
        return null;
    }


    @Override
    public Jugador buscarJugador(Long idJugador, Equipo equipo) {
        Session sesion= sessionFactory.getCurrentSession();
        return (Jugador) sesion.createCriteria(Equipo.class,"equipo")
                .createAlias("equipo.jugadores", "jugador")
                .add(Restrictions.eq("equipo.id",equipo.getId()))
                .add(Restrictions.eq("jugador.id",idJugador))
                .uniqueResult();

    }

    @Override
    public List<Equipo> buscarEquipos() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Equipo.class);
        return criteria.list();


    }

    @Override
    public void eliminar(Equipo equipo) {
        Session sesion= sessionFactory.getCurrentSession();
        sesion.delete(equipo);

    }

    @Override
    public Equipo buscarPorDt(Long dni) {
        Session session = sessionFactory.getCurrentSession();

        return (Equipo) session.createCriteria(Equipo.class,"equipo")
                .add(Restrictions.eq("dtDni",dni))
                .uniqueResult();
    }


}
