package com.tallerwebi.infraestructura;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.Arbitro;
import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.ServicioAdmin;
import com.tallerwebi.dominio.Torneo;

import java.util.List;

@Service("servicioAdmin")
@Transactional
public class ServicioAdminImpl implements ServicioAdmin {

    private RepositorioAdmin repositorioAdmin;

    public ServicioAdminImpl(RepositorioAdmin repositorioAdmin) {
        this.repositorioAdmin = repositorioAdmin;
    }

    @Override
    public void guardarTorneo(Torneo nombre) {
        repositorioAdmin.guardarTorneo(nombre);
    }

    @Override
    public void eliminarTorneo(Torneo nombre) {
        repositorioAdmin.eliminarTorneo(nombre);
    }

//    @Override
//    public void guardarEquipo(Equipo nombre) {
//        repositorioAdmin.guardarEquipo(nombre);
//    }
//
//    @Override
//    public void eliminarEquipo(Equipo nombre) {
//        repositorioAdmin.eliminarEquipo(nombre);
//    }
//
//    @Override
//    public void guardarJugador(Jugador nombre) {
//        repositorioAdmin.guardarJugador(nombre);
//    }
//
//    @Override
//    public void eliminarJugador(Jugador nombre) {
//        repositorioAdmin.eliminarJugador(nombre);
//    }

    @Override
    public void guardarCancha(Cancha nombre) {
        repositorioAdmin.guardarCancha(nombre);
    }

    @Override
    public void elimiarCancha(Cancha nombre) {
        repositorioAdmin.eliminarCancha(nombre);
    }

    @Override
    public void guardarArbitro(Arbitro nombre) {
        repositorioAdmin.guardarArbitro(nombre);
    }

    @Override
    public void eliminarArbitro(Arbitro nombre) {
        repositorioAdmin.eliminarArbitro(nombre);
    }

    @Override
    public List<Cancha> obtenerCanchas() {
        return repositorioAdmin.obtenerCanchas();
    }

    @Override
    public List<Arbitro> obtenerArbitros() {
        return repositorioAdmin.obtenerArbitros();
    }

    @Override
    public List<Torneo> obtenerTorneos() {
        return repositorioAdmin.obtenerTorneos();
    }

    @Override
    public Torneo obtenerTorneo(Long id) {
        return repositorioAdmin.obtenerTorneoPorId(id);
    }
}
