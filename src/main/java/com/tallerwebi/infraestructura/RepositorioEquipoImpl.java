package com.tallerwebi.infraestructura;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioEquipo;

@SuppressWarnings("deprecation")
@Repository
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
    @Transactional
    public void guardar(Equipo equipoCreado) {
        Session session = sessionFactory.getCurrentSession();
        session.save(equipoCreado);
    }
}
