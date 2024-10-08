package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
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
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador){
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public void guardarJugador(Jugador nombre) throws JugadorExistente, JugadorInexistente {
//        Jugador jugadorobtenido = buscarJugador(nombre.getId());
//        try {
//            if(jugadorobtenido != null){
//                throw new JugadorExistente();
//            }
//        }catch(JugadorExistente ex){
//            repositorioJugador.crearJugador(jugadorobtenido);
//        }
        repositorioJugador.crearJugador(nombre);

    }

    @Override
    public void eliminarJugador(int jugadorId) throws JugadorInexistente {

    }

    @Override
    public Boolean actualizarJugador(int jugadorId) throws JugadorInexistente {
        return null;
    }

    @Override
    public Jugador buscarJugador(int jugadorId) throws JugadorInexistente {
        return null;
    }

    @Override
    public List<Jugador> obtenerJugadores() {
        return List.of();
    }


}
