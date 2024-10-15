package com.tallerwebi.infraestructura;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.ServicioAdmin;
import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

@Service("servicioAdmin")
@Transactional
public class ServicioAdminImpl implements ServicioAdmin {

    private RepositorioAdmin repositorioAdmin;

    public ServicioAdminImpl(RepositorioAdmin repositorioAdmin) {
        this.repositorioAdmin = repositorioAdmin;
    }

    @Override
    public void guardarTorneo(Torneo torneo) throws TorneoExistente {
        Torneo torneoExistente = repositorioAdmin.buscarTorneoPorNombre(torneo.getNombre());

        if (torneoExistente != null && !torneoExistente.getId().equals(torneo.getId())) {
            throw new TorneoExistente();
        }

        repositorioAdmin.guardarTorneo(torneo);
    }

    @Override
    public void eliminarTorneo(Torneo torneo) {
        repositorioAdmin.eliminarTorneo(torneo);
    }

    @Override
    public void actualizarTorneo(Torneo torneo) {
        repositorioAdmin.guardarTorneo(torneo);
    }

    @Override
    public List<Torneo> obtenerTorneos() {
        return repositorioAdmin.obtenerTorneos();
    }

    @Override
    public Torneo buscarTorneoPorId(Long id) {
        return repositorioAdmin.obtenerTorneoPorId(id);
    }

    // @Override
    // public void guardarEquipo(Equipo nombre) {
    // repositorioAdmin.guardarEquipo(nombre);
    // }
    //
    // @Override
    // public void eliminarEquipo(Equipo nombre) {
    // repositorioAdmin.eliminarEquipo(nombre);
    // }
    //
    // @Override
    // public void guardarJugador(Jugador nombre) {
    // repositorioAdmin.guardarJugador(nombre);
    // }
    //
    // @Override
    // public void eliminarJugador(Jugador nombre) {
    // repositorioAdmin.eliminarJugador(nombre);
    // }

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
    public Cancha obtenerCanchaPorId(Long id) {
        return repositorioAdmin.obtenerCanchaPorId(id);
    }

    @Override
    public List<Arbitro> obtenerArbitros() {
        return repositorioAdmin.obtenerArbitros();
    }

    @Override
    public Arbitro obtenerArbitroPorId(Long id) {
        return repositorioAdmin.obtenerArbitroPorId(id);
    }

    @Override
    public void guardarPartido(Partido partido) {
        repositorioAdmin.guardarPartido(partido);
    }

    @Override
    public List<Partido> obtenerPartidos() {
        return repositorioAdmin.obtenerPartidos();
    }

    @Override
    public List<Partido> obtenerPartidosPorTorneo(Torneo torneo) {
        return repositorioAdmin.obtenerPartidosPorTorneo(torneo);
    }

    @Override
    public Partido obtenerPartidoPorId(Long id) {
        return repositorioAdmin.obtenerPartidoPorId(id);
    }

    @Override
    public Partido obtenerPartidoEsperandoRival(Torneo torneo, String fase) {
        return repositorioAdmin.obtenerPartidoEsperandoRival(torneo, fase);
    }
}
