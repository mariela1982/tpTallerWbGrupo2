package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CamposVacios;
import com.tallerwebi.dominio.excepcion.EquipoExistente;
import com.tallerwebi.infraestructura.ServicioEquipoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

