package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.excepcion.DniInvalidoException;
import com.tallerwebi.dominio.excepcion.NombreInvalidoException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;


@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static final Pattern PATRON_NUMEROS = Pattern.compile(".*\\d.*");


    private final RepositorioJugador repositorioJugador;

    public ServicioJugadorImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public Jugador guardar(Jugador jugador) throws NombreInvalidoException, DniInvalidoException {
        verificarDatosDelJugador(jugador);
        this.repositorioJugador.guardar(jugador);
        return jugador;
    }



    @Override
    public Boolean verificarDatosDelJugador(Jugador jugador) throws DniInvalidoException, NombreInvalidoException {

        verificarDni(jugador.getDni());
        verificarNombre(jugador.getNombre());
        verificarFechaDeNacimiento(jugador.getFechaNacimiento());
        return true;
    }

    @Override
    public Boolean verificarDni(String dni) throws DniInvalidoException {

        if(dni.length() != 8){
            throw new DniInvalidoException("Dni no es valido");
        }
        return true;

    }

    @Override
    public Boolean verificarNombre(String nombre) throws NombreInvalidoException {

        if(nombre.isEmpty() || PATRON_NUMEROS.matcher(nombre).matches()){
            throw new NombreInvalidoException("Nombre no valido");

        }
        return true;
    }

    @Override
    public Boolean verificarFechaDeNacimiento(String fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            // Intenta parsear la fecha
            LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento debe estar en el formato " + DATE_FORMAT);
        }
        return true;
    }
}
