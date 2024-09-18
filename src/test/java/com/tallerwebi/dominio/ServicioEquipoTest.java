package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CamposVacios;
import com.tallerwebi.dominio.excepcion.EquipoExistente;
import com.tallerwebi.infraestructura.RepositorioEquipoImpl;
import com.tallerwebi.infraestructura.ServicioEquipoImpl;
import org.junit.Test;

import java.time.Clock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioEquipoTest {

    RepositorioEquipo repositorioEquipo = mock(RepositorioEquipoImpl.class);
    ServicioEquipo servicioEquipo = new ServicioEquipoImpl(repositorioEquipo);


    String nombreEquipo = "Los Salchipapas";

    String cbu = "4934607627390389151791";

    Long dtDni = 45285663l;
    @Test
    public void siExisteNombreCbuYDtDniElRegistroEsExitoso() throws EquipoExistente {
        Equipo equipoCreado = whenRegistroEquipo("Los Salchipapas","4934607627390389151791",45285663L);
        thenElRegistroEsExitoso(equipoCreado);
    }




    private void thenElRegistroEsExitoso(Equipo equipoCreado) {
        assertThat(equipoCreado,notNullValue());
        verify(repositorioEquipo,times(1)).guardar(equipoCreado);
    }

    private Equipo whenRegistroEquipo(String nombre, String cbu, Long dtDni) throws EquipoExistente {
        return servicioEquipo.registrar(nombre,cbu,dtDni);
    }


    @Test
    public void siYaExisteEquipoConElMismoNombreElRegistroFalla() throws EquipoExistente {


        //registramos el equipo por primera vez
        givenExisteEquipo(nombreEquipo,cbu,dtDni);


        //
        when(repositorioEquipo.buscar(nombreEquipo)).thenReturn(new Equipo());

        Equipo equipoCreado = whenRegistroEquipo(nombreEquipo,cbu,dtDni);

        thenElRegistroFalla(equipoCreado);
    }

    private void thenElRegistroFalla(Equipo equipoCreado) {
        assertThat(equipoCreado,nullValue());

    }

    private void givenExisteEquipo(String nombreEquipo, String cbu, Long dtDni) throws EquipoExistente {
        whenRegistroEquipo(nombreEquipo,cbu,dtDni);
    }
}
