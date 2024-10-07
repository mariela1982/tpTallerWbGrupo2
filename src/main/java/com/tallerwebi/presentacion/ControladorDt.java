package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorDt {
    private ServicioDt servicioDt;
    private ServicioEquipo servicioEquipo;
    private ServicioAdmin servicioAdmin;


    @Autowired
    public ControladorDt(ServicioDt servicioDt) {
        this.servicioDt = servicioDt;
    }
    @Autowired
    public ControladorUsuario(RepositorioAdmin repositorioAdmin) {
        this.repositorioAdmin = repositorioAdmin;
    }

    @GetMapping("/directorTecnico")
    public ModelAndView panelAdmin(){
        return new ModelAndView();
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

    @GetMapping("/torneos")
    public ModelAndView verTorneos(@RequestParam(value = "partido", required = false) PartidosDeBsAs partido,
                                   HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("torneos");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        List<Torneo> torneos = repositorioAdmin.obtenerTorneos();
        if (partido != null) {
            torneos = torneos.stream()
                    .filter(torneo -> torneo.getPartido().equals(partido))
                    .collect(Collectors.toList());
        }

        mav.addObject("torneos", torneos);
        mav.addObject("usuario", usuario);
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());
        mav.addObject("partido", partido);
        return mav;
    }

    @GetMapping("/torneos/{id}")
    public ModelAndView verTorneo(@PathVariable("id") Long torneoId, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mav = new ModelAndView("torneo");
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        Integer cuposOcupados = torneo.getEquipos().size();
        Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;

        mav.addObject("usuario", usuario);
        mav.addObject("torneo", torneo);
        mav.addObject("cuposOcupados", cuposOcupados);
        mav.addObject("cuposDisponibles", cuposDisponibles);

        return mav;
    }

    @PostMapping("/equipo/inscribir")
    public ModelAndView inscribirEquipo(@RequestParam("torneoId") Long torneoId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("torneo");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        Integer cuposOcupados = torneo.getEquipos().size();
        Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;

        // Validar si el usuario esta logueado
        if (usuario == null) {
            mav.addObject("error", "Tenes que estar logueado para inscribirte a un torneo");
        } else {
            // Validar si el torneo tiene cupos disponibles
            if (cuposDisponibles <= 0) {
                mav.addObject("error", "No hay cupos disponibles para inscribirte al torneo");
            } else {
                // Validar si el usuario tiene saldo suficiente
                if (usuario.getSaldo() < torneo.getPrecioEntrada()) {
                    mav.addObject("error", "No tenes saldo suficiente para inscribirte al torneo");
                } else {
                    // Si tiene saldo suficiente, procesar el pago
                    usuario.setSaldo(usuario.getSaldo() - torneo.getPrecioEntrada());
                    mav.addObject("success", "Te inscribiste al torneo correctamente");
                }
            }
        }

        mav.addObject("usuario", usuario);
        mav.addObject("torneo", torneo);
        mav.addObject("cuposOcupados", cuposOcupados);
        mav.addObject("cuposDisponibles", cuposDisponibles);

        return mav;
    }




}
