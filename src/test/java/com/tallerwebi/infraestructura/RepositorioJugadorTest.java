//package com.tallerwebi.infraestructura;
//
//import com.tallerwebi.dominio.Jugador;
//import com.tallerwebi.dominio.RepositorioEquipo;
//import com.tallerwebi.dominio.RepositorioJugador;
//import com.tallerwebi.integracion.config.HibernateTestConfig;
//import com.tallerwebi.integracion.config.SpringWebTestConfig;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//import static com.tallerwebi.presentacion.ControladorJugadorTest.generarJugador;
//import static junit.framework.TestCase.assertEquals;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
//public class RepositorioJugadorTest {
//
//    @Mock
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    RepositorioJugador repositorioJugador;
//
//
//    @Test
//    @Transactional
//    @Rollback
//    public void puedoGuardarUnJugador() {
//
//        Jugador jugador = generarJugador();
//
//        repositorioJugador.guardar(jugador);
//
//        assertThat(jugador.getId(),notNullValue());
//
//    }
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void testObtenerTodos() {
//        Jugador jugador1 = new Jugador();
//        jugador1.setNombre("Jugador 1");
//        jugador1.setPosicion("Delantero");
//
//        Jugador jugador2 = new Jugador();
//        jugador2.setNombre("Jugador 2");
//        jugador2.setPosicion("Defensa");
//
//        repositorioJugador.guardar(jugador1);
//        repositorioJugador.guardar(jugador2);
//
//        List<Jugador> jugadores = repositorioJugador.findAll();
//
//        Assertions.assertEquals(2, jugadores.size());
//        Assertions.assertEquals("Jugador 1", jugadores.get(0).getNombre());
//        Assertions.assertEquals("Jugador 2", jugadores.get(1).getNombre());
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback
//    public void obtenerPorDni(){
//        Jugador jugador1 = new Jugador();
//        jugador1.setNombre("Kevin");
//        jugador1.setDni("45285663");
//
//        repositorioJugador.guardar(jugador1);
//
//        Jugador jugadorObtenido = repositorioJugador.obtenerPorDni("45285663");
//
//        Assertions.assertEquals("Kevin", jugadorObtenido.getNombre());
//
//    }
//
//    }
//
//
