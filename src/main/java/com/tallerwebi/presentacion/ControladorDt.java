package com.tallerwebi.presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.entidades.*;
import com.tallerwebi.dominio.enums.TipoDePago;
import com.tallerwebi.dominio.excepcion.EquipoExistente;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    private ServicioLogin servicioLogin;


    @Autowired
     public ControladorDt(ServicioDt servicioDt, ServicioEquipo servicioEquipo, ServicioAdmin admin,ServicioJugador servicioJugador,ServicioLogin servicioLogin) {
        this.servicioDt = servicioDt;
        this.servicioEquipo = servicioEquipo;
        this.servicioAdmin = admin;
        this.servicioJugador = servicioJugador;
        this.servicioLogin = servicioLogin;
    }

    @GetMapping("/directorTecnico")
    public ModelAndView panelAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        return new ModelAndView();
    }


//--------------para inscribirse a un torneo-----------

    @GetMapping("/torneos")
    public ModelAndView verTorneos(@RequestParam(value = "partido", required = false) PartidosDeBsAs partido,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("torneos");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        List<Torneo> torneos = servicioAdmin.obtenerTorneosPorPartidoDeBsAs(partido);

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
        Torneo torneo = servicioAdmin.buscarTorneoPorId(torneoId);

        Integer cuposOcupados = torneo.getEquipos().size();
        Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;
        boolean usuarioLogueado = (usuario != null);

        mav.addObject("usuario", usuario);
        mav.addObject("torneo", torneo);
        mav.addObject("cuposOcupados", cuposOcupados);
        mav.addObject("cuposDisponibles", cuposDisponibles);
        mav.addObject("usuarioLogueado", usuarioLogueado);

        return mav;
    }


    @PostMapping("/equipo/inscribir")
    public ModelAndView inscribirEquipo(@RequestParam(value ="torneoId") Long torneoId, HttpServletRequest request,RedirectAttributes redirectAttributes) {

        ModelMap model = new ModelMap();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Torneo torneo = servicioAdmin.buscarTorneoPorId(torneoId);
     //   Integer cuposOcupados = torneo.getEquipos().size();
       // Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;

            Equipo equipo = servicioEquipo.buscarEquipoPorDt(usuario.getDni());
            torneo.getEquipos().add(equipo);
          Integer  cuposOcupados = torneo.getEquipos().size();
         Integer  cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;


        redirectAttributes.addFlashAttribute("torneo",torneo);
        redirectAttributes.addFlashAttribute("id",torneoId);
        redirectAttributes.addFlashAttribute("cuposOcupados",cuposOcupados);
        redirectAttributes.addFlashAttribute("cuposDisponibles",cuposDisponibles);
        redirectAttributes.addFlashAttribute("usuario",usuario);
        redirectAttributes.addFlashAttribute("equipo",equipo);
        redirectAttributes.addFlashAttribute("exitoso","carga para inscribire exitosa");

            return new ModelAndView("redirect:/inscripcion", model);
        }

    @GetMapping("/inscripcion")
    public ModelAndView verInscripcion( HttpServletRequest request) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            ModelAndView mav = new ModelAndView("inscripcion");
           Torneo torneo= servicioAdmin.buscarTorneoPorId(3L);

            mav.addObject("usuario", usuario);
        mav.addObject("torneo", torneo);
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
public ModelAndView crearJugador(@ModelAttribute("jugador") Jugador jugador,HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    ModelMap model = new ModelMap();
    try {
        servicioJugador.guardarJugador(jugador);
        servicioLogin.guardarJugadorCreadoPorDt(usuario.getId(), jugador);
        model.addAttribute("jugadores",servicioJugador.obtenerJugadores());
        model.addAttribute("jugadorCreado",jugador);
        model.put("registro","jugador creado");

    } catch (JugadorExistente | JugadorInexistente e) {
        model.put("Error", "El jugador ya existe");
        return new ModelAndView("jugadores", model);
    }

    return new ModelAndView("jugadores", model);

    }

    //editar jugadores
//
//    @GetMapping("/edicionJugador")
//    public ModelAndView editarJugador( HttpServletRequest request) {
//        ModelMap model = new ModelMap();
//        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
//        model.addAttribute("jugadores", servicioDt.obtenerJugadores(usuario.getId()));
//        return new ModelAndView("edicionJugador", model);
//    }
    @GetMapping("/edicionJugador")
    public ModelAndView editarJugador( HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("edicionJugador");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Integer id = (Integer) request.getSession().getAttribute("id");
      mav.addObject("jugadores",servicioJugador.obtenerJugadoresPorDt(id));
     //   mav.addObject("jugadores", servicioJugador.obtenerJugadores());
        return mav;

    }

    // Controller para la vista de edicion de jugadores
    @GetMapping("/edicionJugador/editar/{id}")
    public ResponseEntity<?> mostrarFormularioEdicionJugador(@PathVariable("id") Long id) {
       // Jugador jugador = null;

        try {
         Jugador  jugador = servicioJugador.buscarJugador(id);
         return ResponseEntity.ok(jugador);

        } catch (JugadorInexistente e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el Jugador No Existe");

        }

    }

    @PostMapping("/edicionJugador/editar")
    public ModelAndView editarJugador(@ModelAttribute("jugador") Jugador jugador) {
        ModelMap model = new ModelMap();
        try {
            servicioJugador.actualizarJugador(jugador.getId());

        } catch (JugadorInexistente e) {
            model.put("Error", "El jugador no existe");
        }
        return new ModelAndView("redirect:/edicionJugador");
    }

    // Controller para la eliminacion de jugadores
    @GetMapping("/edicionJugador/eliminar/{id}")
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

        modelMap.addAttribute("equipo", equipo);
        modelMap.addAttribute("equipos", servicioEquipo.obtenerEquipos());
        modelMap.addAttribute("jugadores", servicioJugador.obtenerJugadores());
        modelMap.addAttribute("editando", false);
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
                    Jugador jugador = servicioJugador.buscarJugador(id);
                    jugadoresElegidos.add(jugador);
                    equipo.agregarJugador(jugador);
                    //relacion bidireccional
                 //  servicioJugador.agregarleEquipo(jugador.getId(),equipo);
                    jugador.setEquipo(equipo);
                    System.out.println("Equipo recibido: " + equipo);
                    System.out.println("Jugadores ID: " + jugadoresId);

                }
                if (equipo.getJugadores() == null) {
                    equipo.setJugadores(new ArrayList<>());
                }
                equipo.getJugadores().addAll(jugadoresElegidos);
            }

            if (equipo.getJugadores() == null || equipo.getJugadores().isEmpty()) {
                throw new IllegalArgumentException("El equipo debe tener al menos un jugador.");
            }
            servicioEquipo.guardarEquipo(equipo);
            model.addAttribute("equipos", servicioEquipo.obtenerEquipos());
            model.addAttribute("equipoCreado", equipo);
            model.put("registro", "equipoCreado");

        } catch (EquipoExistente | JugadorInexistente e) {
            model.put("Error", "El equipo ya existe");
            return new ModelAndView("equipos", model);
        }
        return new ModelAndView("equipos",model);
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

    @GetMapping("/buscarJugadores")
    public ModelAndView buscarJugadores() {
        ModelMap model = new ModelMap();
        model.addAttribute("jugadores", servicioJugador.obtenerUsuariosJugadores());
        model.addAttribute("partidosDeBsAs", PartidosDeBsAs.values());
        return new ModelAndView("buscarJugadores", model);
    }

}
