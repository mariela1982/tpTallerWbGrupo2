package com.tallerwebi.dominio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.excepcion.TorneoExistente;
import com.tallerwebi.infraestructura.ServicioAdminImpl;

public class ServicioAdminTest {
    RepositorioAdmin repositorioAdmin = mock(RepositorioAdmin.class);
    ServicioAdmin servicioAdmin = new ServicioAdminImpl(repositorioAdmin);

    @Test
    public void queSePuedaGuardarTorneo() throws TorneoExistente {
        // Preparación
        Torneo torneo = new Torneo();
        torneo.setNombre("Torneo de prueba");

        // Ejecución
        when(repositorioAdmin.buscarTorneoPorNombre("Torneo de prueba")).thenReturn(null);
        servicioAdmin.guardarTorneo(torneo);

        // Validación
        verify(repositorioAdmin, times(1)).guardarTorneo(torneo);
    }

    @Test
    public void queNoSePuedaGuardarTorneoExistente() {
        // Preparación
        Torneo torneo = new Torneo();
        torneo.setNombre("Torneo de prueba");
        torneo.setId(1L);

        Torneo torneoExistente = new Torneo();
        torneoExistente.setNombre("Torneo de prueba");
        torneoExistente.setId(2L);

        // Ejecución
        when(repositorioAdmin.buscarTorneoPorNombre("Torneo de prueba")).thenReturn(torneoExistente);

        // Validación
        assertThrows(TorneoExistente.class, () -> {
            servicioAdmin.guardarTorneo(torneo);
        });
    }
    
    @Test
    public void queSePuedaActualizarTorneo() {
        // Preparación
        Torneo torneo = new Torneo();
        torneo.setNombre("Torneo de prueba");

        // Ejecución
        servicioAdmin.actualizarTorneo(torneo);

        // Validación
        verify(repositorioAdmin, times(1)).guardarTorneo(torneo);
    }

    @Test
    public void queSePuedaObtenerTorneos() {
        // Preparación
        List<Torneo> torneos = List.of(new Torneo(), new Torneo(), new Torneo());

        // Ejecución
        when(repositorioAdmin.obtenerTorneos()).thenReturn(torneos);

        // Validación
        assertEquals(torneos, servicioAdmin.obtenerTorneos());
    }

    @Test
    public void queSePuedaBuscarTorneoPorId() {
        // Preparación
        Torneo torneo = new Torneo();
        torneo.setId(1L);

        // Ejecución
        when(repositorioAdmin.obtenerTorneoPorId(1L)).thenReturn(torneo);

        // Validación
        assertEquals(torneo, servicioAdmin.buscarTorneoPorId(1L));
    }

    @Test
    public void queSePuedaGuardarCancha() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setNombre("Cancha de prueba");

        // Ejecución
        servicioAdmin.guardarCancha(cancha);

        // Validación
        verify(repositorioAdmin, times(1)).guardarCancha(cancha);
    }

    @Test
    public void queSePuedaEliminarCancha() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setNombre("Cancha de prueba");

        // Ejecución
        servicioAdmin.elimiarCancha(cancha);

        // Validación
        verify(repositorioAdmin, times(1)).eliminarCancha(cancha);
    }

    @Test
    public void queSePuedaObtenerCanchas() {
        // Preparación
        List<Cancha> canchas = List.of(new Cancha(), new Cancha(), new Cancha());

        // Ejecución
        when(repositorioAdmin.obtenerCanchas()).thenReturn(canchas);

        // Validación
        assertEquals(canchas, servicioAdmin.obtenerCanchas());
    }

    @Test
    public void queSePuedaObtenerCanchaPorId() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setId(1L);

        // Ejecución
        when(repositorioAdmin.obtenerCanchaPorId(1L)).thenReturn(cancha);

        // Validación
        assertEquals(cancha, servicioAdmin.obtenerCanchaPorId(1L));
    }

    @Test
    public void queSePuedaGuardarArbitro() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre("Arbitro de prueba");

        // Ejecución
        servicioAdmin.guardarArbitro(arbitro);

        // Validación
        verify(repositorioAdmin, times(1)).guardarArbitro(arbitro);
    }

    @Test
    public void queSePuedaEliminarArbitro() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre("Arbitro de prueba");

        // Ejecución
        servicioAdmin.eliminarArbitro(arbitro);

        // Validación
        verify(repositorioAdmin, times(1)).eliminarArbitro(arbitro);
    }

    @Test
    public void queSePuedaObtenerArbitros() {
        // Preparación
        List<Arbitro> arbitros = List.of(new Arbitro(), new Arbitro(), new Arbitro());

        // Ejecución
        when(repositorioAdmin.obtenerArbitros()).thenReturn(arbitros);

        // Validación
        assertEquals(arbitros, servicioAdmin.obtenerArbitros());
    }


    @Test
    public void queSePuedaObtenerArbitroPorId() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setId(1L);

        // Ejecución
        when(repositorioAdmin.obtenerArbitroPorId(1L)).thenReturn(arbitro);

        // Validación
        assertEquals(arbitro, servicioAdmin.obtenerArbitroPorId(1L));
    }

    @Test
    public void queSePuedaGuardarPartido() {
        // Preparación
        Partido partido = new Partido();

        // Ejecución
        servicioAdmin.guardarPartido(partido);

        // Validación
        verify(repositorioAdmin, times(1)).guardarPartido(partido);
    }

    @Test
    public void queSePuedaObtenerPartidos() {
        // Preparación
        List<Partido> partidos = List.of(new Partido(), new Partido(), new Partido());

        // Ejecución
        when(repositorioAdmin.obtenerPartidos()).thenReturn(partidos);

        // Validación
        assertEquals(partidos, servicioAdmin.obtenerPartidos());
    }

    @Test
    public void queSePuedaObtenerPartidosPorTorneo() {
        // Preparación
        Torneo torneo = new Torneo();
        List<Partido> partidos = List.of(new Partido(), new Partido(), new Partido());

        // Ejecución
        when(repositorioAdmin.obtenerPartidosPorTorneo(torneo)).thenReturn(partidos);

        // Validación
        assertEquals(partidos, servicioAdmin.obtenerPartidosPorTorneo(torneo));
    }

    @Test
    public void queSePuedaObtenerPartidoPorId() {
        // Preparación
        Partido partido = new Partido();
        partido.setId(1L);

        // Ejecución
        when(repositorioAdmin.obtenerPartidoPorId(1L)).thenReturn(partido);

        // Validación
        assertEquals(partido, servicioAdmin.obtenerPartidoPorId(1L));
    }

    @Test
    public void queSePuedaObtenerPartidoEsperandoRival() {
        // Preparación
        Torneo torneo = new Torneo();
        String fase = "Semifinal";
        Partido partido = new Partido();

        // Ejecución
        when(repositorioAdmin.obtenerPartidoEsperandoRival(torneo, fase)).thenReturn(partido);

        // Validación
        assertEquals(partido, servicioAdmin.obtenerPartidoEsperandoRival(torneo, fase));
    }
}
