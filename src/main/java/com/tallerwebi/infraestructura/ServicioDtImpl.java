package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioDt;
import com.tallerwebi.dominio.ServicioDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void guardarJugador(Jugador nombre) {

    }

    @Override
    public void eliminarJugador(Jugador nombre) {

    }
}
