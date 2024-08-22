package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {
    public ModelAndView registrar(String email, String password) {

       if(email.isEmpty()  || password.isEmpty()){
           return new ModelAndView("registro");
       }
        return new ModelAndView("redirect:/login");

    }
}
