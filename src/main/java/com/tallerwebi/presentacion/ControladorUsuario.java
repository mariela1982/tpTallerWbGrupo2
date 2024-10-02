package com.tallerwebi.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.Torneo;
import com.tallerwebi.dominio.Usuario;


public class ControladorUsuario {
        private RepositorioAdmin repositorioAdmin;

    @Autowired
    public ControladorUsuario(RepositorioAdmin repositorioAdmin) {
        this.repositorioAdmin = repositorioAdmin;
    }

    @GetMapping("/cargar-dinero")
    public ModelAndView cargarDinero(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cargarDinero");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        mav.addObject("usuario", usuario);
        return mav;
    }

    @PostMapping("/procesar-cargar-dinero")
    public ModelAndView procesarCargarDinero(@ModelAttribute("monto") Integer monto, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        usuario.setSaldo(usuario.getSaldo() + monto);
        return new ModelAndView("redirect:/cargar-dinero");
    }

    @GetMapping("/pagar-inscripcion")
    public ModelAndView pagarInscripcion(@RequestParam("torneo") Long torneoId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pagarInscripcion");
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        mav.addObject("torneo", torneo);
        mav.addObject("torneos", repositorioAdmin.obtenerTorneos());

        return mav;
    }

    @GetMapping("/torneos")
    public ModelAndView verTorneos(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("torneos");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        List<Torneo> torneos = repositorioAdmin.obtenerTorneos();
        mav.addObject("torneos", torneos);
        mav.addObject("usuario", usuario);
        return mav;
    }

    @GetMapping("/torneos/{id}")
    public ModelAndView verTorneo(@PathVariable("id") Long torneoId, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mav = new ModelAndView("torneo");
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        int cuposOcupados = torneo.getEquipos().size();
        int cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;

        mav.addObject("usuario", usuario);
        mav.addObject("torneo", torneo);
        mav.addObject("cuposOcupados", cuposOcupados);
        mav.addObject("cuposDisponibles", cuposDisponibles);

        return mav;
    }

    @PostMapping("/equipo/inscribir")
    public ModelAndView inscribirEquipo(@RequestParam("torneoId") Long torneoId, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        // Validar si el usuario tiene saldo suficiente
        if (usuario.getSaldo() < torneo.getPrecioEntrada()) {
            ModelAndView mav = new ModelAndView("torneo");
            mav.addObject("usuario", usuario);
            mav.addObject("torneo", torneo);
            mav.addObject("error", "Saldo insuficiente");
            return mav;
        }

        // Si tiene saldo suficiente, procesar el pago
        usuario.setSaldo(usuario.getSaldo() - torneo.getPrecioEntrada());

        // Crear el equipo
        Equipo equipo = new Equipo();
        equipo.setNombre("Equipo de " + usuario.getNombre());

        // Agregar el equipo al torneo
        torneo.getEquipos().add(equipo);

        // Guardar los cambios
        repositorioAdmin.guardarEquipo(equipo);

        return new ModelAndView("redirect:/torneos");
    }

}
