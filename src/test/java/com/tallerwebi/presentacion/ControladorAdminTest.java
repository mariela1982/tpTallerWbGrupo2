package com.tallerwebi.presentacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.Arbitro;
import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.Torneo;

public class ControladorAdminTest {

    @Mock
    private RepositorioAdmin repositorioAdmin;

    @InjectMocks
    private ControladorAdmin controladorAdmin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queSePuedaRedirigirAPanel() {
        // ejecucion
        ModelAndView mav = controladorAdmin.redirigirAPanel();

        // comprobacion
        assertEquals("redirect:/admin/panel", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarElPanelAdmin() {
        // ejecucion
        ModelAndView mav = controladorAdmin.panelAdmin();

        // comprobacion
        assertEquals("admin/panel", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeTorneos() {
        // preparacion
        when(repositorioAdmin.obtenerTorneos()).thenReturn(null);

        // ejecucion
        ModelAndView mav = controladorAdmin.gestionTorneos();

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerTorneos();
        assertEquals("admin/torneos", mav.getViewName());
    }

    @Test
    public void queSePuedaAgregarUnNuevoTorneo() {
        // preparacion
        Torneo torneo = new Torneo();
        when(repositorioAdmin.guardarTorneo(torneo)).thenReturn(torneo);

        // ejecucion
        ModelAndView mav = controladorAdmin.crearTorneo(torneo);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarTorneo(torneo);
        assertEquals("redirect:/admin/torneos?creado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEditarUnTorneo() {
        // preparacion
        Torneo torneo = new Torneo();
        when(repositorioAdmin.guardarTorneo(torneo)).thenReturn(torneo);

        // ejecucion
        ModelAndView mav = controladorAdmin.editarTorneo(torneo);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarTorneo(torneo);
        assertEquals("redirect:/admin/torneos?editado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEliminarUnTorneo() {
        // preparacion
        Torneo torneo = new Torneo();
        when(repositorioAdmin.obtenerTorneoPorId(1L)).thenReturn(torneo);

        // ejecucion
        ModelAndView mav = controladorAdmin.eliminarTorneo(1L);

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerTorneoPorId(1L);
        verify(repositorioAdmin, times(1)).eliminarTorneo(torneo);
        assertEquals("redirect:/admin/torneos?eliminado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeEquipos() {
        // preparacion
        when(repositorioAdmin.obtenerEquipos()).thenReturn(null);

        // ejecucion
        ModelAndView mav = controladorAdmin.gestionEquipos();

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerEquipos();
        assertEquals("admin/equipos", mav.getViewName());
    }

    @Test
    public void queSePuedaAgregarUnNuevoEquipo() {
        // preparacion
        Equipo equipo = new Equipo();
        when(repositorioAdmin.guardarEquipo(equipo)).thenReturn(equipo);

        // ejecucion
        ModelAndView mav = controladorAdmin.crearEquipo(equipo);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarEquipo(equipo);
        assertEquals("redirect:/admin/equipos?creado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeJugadores() {
        // preparacion
        when(repositorioAdmin.obtenerJugadores()).thenReturn(null);

        // ejecucion
        ModelAndView mav = controladorAdmin.gestionJugadores();

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerJugadores();
        assertEquals("admin/jugadores", mav.getViewName());
    }

    @Test
    public void queSePuedaAgregarUnNuevoJugador() {
        // preparacion
        Jugador jugador = new Jugador();
        when(repositorioAdmin.guardarJugador(jugador)).thenReturn(jugador);

        // ejecucion
        ModelAndView mav = controladorAdmin.crearJugador(jugador);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarJugador(jugador);
        assertEquals("redirect:/admin/jugadores?creado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEditarUnJugador() {
        // preparacion
        Jugador jugador = new Jugador();
        when(repositorioAdmin.guardarJugador(jugador)).thenReturn(jugador);

        // ejecucion
        ModelAndView mav = controladorAdmin.editarJugador(jugador);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarJugador(jugador);
        assertEquals("redirect:/admin/jugadores?editado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEliminarUnJugador() {
        // preparacion
        Jugador jugador = new Jugador();
        when(repositorioAdmin.obtenerJugadorPorId(1L)).thenReturn(jugador);

        // ejecucion
        ModelAndView mav = controladorAdmin.eliminarJugador(1L);

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerJugadorPorId(1L);
        verify(repositorioAdmin, times(1)).eliminarJugador(jugador);
        assertEquals("redirect:/admin/jugadores?eliminado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeCanchas() {
        // preparacion
        when(repositorioAdmin.obtenerCanchas()).thenReturn(null);

        // ejecucion
        ModelAndView mav = controladorAdmin.gestionCanchas();

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerCanchas();
        assertEquals("admin/canchas", mav.getViewName());
    }

    @Test
    public void queSePuedaAgregarUnaNuevaCancha() {
        // preparacion
        Cancha cancha = new Cancha();
        when(repositorioAdmin.guardarCancha(cancha)).thenReturn(cancha);

        // ejecucion
        ModelAndView mav = controladorAdmin.crearCancha(cancha);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarCancha(cancha);
        assertEquals("redirect:/admin/canchas?creada=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEditarUnaCancha() {
        // preparacion
        Cancha cancha = new Cancha();
        when(repositorioAdmin.guardarCancha(cancha)).thenReturn(cancha);

        // ejecucion
        ModelAndView mav = controladorAdmin.editarCancha(cancha);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarCancha(cancha);
        assertEquals("redirect:/admin/canchas?editada=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEliminarUnaCancha() {
        // preparacion
        Cancha cancha = new Cancha();
        when(repositorioAdmin.obtenerCanchaPorId(1L)).thenReturn(cancha);

        // ejecucion
        ModelAndView mav = controladorAdmin.eliminarCancha(1L);

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerCanchaPorId(1L);
        verify(repositorioAdmin, times(1)).eliminarCancha(cancha);
        assertEquals("redirect:/admin/canchas?eliminada=true", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeArbitros() {
        // preparacion
        when(repositorioAdmin.obtenerArbitros()).thenReturn(null);

        // ejecucion
        ModelAndView mav = controladorAdmin.gestionArbitros();

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerArbitros();
        assertEquals("admin/arbitros", mav.getViewName());
    }

    @Test
    public void queSePuedaAgregarUnNuevoArbitro() {
        // preparacion
        Arbitro arbitro = new Arbitro();
        when(repositorioAdmin.guardarArbitro(arbitro)).thenReturn(arbitro);

        // ejecucion
        ModelAndView mav = controladorAdmin.crearArbitro(arbitro);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarArbitro(arbitro);
        assertEquals("redirect:/admin/arbitros?creado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEditarUnArbitro() {
        // preparacion
        Arbitro arbitro = new Arbitro();
        when(repositorioAdmin.guardarArbitro(arbitro)).thenReturn(arbitro);

        // ejecucion
        ModelAndView mav = controladorAdmin.editarArbitro(arbitro);

        // comprobacion
        verify(repositorioAdmin, times(1)).guardarArbitro(arbitro);
        assertEquals("redirect:/admin/arbitros?editado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaEliminarUnArbitro() {
        // preparacion
        Arbitro arbitro = new Arbitro();
        when(repositorioAdmin.obtenerArbitroPorId(1L)).thenReturn(arbitro);

        // ejecucion
        ModelAndView mav = controladorAdmin.eliminarArbitro(1L);

        // comprobacion
        verify(repositorioAdmin, times(1)).obtenerArbitroPorId(1L);
        verify(repositorioAdmin, times(1)).eliminarArbitro(arbitro);
        assertEquals("redirect:/admin/arbitros?eliminado=true", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDePartidos() {
        // ejecucion
        ModelAndView mav = controladorAdmin.gestionPartidos();

        // comprobacion
        assertEquals("admin/partidos", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeSanciones() {
        // ejecucion
        ModelAndView mav = controladorAdmin.gestionSanciones();

        // comprobacion
        assertEquals("admin/sanciones", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarLaGestionDeResultados() {
        // ejecucion
        ModelAndView mav = controladorAdmin.gestionResultados();

        // comprobacion
        assertEquals("admin/resultados", mav.getViewName());
    }

    @Test
    public void queSePuedaMostrarElHistorialDeTransacciones() {
        // ejecucion
        ModelAndView mav = controladorAdmin.historialTransacciones();

        // comprobacion
        assertEquals("admin/transacciones", mav.getViewName());
    }
}
