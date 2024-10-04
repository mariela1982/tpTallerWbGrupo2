package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.excepcion.EquipoExistente;

@Controller
@RequestMapping("/equipos")
public class ControladorEquipo {

    ServicioEquipo servicioEquipo;

    @Autowired
    public ControladorEquipo(ServicioEquipo servicioEquipo){
        this.servicioEquipo = servicioEquipo;
    }



    @PostMapping("/crearEquipo")
    public ModelAndView registrar( @RequestParam String nombre,
                                   @RequestParam String numeroCbu,
                                   @RequestParam Long dtDni) {


        if(nombre.isEmpty() || numeroCbu.isEmpty() || dtDni == null){
            ModelMap modelo = new ModelMap();
            modelo.put("error","Completa los campos");
        }

        try{
            servicioEquipo.registrar(nombre, numeroCbu, dtDni);

        }
        catch (EquipoExistente ex){
            ModelMap modelo = new ModelMap();
            modelo.put("error","El equipo ya existe");
            return new ModelAndView("formularioCreacionEquipo",modelo);
        }


        return new ModelAndView("redirect:/home");
    }

    }

