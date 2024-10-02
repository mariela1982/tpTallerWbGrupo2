package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.DniInvalidoException;
import com.tallerwebi.dominio.excepcion.NombreInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/jugadorFormulario")
public class ControladorJugador {


    ServicioJugador servicioJugador;

    public ControladorJugador(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
    }

    @PostMapping("/crearJugador")
    public String crearJugador(@ModelAttribute Jugador jugador, Model model) throws NombreInvalidoException, DniInvalidoException {

        servicioJugador.guardar(jugador);

        model.addAttribute("mensajeExito", "Jugador registrado exitosamente.");

        return "dt/jugadoresDt";
    }

    @GetMapping("/jugadores")
    public ModelAndView mostrarJugadores() {
        List<Jugador> jugadores = servicioJugador.obtenerTodos();
        System.out.println("Jugadores en el controlador: " + jugadores); // Para depuración
        ModelAndView mav = new ModelAndView("dt/jugadoresDt");
        mav.addObject("jugadores", jugadores);
        return mav;
    }



}
