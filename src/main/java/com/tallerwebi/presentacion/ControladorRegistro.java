package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    ServicioUsuario servicioUsuario;
    @Autowired
    public ControladorRegistro(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }
    public ModelAndView registrar(String email, String password,  String contrasenia2) {

       if(email.isEmpty()  || password.isEmpty()){
           ModelMap modelo = new ModelMap();
           modelo.put("error","El mail y la contraseña es obligatorio");
           return new ModelAndView("registro",modelo);
       }
//       if(!password.equals(contrasenia2)){
//           ModelMap modelo = new ModelMap();
//           modelo.put("error","contraseñas diferentes");
//           return new ModelAndView("registro",modelo);
//
//       }
        //la logica la voy a poner en el servicio
        try {
            servicioUsuario.registrar(email,"");
        } catch (UsuarioExistente ex){
            ModelMap modelo = new ModelMap();
            modelo.put("error","El email ya existe");
            return new ModelAndView("registro",modelo);
        } catch (PasswordLongitudIncorrecta e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/login");

    }
}
