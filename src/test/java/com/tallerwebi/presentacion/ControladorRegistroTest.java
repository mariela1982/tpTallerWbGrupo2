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
    public void siExistePassworYmailElRegistroEsExitoso() {
        // preparacion -> given
        givenNoExisteUsuario();
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario("mari@gmil","mariela", "mariela");
        //comprobacion ->then
        thenElRegistroEsExitoso(mav);
        
    }

    private ModelAndView whenRegistroUsuario(String email, String password, String contrasenia2) {
         ModelAndView mav = controladorRegistro.registrar(email,password,contrasenia2);
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
        String contrasenia2 = "lala";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(emailvacio,contrasenia, contrasenia2);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));
        if (mav.getModel().containsKey("error")) {
            assertThat(mav.getModel().get("error"), is(notNullValue()));
            if (mav.getModel().containsKey("message")) {
                assertThat(mav.getModel().get("message"), is(notNullValue()));
            }
        }
     //   assertThat(mav.getModel().get("error").toString(),equalToIgnoringCase("El mail y la contraseña es obligatorio"));

    }
    @Test
    public void siLaContraseniaEstaVaciaElRegistroFalla() {
        // preparacion -> given
        givenNoExisteUsuario();
        String emailvacio ="";
        String contrasenia ="";
        String contrasenia2 ="";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(emailvacio,contrasenia, contrasenia2);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }

    @Test
    public void siLaContraseniaEsDistintaElRegistroFalla() {
        // preparacion -> given
        givenNoExisteUsuario();
        String email ="casa@gmail.com";
        String contrasenia ="lala43";
        String contrasenia2 ="lala44";
        //ejecucion ->when
        ModelAndView mav = whenRegistroUsuario(email,contrasenia,contrasenia2);
        //comprobacion ->then
        thenElRegistroFalla(mav);

    }



}
