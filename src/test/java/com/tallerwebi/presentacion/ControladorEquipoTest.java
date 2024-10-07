//package com.tallerwebi.presentacion;
//
////import com.tallerwebi.dominio.ControladorEquipo;
//import com.tallerwebi.dominio.ServicioEquipo;
//import com.tallerwebi.dominio.excepcion.EquipoExistente;
//import com.tallerwebi.infraestructura.ServicioEquipoImpl;
//import org.testng.annotations.Test;
//import org.springframework.web.servlet.ModelAndView;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalToIgnoringCase;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class ControladorEquipoTest {
//
//    String nombreEquipo = "Los salchipapas";
//    Long dtDni = 45285663L;
//    String cbu = "4934607627390389151791";
//
//    ServicioEquipo servicioEquipo = mock(ServicioEquipoImpl.class);
//    ControladorEquipo controladorEquipo = new ControladorEquipo(servicioEquipo);
//
//
//
//    @Test
//    public void siExisteNombreDniYCbuElRegistroEsExitoso(){
//
//        ModelAndView mav = whenRegistroEquipo(nombreEquipo,cbu,dtDni);
//        thenRegistroEsExitoso(mav);
//
//    }
//
//    private void thenRegistroEsExitoso(ModelAndView mav) {
//        assertThat(mav.getViewName(),equalToIgnoringCase("redirect:/home"));
//    }
//
//    private ModelAndView whenRegistroEquipo(String nombreEquipo, String cbu, Long dtDni) {
//        return controladorEquipo.registrar(nombreEquipo,cbu,dtDni);
//    }
//
//
//    @Test
//    public void siExisteEquipoConNombreDelRegistroElRegistroFalla() throws EquipoExistente {
//
//        when(servicioEquipo.guardarEquipo(nombreEquipo,cbu,dtDni)).thenThrow(EquipoExistente.class);
//
//        ModelAndView mav = whenRegistroEquipo(nombreEquipo,cbu,dtDni);
//        thenRegistroFalla(mav,"El equipo ya existe");
//
//    }
//
//    private void thenRegistroFalla(ModelAndView mav, String mensaje) {
//        assertThat(mav.getViewName(),equalToIgnoringCase("formularioCreacionEquipo"));
//        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase(mensaje));
//
//    }
//}
