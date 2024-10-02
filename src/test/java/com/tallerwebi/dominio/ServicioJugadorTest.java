package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DniInvalidoException;
import com.tallerwebi.dominio.excepcion.NombreInvalidoException;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import com.tallerwebi.infraestructura.ServicioJugadorImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static com.tallerwebi.presentacion.ControladorJugadorTest.generarJugador;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioJugadorTest {

    RepositorioJugador repositorioJugador = mock(RepositorioJugadorImpl.class);
    ServicioJugador servicioJugador = new ServicioJugadorImpl(repositorioJugador);

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testSessionFactory() {
        assertNotNull(sessionFactory);
        assertNotNull(sessionFactory.openSession());
    }


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

    @Test
    public void testObtenerTodos() throws NombreInvalidoException, DniInvalidoException {
        // Crear jugadores de prueba
        Jugador jugador1 = new Jugador();
        jugador1.setNombre("Kevin");
        jugador1.setFechaNacimiento("12/07/2003");
        jugador1.setPosicion("Delantero");
        jugador1.setDni("45285663");

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("Alexis");
        jugador2.setFechaNacimiento("13/07/2003");
        jugador2.setDni("22222222");


        jugador2.setPosicion("Defensa");


        servicioJugador.guardar(jugador1);
        servicioJugador.guardar(jugador2);

        Jugador jugadorObtenido = servicioJugador.obtenerPorDni("45285663");
        Jugador jugadorObtenido2 = servicioJugador.obtenerPorDni("22222222");



        assertEquals("Kevin", jugadorObtenido.getNombre());
        assertEquals("Alexis", jugadorObtenido2.getNombre());





        // Verificar que se llamó al repositorio
    }

}
