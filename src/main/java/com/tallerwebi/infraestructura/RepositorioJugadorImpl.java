package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
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
    public void eliminarJugador(Jugador j) {

    }

    @Override
    @Transactional
    public Jugador buscarJugador(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
            return (Jugador) session.createCriteria(Jugador.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        }


    @Override
    public List<Jugador> buscarJugadores() {
        final Session session = sessionFactory.getCurrentSession();

        return session.createCriteria(Jugador.class).list();
    }
}
