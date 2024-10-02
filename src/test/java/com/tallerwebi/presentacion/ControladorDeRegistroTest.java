package com.tallerwebi.presentacion;

import org.dom4j.rule.Mode;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ControladorDeRegistroTest {


    String email = "kvnrotela@gmail.com";
    String contrasenia = "Kevin_2024";
    ControladorRegistro controladorRegistro = new ControladorRegistro();

    @Test
    public void siExisteEmailYPasswordElRegistroEsExitoso() {

        //preparacion -> given
        givenNoExisteUsuario();
        //ejecucion -> when
        ModelAndView mav = whenRegistroUsuario(email,contrasenia,contrasenia);
        //comprobacion -> then
        thenElRegistroEsExitoso(mav);



    }


    private void givenNoExisteUsuario() {
    }

    private ModelAndView whenRegistroUsuario(String email,String contrasenia,String contrasenia2) {
        ModelAndView mav = controladorRegistro.registrar(email,contrasenia,contrasenia2);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void siElEmailEstaVacioElRegistroFalla() {
        //preparacion -> given
        givenNoExisteUsuario();
        //ejecucion -> when
        String emailVacio = "";
        ModelAndView mav = whenRegistroUsuario(emailVacio, contrasenia,contrasenia);
        //comprobacion -> then
        thenElRegistroFallaPorEmailVacio(mav);
    }

    private void thenElRegistroFallaPorEmailVacio(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));

        assertThat(mav.getModel().get("error").toString(),equalToIgnoringCase("el email es obligatorio"));

    }





    @Test
    public void siLaContraseniaEstaVaciaElRegistroFalla() {
        //preparacion -> given
        givenNoExisteUsuario();
        //ejecucion -> when
        String contraseniaVacia = "";
        ModelAndView mav = whenRegistroUsuario(email,contraseniaVacia,contraseniaVacia);
        //comprobacion -> then
        thenElRegistroFallaPorContraseniaVacia(mav);

    }

    private void thenElRegistroFallaPorContraseniaVacia(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(),equalToIgnoringCase("la contrasenia es obligatoria"));

    }

    @Test
    public void siLasPasswordSonDistintasElRegistroFalla() {
        //preparacion -> given
        givenNoExisteUsuario();

        //ejecucion -> when

        String contrasenia2 = "jijo";

        ModelAndView mav = whenRegistroUsuario(email,contrasenia,contrasenia2);

        thenElRegistroFallaPorNoCoincidenciaDeContrasenia(mav);

    }

    @Test
    public void siLosCamposEstanVaciosElRegistroFalla(){
        givenNoExisteUsuario();

       ModelAndView mav =  whenRegistroUsuario("","","");

        thenElRegistroFallaPorFaltaDeDatos(mav);
    }

    private void thenElRegistroFallaPorFaltaDeDatos(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(),equalToIgnoringCase("completa los campos"));


    }

    private void thenElRegistroFallaPorNoCoincidenciaDeContrasenia(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(),equalToIgnoringCase("la contrasenia no coinciden"));

    }


}
