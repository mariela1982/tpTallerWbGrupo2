package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    @Autowired
    private SessionFactory sessionFactory;


    public RepositorioUsuarioImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }


    @Transactional
    @Override
    public Usuario buscarUsuario(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Transactional
    @Override
    public void guardar(Usuario usuario) {

        Session session =sessionFactory.getCurrentSession();
        session.save(usuario);
    }

//    @Transactional
//    @Override
//    public Usuario buscar(String email) {
//        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
//                .add(Restrictions.eq("email", email))
//                .uniqueResult();
//    }

//    @Transactional
//    @Override
//    public void modificar(Usuario usuario) {
//        sessionFactory.getCurrentSession().update(usuario);
//    }

}
