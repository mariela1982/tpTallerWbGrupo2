package com.tallerwebi.presentacion;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioAdmin;
import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;

public class ControladorAdminTest {
    private ControladorAdmin controladorAdmin;
    private ServicioAdmin servicioAdmin;
    private ServicioEquipo servicioEquipo;
    private ServicioJugador servicioJugador;

    @BeforeEach
    public void init() {
        this.servicioAdmin = mock(ServicioAdmin.class);
        this.servicioEquipo = mock(ServicioEquipo.class);
        this.servicioJugador = mock(ServicioJugador.class);
        this.controladorAdmin = new ControladorAdmin(servicioJugador, servicioEquipo, servicioAdmin);
    }

    @Test
    public void testRedirigirAPanel() {
        ModelAndView mav = controladorAdmin.redirigirAPanel();
        assertThat(mav.getViewName(), is("redirect:/admin/panel"));
    }

    @Test
    public void testPanelAdmin() {
        ModelAndView mav = controladorAdmin.panelAdmin();
        assertThat(mav.getViewName(), is("admin/panel"));
    }

    @Test
    public void testGestionTorneos() {
        ModelAndView mav = controladorAdmin.gestionTorneos();
        assertThat(mav.getViewName(), is("admin/torneos"));
    }

    @Test
    public void testCrearTorneo() {
        Torneo torneo = new Torneo();
        ModelAndView mav = controladorAdmin.crearTorneo(torneo);
        assertThat(mav.getViewName(), is("redirect:/admin/torneos?creado=true"));
    }

    @Test
    public void testEliminarTorneo() {
        Long id = 1L;
        Torneo torneo = new Torneo();
        when(servicioAdmin.buscarTorneoPorId(id)).thenReturn(torneo);
        ModelAndView mav = controladorAdmin.eliminarTorneo(id);
        assertThat(mav.getViewName(), is("redirect:/admin/torneos?eliminado=true"));
    }

    @Test
    public void testMostrarFormularioEdicionTorneo() {
        Long id = 1L;
        Torneo torneo = new Torneo();
        when(servicioAdmin.buscarTorneoPorId(id)).thenReturn(torneo);
        ModelAndView mav = controladorAdmin.mostrarFormularioEdicionTorneo(id);
        assertThat(mav.getViewName(), is("admin/torneos"));
    }

    @Test
    public void testEditarTorneo() {
        Torneo torneo = new Torneo();
        ModelAndView mav = controladorAdmin.editarTorneo(torneo);
        assertThat(mav.getViewName(), is("redirect:/admin/torneos?editado=true"));
    }

    @Test
    public void testGestionCanchas() {
        ModelAndView mav = controladorAdmin.gestionCanchas();
        assertThat(mav.getViewName(), is("admin/canchas"));
    }

    @Test
    public void testCrearCancha() {
        Cancha cancha = new Cancha();
        ModelAndView mav = controladorAdmin.crearCancha(cancha);
        assertThat(mav.getViewName(), is("redirect:/admin/canchas?creada=true"));
    }

    @Test
    public void testEditarCancha() {
        Cancha cancha = new Cancha();
        ModelAndView mav = controladorAdmin.editarCancha(cancha);
        assertThat(mav.getViewName(), is("redirect:/admin/canchas?editada=true"));
    }

    @Test
    public void testGestionArbitros() {
        ModelAndView mav = controladorAdmin.gestionArbitros();
        assertThat(mav.getViewName(), is("admin/arbitros"));
    }

    @Test
    public void testCrearArbitro() {
        Arbitro arbitro = new Arbitro();
        ModelAndView mav = controladorAdmin.crearArbitro(arbitro);
        assertThat(mav.getViewName(), is("redirect:/admin/arbitros?creado=true"));
    }

    @Test
    public void testEditarArbitro() {
        Arbitro arbitro = new Arbitro();
        ModelAndView mav = controladorAdmin.editarArbitro(arbitro);
        assertThat(mav.getViewName(), is("redirect:/admin/arbitros?editado=true"));
    }

    @Test
    public void testGestionPartidos() {
        ModelAndView mav = controladorAdmin.gestionPartidos();
        assertThat(mav.getViewName(), is("admin/partidos"));
    }

    @Test
    public void testVerPartido() {
        Long id = 1L;
        Partido partido = new Partido();
        when(servicioAdmin.obtenerPartidoPorId(id)).thenReturn(partido);
        ModelAndView mav = controladorAdmin.verPartido(id);
        assertThat(mav.getViewName(), is("admin/partido"));
    }

    @Test
    public void testJugarPartido() {
        Long id = 1L;
        Partido partido = new Partido();
        when(servicioAdmin.obtenerPartidoPorId(id)).thenReturn(partido);
        ModelAndView mav = controladorAdmin.jugarPartido(id);
        assertThat(mav.getViewName(), is("redirect:/admin/partidos/" + id + "?sinEquipoVisitante=true"));
    }

    @Test
    public void testCompletarPartido() {
        Long id = 1L;
        Partido partido = new Partido();
        when(servicioAdmin.obtenerPartidoPorId(id)).thenReturn(partido);
        ModelAndView mav = controladorAdmin.completarPartido(id, null, null, null, null);
        assertThat(mav.getViewName(), is("redirect:/admin/partidos"));
    }

    @Test
    public void testCompletarResultado() {
        Long id = 1L;
        Partido partido = new Partido();
        when(servicioAdmin.obtenerPartidoPorId(id)).thenReturn(partido);
        ModelAndView mav = controladorAdmin.completarResultado(id, null, null);
        assertThat(mav.getViewName(), is("redirect:/admin/partidos/" + id + "?sinEquipoVisitante=true"));
    }

    @Test
    public void testGestionSanciones() {
        ModelAndView mav = controladorAdmin.gestionSanciones();
        assertThat(mav.getViewName(), is("admin/sanciones"));
    }

    @Test
    public void testObtenerJugadoresPorEquipo() throws EquipoInexistente {
        Long id = 1L;
        Equipo equipo = new Equipo();
        List<Jugador> jugadores = new ArrayList<>();
        when(servicioEquipo.buscarEquipoPorId(id)).thenReturn(equipo);
        when(servicioJugador.obtenerJugadoresPorEquipo(equipo)).thenReturn(jugadores);
        assertNotNull(controladorAdmin.obtenerJugadoresPorEquipo(id));
    }

    @Test
    public void testAsignarSancion() throws JugadorInexistente, EquipoInexistente, JugadorExistente {
        Long equipoId = 1L;
        Long jugadorId = 1L;
        String sancion = "Amarilla";
        Equipo equipo = new Equipo();
        Jugador jugador = new Jugador();
        when(servicioEquipo.buscarEquipoPorId(equipoId)).thenReturn(equipo);
        when(servicioJugador.buscarJugador(jugadorId)).thenReturn(jugador);
        ModelAndView mav = controladorAdmin.asignarSancion(equipoId, jugadorId, sancion);
        assertThat(mav.getViewName(), is("redirect:/admin/sanciones?asignada=true"));
    }

    @Test
    public void testGestionResultados() {
        ModelAndView mav = controladorAdmin.gestionResultados();
        assertThat(mav.getViewName(), is("admin/resultados"));
    }

    @Test
    public void testHistorialTransacciones() {
        ModelAndView mav = controladorAdmin.historialTransacciones();
        assertThat(mav.getViewName(), is("admin/transacciones"));
    }

}
