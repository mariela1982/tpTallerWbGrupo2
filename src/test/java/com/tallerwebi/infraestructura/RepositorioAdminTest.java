package com.tallerwebi.infraestructura;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebTestConfig.class, HibernateTestConfig.class })
public class RepositorioAdminTest {
    @Autowired
    RepositorioAdmin repositorioAdmin;

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerTorneos() {
        // Preparación
        Torneo torneo1 = new Torneo();
        torneo1.setNombre("Torneo de prueba 1");
        Torneo torneo2 = new Torneo();
        torneo2.setNombre("Torneo de prueba 2");
        repositorioAdmin.guardarTorneo(torneo1);
        repositorioAdmin.guardarTorneo(torneo2);

        // Ejecución
        List<Torneo> torneos = repositorioAdmin.obtenerTorneos();

        // Validación
        assertNotNull(torneos);
        assertEquals(2, torneos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarTorneoPorNombre() {
        // Preparación
        Torneo torneo = new Torneo();
        torneo.setNombre("Torneo de prueba");
        repositorioAdmin.guardarTorneo(torneo);

        // Ejecución
        Torneo torneoBuscado = repositorioAdmin.buscarTorneoPorNombre("Torneo de prueba");

        // Validación
        assertNotNull(torneoBuscado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarCancha() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setNombre("Cancha de prueba");

        // Ejecución
        repositorioAdmin.guardarCancha(cancha);

        // Validación
        Cancha canchaGuardada = repositorioAdmin.obtenerCanchaPorId(cancha.getId());
        assertNotNull(canchaGuardada);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerCanchas() {
        // Preparación
        Cancha cancha1 = new Cancha();
        cancha1.setNombre("Cancha de prueba 1");
        Cancha cancha2 = new Cancha();
        cancha2.setNombre("Cancha de prueba 2");
        repositorioAdmin.guardarCancha(cancha1);
        repositorioAdmin.guardarCancha(cancha2);

        // Ejecución
        List<Cancha> canchas = repositorioAdmin.obtenerCanchas();

        // Validación
        assertNotNull(canchas);
        assertEquals(2, canchas.size());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerCanchaPorId() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setNombre("Cancha de prueba");
        repositorioAdmin.guardarCancha(cancha);

        // Ejecución
        Cancha canchaObtenida = repositorioAdmin.obtenerCanchaPorId(cancha.getId());

        // Validación
        assertNotNull(canchaObtenida);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarCancha() {
        // Preparación
        Cancha cancha = new Cancha();
        cancha.setNombre("Cancha de prueba");
        repositorioAdmin.guardarCancha(cancha);

        // Ejecución
        repositorioAdmin.eliminarCancha(cancha);

        // Validación
        Cancha canchaEliminada = repositorioAdmin.obtenerCanchaPorId(cancha.getId());
        assertNull(canchaEliminada);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarArbitro() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre("Arbitro de prueba");

        // Ejecución
        repositorioAdmin.guardarArbitro(arbitro);

        // Validación
        Arbitro arbitroGuardado = repositorioAdmin.obtenerArbitroPorId(arbitro.getId());
        assertNotNull(arbitroGuardado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerArbitros() {
        // Preparación
        Arbitro arbitro1 = new Arbitro();
        arbitro1.setNombre("Arbitro de prueba 1");
        Arbitro arbitro2 = new Arbitro();
        arbitro2.setNombre("Arbitro de prueba 2");
        repositorioAdmin.guardarArbitro(arbitro1);
        repositorioAdmin.guardarArbitro(arbitro2);

        // Ejecución
        List<Arbitro> arbitros = repositorioAdmin.obtenerArbitros();

        // Validación
        assertNotNull(arbitros);
        assertEquals(2, arbitros.size());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerArbitroPorId() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre("Arbitro de prueba");
        repositorioAdmin.guardarArbitro(arbitro);

        // Ejecución
        Arbitro arbitroObtenido = repositorioAdmin.obtenerArbitroPorId(arbitro.getId());

        // Validación
        assertNotNull(arbitroObtenido);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarArbitro() {
        // Preparación
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre("Arbitro de prueba");
        repositorioAdmin.guardarArbitro(arbitro);

        // Ejecución
        repositorioAdmin.eliminarArbitro(arbitro);

        // Validación
        Arbitro arbitroEliminado = repositorioAdmin.obtenerArbitroPorId(arbitro.getId());
        assertNull(arbitroEliminado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarPartido() {
        // Preparación
        Partido partido = new Partido();
        Date fecha = Date.valueOf(LocalDate.of(2024, 10, 10));
        partido.setFecha(fecha);

        // Ejecución
        repositorioAdmin.guardarPartido(partido);

        // Validación
        Partido partidoGuardado = repositorioAdmin.obtenerPartidoPorId(partido.getId());
        assertNotNull(partidoGuardado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerPartidos() {
        // Preparación
        Partido partido1 = new Partido();
        Date fecha1 = Date.valueOf(LocalDate.of(2024, 10, 10));
        partido1.setFecha(fecha1);
        Partido partido2 = new Partido();
        Date fecha2 = Date.valueOf(LocalDate.of(2024, 10, 11));
        partido2.setFecha(fecha2);
        repositorioAdmin.guardarPartido(partido1);
        repositorioAdmin.guardarPartido(partido2);

        // Ejecución
        List<Partido> partidos = repositorioAdmin.obtenerPartidos();

        // Validación
        assertNotNull(partidos);
        assertEquals(2, partidos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerPartidoPorId() {
        // Preparación
        Partido partido = new Partido();
        Date fecha = Date.valueOf(LocalDate.of(2024, 10, 10));
        partido.setFecha(fecha);
        repositorioAdmin.guardarPartido(partido);

        // Ejecución
        Partido partidoObtenido = repositorioAdmin.obtenerPartidoPorId(partido.getId());

        // Validación
        assertNotNull(partidoObtenido);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarPartido() {
        // Preparación
        Partido partido = new Partido();
        Date fecha = Date.valueOf(LocalDate.of(2024, 10, 10));
        partido.setFecha(fecha);
        repositorioAdmin.guardarPartido(partido);

        // Ejecución
        repositorioAdmin.eliminarPartido(partido);

        // Validación
        Partido partidoEliminado = repositorioAdmin.obtenerPartidoPorId(partido.getId());
        assertNull(partidoEliminado);
    }

}
