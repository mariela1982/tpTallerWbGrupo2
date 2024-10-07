package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("repositorioJugador")
@Transactional
public class RepositorioJugadorImpl implements RepositorioJugador {

    @Override
    public void agregarJugador(Jugador j) {

    }

    @Override
    public void eliminarJugador(Jugador j) {

    }

    @Override
    public Jugador buscarJugador(int nombre) {
        return null;
    }

    @Override
    public List<Jugador> buscarJugadores() {
        return List.of();
    }
}
