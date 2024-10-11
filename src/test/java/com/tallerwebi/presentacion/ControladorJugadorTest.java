//package com.tallerwebi.presentacion;
//
//import com.tallerwebi.dominio.Jugador;
//import com.tallerwebi.dominio.ServicioJugador;
//import com.tallerwebi.dominio.enums.PartidosDeBsAs;
//import com.tallerwebi.dominio.excepcion.DniInvalidoException;
//import com.tallerwebi.dominio.excepcion.NombreInvalidoException;
//import com.tallerwebi.infraestructura.ServicioJugadorImpl;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@WebAppConfiguration
//
//public class ControladorJugadorTest {
//
//
//    ServicioJugador servicioJugador = mock(ServicioJugadorImpl.class);
//    ControladorJugador controladorJugador = new ControladorJugador(servicioJugador);
//
//
//    @Mock
//    private Model model = mock(Model.class);
//
//
//    @Test
//    public void crearJugador() {
//        Jugador jugadorMock = mock(Jugador.class);
//
//        inicializarJugador(jugadorMock);
//
//        Long id = jugadorMock.getId();
//        String nombre = jugadorMock.getNombre();
//        String fechaNacimiento = jugadorMock.getFechaNacimiento();
//        String posicion = jugadorMock.getPosicion();
//        String dni = jugadorMock.getDni();
//        PartidosDeBsAs partido = jugadorMock.getPartido();
//
//
//        assertEquals("Kevin",nombre);
//        assertEquals(LocalDate.of(2003,7,12),fechaNacimiento);
//        assertEquals("Delantero",posicion);
//        assertEquals("45285663",dni);
//        assertEquals(PartidosDeBsAs.LA_MATANZA, partido);
//        assertEquals((Long) 1L,id);
//    }
//
//    private static void inicializarJugador(Jugador jugadorMock) {
//        when(jugadorMock.getId()).thenReturn(1L);
//        when(jugadorMock.getNombre()).thenReturn("Kevin");
//        when(jugadorMock.getFechaNacimiento()).thenReturn("12/07/2003");
//        when(jugadorMock.getPosicion()).thenReturn("Delantero");
//        when(jugadorMock.getDni()).thenReturn("45285663");
//        when(jugadorMock.getPartido()).thenReturn(PartidosDeBsAs.LA_MATANZA);
//    }
//
//
//    @Test
//    public void queAlRegistrarJugadorMeRotorneUnMensajeEnLaMismaVista() throws NombreInvalidoException, DniInvalidoException {
//
//
//        Jugador jugador = new Jugador();
//        jugador.setNombre("Kevin");
//        jugador.setDni("45285663");
//        jugador.setFechaNacimiento("12/07/2003");
//        jugador.setPosicion("DELANTERO");
//        jugador.setPartido(PartidosDeBsAs.LA_MATANZA);
//
//        String vista = controladorJugador.crearJugador(jugador, model);
//
//
//        verify(model).addAttribute("mensajeExito", "Jugador registrado exitosamente.");
//        assertEquals("dt/jugadoresDt",vista);
//    }
//
//
//
//
//    public static Jugador generarJugador() {
//        Jugador jugador = new Jugador();
//
//        String nombre = "Kevin";
//        String fechaNacimiento = "12/07/2003";
//        String posicion = "Delantero";
//        String dni = "45285663";
//        PartidosDeBsAs partidosDeBsAs = PartidosDeBsAs.LA_MATANZA;
//
//        jugador.setNombre(nombre);
//        jugador.setFechaNacimiento(fechaNacimiento);
//        jugador.setPosicion(posicion);
//        jugador.setDni(dni);
//        jugador.setPartido(partidosDeBsAs);
//        return jugador;
//    }
//
//
//
//
//}
