package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service("servicioJugador")
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public void guardarJugador(Jugador nombre) throws JugadorExistente, JugadorInexistente {
        // Jugador jugadorobtenido = buscarJugador(nombre.getId());
        // try {
        // if(jugadorobtenido != null){
        // throw new JugadorExistente();
        // }
        // }catch(JugadorExistente ex){
        // repositorioJugador.crearJugador(jugadorobtenido);
        // }
        repositorioJugador.crearJugador(nombre);

    }

    @Override
    public void eliminarJugador(Long jugadorId) throws JugadorInexistente {
        Jugador jugadorBuscado = repositorioJugador.buscarJugador(jugadorId);
        if (jugadorBuscado == null) {
            throw new JugadorInexistente();
        } else {
            repositorioJugador.eliminarJugador(jugadorBuscado);
        }

    }

    @Override
    public Boolean actualizarJugador(Long jugadorId) throws JugadorInexistente {
        return null;
    }

    @Override
    public Jugador buscarJugador(Long jugadorId) throws JugadorInexistente {
        Jugador jugadorBuscado = repositorioJugador.buscarJugador(jugadorId);
        if (jugadorBuscado == null) {
            throw new JugadorInexistente();
        }
        return jugadorBuscado;
    }

    @Override
    public List<Jugador> obtenerJugadores() {

        return repositorioJugador.buscarJugadores();
    }

    @Override
    public List<Jugador> obtenerJugadoresPorEquipo(Equipo equipo) {
        return repositorioJugador.buscarJugadoresPorEquipo(equipo);
    }

    @Override
    public List<Jugador> obtenerJugadoresConSancion() {
        return repositorioJugador.buscarJugadoresConSancion();
    }

    @Override
    public void agregarleEquipo(Long jugador, Equipo equipo) {
        repositorioJugador.agregarEquipo(jugador, equipo);
    }

    @Override
    public List<Usuario> obtenerUsuariosJugadores() {
            return repositorioJugador.obtenerUsuariosJugadores();
    }
}
