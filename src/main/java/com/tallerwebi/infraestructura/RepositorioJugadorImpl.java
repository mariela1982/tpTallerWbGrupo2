package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RepositorioJugadorImpl implements RepositorioJugador {

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    @Transactional
    public void guardar(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        session.save(jugador);

    }

    @Override
    @Transactional(readOnly = true)
    public List obtenerTodos() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Jugador.class).list();
    }


}
