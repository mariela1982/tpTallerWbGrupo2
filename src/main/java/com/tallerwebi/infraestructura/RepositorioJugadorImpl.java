package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioJugadorImpl implements RepositorioJugador {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    @Transactional
    public void guardar(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(jugador);

    }

    @Override
    @Transactional(readOnly = true)
    public Jugador obtenerPorDni(String dni) {
        Session session = sessionFactory.getCurrentSession();
        return (Jugador) session.createCriteria(Jugador.class)
                .add(Restrictions.eq("dni", dni))
                .uniqueResult();
    }



    @Override
    @Transactional(readOnly = true)
    public List<Jugador> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Jugador", Jugador.class).getResultList();
    }

    @Override
    public List<Jugador> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Jugador> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Jugador> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Jugador entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Jugador> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Jugador> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Jugador> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Jugador> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Jugador> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Jugador> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Jugador> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Jugador getOne(Long aLong) {
        return null;
    }

    @Override
    public Jugador getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Jugador> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Jugador> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Jugador> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Jugador> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Jugador> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Jugador> boolean exists(Example<S> example) {
        return false;
    }
}
