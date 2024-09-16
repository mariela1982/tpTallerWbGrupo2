package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {
    public ModelAndView registrar(String email, String password,  String contrasenia2) {

       if(email.isEmpty()  || password.isEmpty()){
           ModelMap modelo = new ModelMap();
           modelo.put("error","El mail y la contraseña es obligatorio");
           return new ModelAndView("registro",modelo);
       }
       if(!password.equals(contrasenia2)){
           ModelMap modelo = new ModelMap();
           modelo.put("error","contraseñas diferentes");
           return new ModelAndView("registro",modelo);

       }
        return new ModelAndView("redirect:/login");

    }
}
