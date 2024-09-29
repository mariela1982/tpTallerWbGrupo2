package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DniInvalidoException;
import com.tallerwebi.dominio.excepcion.NombreInvalidoException;

public interface ServicioJugador {
    Jugador guardar(Jugador jugador) throws NombreInvalidoException, DniInvalidoException;
    Boolean verificarDni(String dni) throws DniInvalidoException;
    Boolean verificarNombre(String nombre) throws NombreInvalidoException;
    Boolean verificarFechaDeNacimiento(String fecha);
    Boolean verificarDatosDelJugador(Jugador jugador) throws DniInvalidoException, NombreInvalidoException;
}
