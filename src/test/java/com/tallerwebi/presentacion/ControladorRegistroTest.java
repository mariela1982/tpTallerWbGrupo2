package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ControladorRegistroTest {
    /*
    1-usuario necesita mail y password 
     */
// tarea validar pasword
    // tarea validar pasword doble

    ControladorRegistro controladorRegistro = new ControladorRegistro();
    @Test 
    public void siExistePassworYcontraseniaElRegistroEsExitoso() {
        // preparacion -> given
        givenNoExisteUsuario();
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario("mari@gmil","mariela");
        //comprobacion ->then
        thenElRegistroEsExitoso(mav);
        
    }

    private ModelAndView whenRegistroUsuario(String email, String password) {
         ModelAndView mav = controladorRegistro.registrar(email,password);
         return mav;
    }

    private void givenNoExisteUsuario() {
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        //comparo la vista, si esta ok vuelve a login
        assertThat(mav.getViewName(),equalToIgnoringCase("redirect:/login"));

    }

    @Test
    public void siElEmailEstaVacioElRegistroFalla() {
        // preparacion -> given
        givenNoExisteUsuario();
        String emailvacio ="";
        String contrasenia = "lala";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(emailvacio,contrasenia);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));

    }
    @Test
    public void siLaContraseniaEstaVaciaElRegistroFalla() {
        // preparacion -> given
        givenNoExisteUsuario();
        String emailvacio ="";
        String contrasenia ="";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(emailvacio,contrasenia);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }
    @Test
    public void siLaContraseniaEsDistintaElRegistroFalla() {
        // preparacion -> given
        givenNoExisteUsuario();
        String emailvacio ="";
        String contrasenia ="";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(emailvacio,contrasenia);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }



}
