package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Torneo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioEquipo;

import java.util.List;

@Repository("repositoioEquipo")
@SuppressWarnings({ "deprecation", "unchecked" })
public class RepositorioEquipoImpl implements RepositorioEquipo {
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioEquipoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(readOnly = true)
    public Equipo buscar(String nombre) {

        //conectarse a la base de datos
        Session session = sessionFactory.getCurrentSession();

        /* filtra si dentro de la base de datos encuentra al objeto con dicha
        condicion (en este caso la de nombre)

         */
        return (Equipo)session.createCriteria(Equipo.class)
                .add(Restrictions.eq("nombre",nombre))
                .uniqueResult();
    }

    @Override
    public Equipo buscarPorID(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public Equipo guardar(Equipo equipo) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(equipo);
        return equipo;
    }

    @Transactional
    @Override
    public Equipo editar(Jugador nuevo, Jugador viejo, Equipo equipo) {

        Jugador jugadorAeliminar = buscarJugador(viejo.getId(),equipo);

        if (jugadorAeliminar != null) {
            equipo.getJugadores().remove(jugadorAeliminar);
            equipo.getJugadores().add(nuevo);
        }

        return buscar(equipo.getNombre());
    }

    @Transactional
    @Override
    public Torneo agregarTorneo(Torneo torneo, String nombreEquipo) {
        return null;
    }

    @Transactional
    @Override
    public Jugador buscarJugador(int idJugador, Equipo equipo) {
//        Session sesion= sessionFactory.getCurrentSession();
//        return (Jugador) sesion.createCriteria(Jugador.class)
//                .createAlias("Jugador.equipo","equipo")
//                .add(Restrictions.eq("equipo.id",equipo.getIdEquipo()))
//                .add(Restrictions.eq("Jugador.id",idJugador);
//           //             .uniqueResult();
return null;
    }

    @Override
    public List<Equipo> buscarEquipos() {
        return List.of();
    }

    @Override
    public void eliminar(Equipo equipo) {

    }


}
