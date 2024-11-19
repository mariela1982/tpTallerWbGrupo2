package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioDt;
import com.tallerwebi.dominio.ServicioDt;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioDt")
@Transactional
public class ServicioDtImpl implements ServicioDt {

    private RepositorioDt repositorioDt;

    @Autowired
    public ServicioDtImpl(RepositorioDt repositorioDt){
        this.repositorioDt = repositorioDt;
    }

    @Override
    public void guardarEquipo(Equipo nombre) {

    }

    @Override
    public void eliminarEquipo(Equipo nombre) {

    }

    @Override
    public List<Jugador> obtenerJugadores(Integer id) {
        return repositorioDt.obtenerJugadores(id);
    }

    @Override
    public void guardarJugadorCreadoPorDt(Jugador jugador) {


    }


}
