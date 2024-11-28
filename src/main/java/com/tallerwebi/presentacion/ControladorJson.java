package com.tallerwebi.presentacion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Usuario;

@RestController // Anotación para un controlador que maneja solicitudes REST
@RequestMapping("/api") // Agrupa tus endpoints bajo una misma ruta
public class ControladorJson {

    private final ServicioJugador servicioJugador;


    @Autowired
    public ControladorJson(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;

    }

    @GetMapping("/jugadoresJson")
        public List<Jugador> obtenerJugadores(HttpServletRequest request) {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Integer id = usuario.getId();


            return servicioJugador.obtenerJugadoresPorDt(id);

        }
    }


