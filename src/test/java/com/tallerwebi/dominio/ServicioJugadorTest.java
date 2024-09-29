package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DniInvalidoException;
import com.tallerwebi.dominio.excepcion.NombreInvalidoException;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import com.tallerwebi.infraestructura.ServicioJugadorImpl;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static com.tallerwebi.presentacion.ControladorJugadorTest.generarJugador;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioJugadorTest {

    RepositorioJugador repositorioJugador = mock(RepositorioJugadorImpl.class);
    ServicioJugador servicioJugador = new ServicioJugadorImpl(repositorioJugador);



    @Test
    public void queSePuedaGuardarJugador() throws NombreInvalidoException, DniInvalidoException {
        Jugador jugador = generarJugador();
        thenRegistroEsExitoso(whenRegistroJugador(jugador));
    }

    @Test
    public void queSeLanceUnaExcepcionSiElNombreContieneNumeros(){
        Jugador jugador = generarJugador();
        jugador.setNombre("Kevin1");
        assertThrows(NombreInvalidoException.class, () -> {
            servicioJugador.guardar(jugador);
        });

    }

    @Test
    public void queSeLanceUnaExcepcionSiElDniNoTieneUnaLongitudDe8Caracteres(){
        Jugador jugador = generarJugador();
        jugador.setDni("456");
        assertThrows(DniInvalidoException.class, () -> {
            servicioJugador.guardar(jugador);
        });

    }

    @Test
    public void queSeLanceUnaExcepcionSiLaFechaNoCoincideConElFormatoAcordado(){
        Jugador jugador = generarJugador();
        jugador.setFechaNacimiento("2003/07/12");
        assertThrows(IllegalArgumentException.class, () -> {
            servicioJugador.guardar(jugador);
        });

    }



    private void thenRegistroEsExitoso(Jugador jugador) {
        assertThat(jugador,notNullValue());
        verify(repositorioJugador,times(1)).guardar(jugador);

    }

    private Jugador whenRegistroJugador(Jugador jugador) throws NombreInvalidoException, DniInvalidoException {
        return this.servicioJugador.guardar(jugador);
    }


}
