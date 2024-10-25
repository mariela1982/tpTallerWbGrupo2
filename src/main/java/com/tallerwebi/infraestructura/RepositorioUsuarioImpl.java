package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.entidades.Usuario;

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

    @Transactional
    @Override
    public void modificar(Usuario usuario) {

        Usuario existingUsuario = sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());

        if (existingUsuario != null) {
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setDireccion(usuario.getDireccion());
            existingUsuario.setPassword(usuario.getPassword());
            existingUsuario.setAdmin(usuario.getAdmin());


            sessionFactory.getCurrentSession().update(existingUsuario);
        } else {
            sessionFactory.getCurrentSession().saveOrUpdate(usuario);
        }
    }


    @Override
    public void actualizarSaldo(Usuario usuario, Integer nuevoSaldo) {
        final Session session = sessionFactory.getCurrentSession();

        // Actualizar el saldo en el objeto usuario
        usuario.setSaldo(nuevoSaldo);

        // Guardar o actualizar el objeto usuario en la base de datos
        session.update(usuario); // Usa update o save dependiendo de tu lógica
    }


}
