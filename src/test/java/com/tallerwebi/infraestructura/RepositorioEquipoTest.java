package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioEquipo;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioEquipoTest {


    // Inyectamos el repositorio y session ambos reales (no mock)


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioEquipo repositorioEquipo;

    String nombreEquipo = "Los salchipapas";
    Long dtDni = 45285663L;
    String cbu = "4934607627390389151791";


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnEquipo() {
        Equipo equipo = new Equipo();
        equipo.setNombre(nombreEquipo);
//        equipo.setDtDni(dtDni);
//        equipo.setCbu(cbu);

        repositorioEquipo.guardar(equipo);

        assertThat(equipo.getId(),notNullValue());





    }



    @Test
    @Transactional
    @Rollback
    public void buscarEquipoPorNombreEquipoPorNombre(){
        Equipo equipo = new Equipo();
        equipo.setNombre(nombreEquipo);
//        equipo.setDtDni(dtDni);
//        equipo.setCbu(cbu);
        sessionFactory.getCurrentSession().saveOrUpdate(equipo);


        Equipo buscado = repositorioEquipo.buscarEquipoPorNombre(nombreEquipo);

        assertThat(buscado,notNullValue());
     //   assertThat(buscado.getCbu(), equalToIgnoringCase("4934607627390389151791"));
    }


}
