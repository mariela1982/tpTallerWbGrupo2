package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("repositorioJugador")
@Transactional
public class RepositorioJugadorImpl implements RepositorioJugador {
//para tener acceso a la bd

    @Autowired
    private SessionFactory sessionFactory;

    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Jugador crearJugador(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        session.save(jugador);
        return jugador;

    }

    @Override
    public void eliminarJugador(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(jugador);

    }

    @Override
    @Transactional
    public Jugador buscarJugador(Long id) {
       return sessionFactory.getCurrentSession().get(Jugador.class, id);
        }


    @Override
    public List<Jugador> buscarJugadores() {
        final Session session = sessionFactory.getCurrentSession();

        TypedQuery<Jugador> query= session.createQuery("from Jugador", Jugador.class);
        return query.getResultList();
    }
}
