package com.tallerwebi.dominio;




import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioJugador extends JpaRepository<Jugador, Long> {
    void guardar(Jugador jugador);

    Jugador obtenerPorDni(String dni);
}
