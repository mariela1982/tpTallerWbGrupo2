package com.tallerwebi.presentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.EquipoExistente;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;

@Controller
public class ControladorDt {

   // private RepositorioAdmin repositorioAdmin;
    private ServicioDt servicioDt;
    private ServicioEquipo servicioEquipo;
    private ServicioAdmin servicioAdmin;
    private ServicioJugador servicioJugador;


    @Autowired
     public ControladorDt(ServicioDt servicioDt, ServicioEquipo servicioEquipo, ServicioAdmin admin,ServicioJugador servicioJugador) {
        this.servicioDt = servicioDt;
        this.servicioEquipo = servicioEquipo;
        this.servicioAdmin = admin;
        this.servicioJugador = servicioJugador;
    }

    @GetMapping("/directorTecnico")
    public ModelAndView panelAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        return new ModelAndView();
    }

//
////modificar hay que usar servicio
//    @GetMapping("/cargar-dinero")
//    public ModelAndView cargarDinero(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("cargarDinero");
//        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
//
//        mav.addObject("usuario", usuario);
//        return mav;
//    }
////modificar
//    @PostMapping("/procesar-cargar-dinero")
//    public ModelAndView procesarCargarDinero(@ModelAttribute("monto") Integer monto, HttpServletRequest request) {
//        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
//        usuario.setSaldo(usuario.getSaldo() + monto);
//        return new ModelAndView("redirect:/cargar-dinero");
//    }
//--------------para inscribirse a un torneo-----------
    @GetMapping("/torneos")
    public ModelAndView verTorneos(@RequestParam(value = "partido", required = false) PartidosDeBsAs partido,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("torneos");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        List<Torneo> torneos = servicioAdmin.obtenerTorneos();
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
        Torneo torneo = servicioAdmin.obtenerTorneo(torneoId);

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
        Torneo torneo = servicioAdmin.obtenerTorneo(torneoId);

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
    @GetMapping("/jugadores")
    public ModelAndView crearJugadores(){
        ModelMap model = new ModelMap();
        Jugador jugador = new Jugador();
        model.addAttribute("jugador", jugador);

        return new ModelAndView("jugadores", model);
    }
    // Controller para la creacion de jugadores
@PostMapping("/crearJugador")
public ModelAndView crearJugador(@ModelAttribute("jugador") Jugador jugador) {
    ModelMap model = new ModelMap();
    try {
        servicioJugador.guardarJugador(jugador);
        model.addAttribute("jugadores",servicioJugador.obtenerJugadores());
        model.addAttribute("jugadorCreado",jugador);
        model.put("registro","jugador creado");

    } catch (JugadorExistente | JugadorInexistente e) {
        model.put("Error", "El jugador ya existe");
        return new ModelAndView("jugadores", model);
    }

    return new ModelAndView("jugadores", model);

    }

    // Controller para la vista de edicion de jugadores
    @GetMapping("/jugadores/editar/{id}")
    public ModelAndView mostrarFormularioEdicionJugador(@PathVariable("id") Long id) {
        Jugador jugador = null;
        ModelMap model = new ModelMap();
        try {
           jugador = servicioJugador.buscarJugador(id);
        } catch (JugadorInexistente e) {
            model.put("Error", "El jugador no existe");
            return new ModelAndView("redirect:/jugadores", model);

        }
        if (jugador != null) {
            ModelAndView mav = new ModelAndView("jugadores");
            mav.addObject("jugador", jugador);
            mav.addObject("jugadores", servicioJugador.obtenerJugadores());
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/jugadores");
    }

    // Controller para la edicion de jugadores
    @PostMapping("/jugadores/editar")
    public ModelAndView editarJugador(@ModelAttribute("jugador") Jugador jugador) {
        ModelMap model = new ModelMap();
        try {
            servicioJugador.actualizarJugador(jugador.getId());

        } catch (JugadorInexistente e) {
            model.put("Error", "El jugador no existe");
        }
        return new ModelAndView("redirect:/jugadores?editado=true");
    }

    // Controller para la eliminacion de jugadores
    @GetMapping("/jugadores/eliminar/{id}")
    public ModelAndView eliminarJugador(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();
        try {
            servicioJugador.eliminarJugador(id);
        } catch (JugadorInexistente e) {
            model.put("Error", "El jugador no existe");
        }
       return new ModelAndView("redirect:/jugadores?eliminado=true");
    }
    // Controller para la vista de gestion de equipos
    @GetMapping("/equipos")
    public ModelAndView gestionEquipos() {
        ModelMap modelMap = new ModelMap();
        Equipo equipo = new Equipo();

        modelMap.put("equipo", equipo);
        modelMap.put("equipos", servicioEquipo.obtenerEquipos());
        modelMap.put("jugadores", servicioJugador.obtenerJugadores());
        modelMap.put("editando", false);
        return new ModelAndView("equipos",modelMap);
    }

    // Controller para la creacion de equipos
    @PostMapping("/equipos/crear")
    public ModelAndView crearEquipo(@ModelAttribute("equipo") Equipo equipo,
                                    @RequestParam(value = "jugadoresId",required = false)List<Long> jugadoresId) {
        ModelMap model = new ModelMap();
        try {
           if (jugadoresId != null) {
               List<Jugador> jugadoresElegidos = new ArrayList<>();
               for (Long id : jugadoresId) {
                   Jugador jugador =servicioJugador.buscarJugador(id);
                   jugadoresElegidos.add(jugador);

               }
               if (equipo.getJugadores() == null){
                   equipo.setJugadores(new ArrayList<>());
               }
               equipo.getJugadores().addAll(jugadoresElegidos);
           }

            if (equipo.getJugadores() == null || equipo.getJugadores().isEmpty()) {
                throw new IllegalArgumentException("El equipo debe tener al menos un jugador.");
            }
          servicioEquipo.guardarEquipo(equipo);
            model.addAttribute("equipos",servicioEquipo.obtenerEquipos());
            model.addAttribute("equipoCreado",equipo);
            model.put("registro","equipoCreado");
        } catch (EquipoExistente | JugadorInexistente e) {
            model.put("Error", "El equipo ya existe");
            return new ModelAndView("equipos", model);
        }
        return new ModelAndView("/equipos",model);
    }


    // Controller para la vista de edicion de equipos
    @GetMapping("/equipos/editar/{id}")
    public ModelAndView mostrarFormularioEdicionEquipo(@PathVariable("id") Long id) {
        Equipo equipo = null;
        ModelMap model = new ModelMap();
        try {
            equipo = servicioEquipo.buscarEquipoPorId(id);
        } catch (EquipoInexistente e) {
            model.put("Error", "El equipo no existe");
        }
        if (equipo != null) {
            ModelAndView mav = new ModelAndView("equipos");
            mav.addObject("equipos", servicioEquipo.obtenerEquipos());
            mav.addObject("jugadores", servicioJugador.obtenerJugadores());
            mav.addObject("equipo", equipo);
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/equipos");
    }
//
    // Controller para la edicion de equipos
    @PostMapping("/equipos/editar")
    public ModelAndView editarEquipo(@ModelAttribute("equipo") Equipo equipo) {
        ModelMap model = new ModelMap();
        try {
            servicioEquipo.guardarEquipo(equipo);
        } catch (EquipoExistente e) {
            model.put("Error", "El equipo no existe");
        }
        return new ModelAndView("redirect:/equipos?editado=true");
    }

    // Controller para la eliminacion de equipos
    @GetMapping("/equipos/eliminar/{id}")
    public ModelAndView eliminarEquipo(@PathVariable("id") Long id) {
        Equipo equipo = null;
        ModelMap model = new ModelMap();
        try {
            equipo = servicioEquipo.buscarEquipoPorId(id);
        } catch (EquipoInexistente e) {
            model.put("Error", "El equipo no existe");
        }
        if (equipo != null) {
            servicioEquipo.eliminarEquipo(equipo);
        }
        return new ModelAndView("redirect:/equipos?eliminado=true");
    }


}