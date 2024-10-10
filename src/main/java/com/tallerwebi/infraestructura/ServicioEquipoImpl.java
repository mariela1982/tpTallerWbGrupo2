package com.tallerwebi.infraestructura;

import javax.transaction.Transactional;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.excepcion.EquipoExistente;

import java.util.List;

@Service("servicioEquipo")
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo {


RepositorioEquipo repositorioEquipo;

@Autowired
public ServicioEquipoImpl(RepositorioEquipo repositorioEquipo) {

    this.repositorioEquipo = repositorioEquipo;
}

    @Override
    public Equipo buscarEquipoPorNombre(String nombre) throws EquipoInexistente {

        Equipo equipo = repositorioEquipo.buscar(nombre);

        if(equipo == null){
            throw new EquipoInexistente();
        }
        return equipo;

     }



    @Override
    public Boolean guardarEquipo(Equipo equipo) throws EquipoExistente {

        Equipo equipoBuscado = repositorioEquipo.buscar(equipo.getNombre());

        if(equipoBuscado != null){
            throw new EquipoExistente();
        }
        repositorioEquipo.guardar(equipoBuscado);

          return  true;


    }

    @Override
    public Boolean editarEquipo(Jugador jugadornuevo, Jugador jugadorViejo, Equipo equipo) throws EquipoInexistente {

    Equipo equipoEncontrado = repositorioEquipo.buscar(equipo.getNombre());

        if(equipoEncontrado == null){
            throw new EquipoInexistente();
        }
         repositorioEquipo.editar(jugadornuevo, jugadorViejo,equipo);
        return true;

    }

    @Override
    public Jugador buscarJugador(Long idJugador,Equipo equipo) throws JugadorInexistente {
    Jugador jugadorEncontrado = repositorioEquipo.buscarJugador(idJugador,equipo);
    if(jugadorEncontrado == null){
        throw new JugadorInexistente();
    }
        return jugadorEncontrado;
    }

    @Override
    public Boolean agregarTorneo(Torneo torneo, String nombreEquipo) throws EquipoInexistente {

        Equipo equipoEncontrado = repositorioEquipo.buscar(nombreEquipo);

        if(equipoEncontrado == null){
            throw new EquipoInexistente();
        }
         repositorioEquipo.agregarTorneo(torneo,  nombreEquipo);
        return true;
    }

    @Override
    public List<Equipo> obtenerEquipos() {
        return List.of();
    }

    @Override
    public Equipo buscarEquipoPorId(Long id) throws EquipoInexistente {
        return null;
    }

    @Override
    public void eliminarEquipo(Equipo equipo) {

    }


}


