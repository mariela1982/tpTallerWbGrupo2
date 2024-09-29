package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Part;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ControladorJugadorTest {





    ControladorJugador controladorJugador = new ControladorJugador();

    @Test
    public void crearJugador() {
        Jugador jugadorMock = mock(Jugador.class);

        inicializarJugador(jugadorMock);

        Long id = jugadorMock.getId();
        String nombre = jugadorMock.getNombre();
        LocalDate fechaNacimiento = jugadorMock.getFechaNacimiento();
        String posicion = jugadorMock.getPosicion();
        String dni = jugadorMock.getDni();
        PartidosDeBsAs partido = jugadorMock.getPartidosDeBsAs();


        assertEquals("Kevin",nombre);
        assertEquals(LocalDate.of(2003,7,12),fechaNacimiento);
        assertEquals("Delantero",posicion);
        assertEquals("45285663",dni);
        assertEquals(PartidosDeBsAs.LA_MATANZA, partido);
        assertEquals((Long) 1L,id);
    }

    private static void inicializarJugador(Jugador jugadorMock) {
        when(jugadorMock.getId()).thenReturn(1L);
        when(jugadorMock.getNombre()).thenReturn("Kevin");
        when(jugadorMock.getFechaNacimiento()).thenReturn(LocalDate.of(2003,7,12));
        when(jugadorMock.getPosicion()).thenReturn("Delantero");
        when(jugadorMock.getDni()).thenReturn("45285663");
        when(jugadorMock.getPartidosDeBsAs()).thenReturn(PartidosDeBsAs.LA_MATANZA);
    }


    @Test
    public void queAlRegistrarJugadorMeRotorneUnMensajeEnLaMismaVista() {
        Jugador jugadorMock = mock(Jugador.class);


        inicializarJugador(jugadorMock);





    }


}
