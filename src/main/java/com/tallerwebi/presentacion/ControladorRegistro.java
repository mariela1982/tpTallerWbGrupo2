package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    public ModelAndView registrar(String email,String contrasenia,String contrasenia2) {
        return verificarDatos(email,contrasenia,contrasenia2);

    }

    private ModelAndView verificarDatos(String email,String contrasenia,String contrasenia2) {
        if(email.isEmpty() && contrasenia.isEmpty() && contrasenia2.isEmpty()) {
            ModelMap modelo = new ModelMap();
            modelo.put("error","completa los campos");
            return new ModelAndView("registro",modelo);
        }

        if(email.isEmpty()){
            ModelMap modelo = new ModelMap();
            modelo.put("error","el email es obligatorio");
            return new ModelAndView("registro",modelo);
        }

        if(contrasenia.isEmpty()){
            ModelMap modelo = new ModelMap();
            modelo.put("error","la contrasenia es obligatoria");
            return new ModelAndView("registro",modelo);
        }

        if(!(contrasenia.compareTo(contrasenia2)==0)){
            ModelMap modelo = new ModelMap();
            modelo.put("error","la contrasenia no coinciden");
            return new ModelAndView("registro",modelo);

        }

        return new ModelAndView("redirect:/login");

    }





}
