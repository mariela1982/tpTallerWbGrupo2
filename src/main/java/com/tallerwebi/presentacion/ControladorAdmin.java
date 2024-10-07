package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.enums.PartidosDeBsAs;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {
    private ServicioJugador servicioJugador;
    private ServicioAdmin servicioAdmin;
    private ServicioEquipo servicioEquipo;


    // Constructor
    public ControladorAdmin() {
    }

    @Autowired
    public ControladorAdmin(ServicioJugador servicioJugador,ServicioEquipo servicioEquipo,ServicioAdmin servicioAdmin) {
        this.servicioJugador = servicioJugador;
        this.servicioEquipo = servicioEquipo;
        this.servicioAdmin = servicioAdmin;
    }

    @GetMapping
    public ModelAndView redirigirAPanel() {
        return new ModelAndView("redirect:/admin/panel");
    }

    // Controller para el panel de administrador
    @GetMapping("/panel")
    public ModelAndView panelAdmin() {
        return new ModelAndView("admin/panel");
    }

    // Controller para la vista de gestion de torneos
    @GetMapping("/torneos")
    public ModelAndView gestionTorneos() {
        ModelAndView mav = new ModelAndView("/torneos");
        mav.addObject("canchas", servicioAdmin.obtenerCanchas());
        mav.addObject("arbitros", servicioAdmin.obtenerArbitros());

        mav.addObject("torneo", new Torneo());
        mav.addObject("torneos", servicioAdmin.obtenerTorneos());
        
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());

        mav.addObject("editando", false);

        return mav;
    }

    // Controller para la creacion de torneos
    @PostMapping("/torneos/crear")
    public ModelAndView crearTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servicioAdmin.guardarTorneo(torneo); //faltaria una exepxion en guardartorneo de torneo existente;
        return new ModelAndView("redirect:/admin/torneos?creado=true");
    }

    // Controller para la vista de edicion de torneos
    @GetMapping("/torneos/editar/{id}")
    public ModelAndView mostrarFormularioEdicionTorneo(@PathVariable("id") Integer id) {
        Torneo torneo = servicioAdmin.obtenerTorneos().get(id);
        if (torneo != null) {
            ModelAndView mav = new ModelAndView("admin/torneos");
            mav.addObject("canchas", servicioAdmin.obtenerCanchas());
            mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
            mav.addObject("torneo", torneo);
            mav.addObject("torneos", servicioAdmin.obtenerTorneos());
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/admin/torneos");
    }

    // Controller para la edicion de torneos
    @PostMapping("/torneos/editar")
    public ModelAndView editarTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servicioAdmin.guardarTorneo(torneo);
        return new ModelAndView("redirect:/admin/torneos?editado=true");
    }

    // Controller para la eliminacion de torneos
    @GetMapping("/torneos/eliminar/{id}")
    public ModelAndView eliminarTorneo(@PathVariable("id") Integer id) {
        Torneo torneo = servicioAdmin.obtenerTorneos().get(id);
        if (torneo != null) {
            servicioAdmin.eliminarTorneo(torneo);
        }
        return new ModelAndView("redirect:/admin/torneos?eliminado=true");
    }

    // Controller para la vista de gestion de equipos
//    @GetMapping("/equipos")
//    public ModelAndView gestionEquipos() {
//        ModelAndView mav = new ModelAndView("equipos");
//        mav.addObject("equipos", repositorioAdmin.obtenerEquipos());
//        mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
//        mav.addObject("editando", false);
//        return mav;
//    }

    // Controller para la creacion de equipos
//    @PostMapping("/equipos/crear")
//    public ModelAndView crearEquipo(@ModelAttribute("equipo") Equipo equipo) {
//        repositorioAdmin.guardarEquipo(equipo);
//        return new ModelAndView("redirect:/admin/equipos?creado=true");
//    }

    // Controller para la vista de edicion de equipos
//    @GetMapping("/equipos/editar/{id}")
//    public ModelAndView mostrarFormularioEdicionEquipo(@PathVariable("id") Long id) {
//        Equipo equipo = repositorioAdmin.obtenerEquipoPorId(id);
//        if (equipo != null) {
//            ModelAndView mav = new ModelAndView("equipos");
//            mav.addObject("equipos", repositorioAdmin.obtenerEquipos());
//            mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
//            mav.addObject("equipo", equipo);
//            mav.addObject("editando", true);
//            return mav;
//        }
//        return new ModelAndView("redirect:/admin/equipos");
//    }

//    // Controller para la edicion de equipos
//    @PostMapping("/equipos/editar")
//    public ModelAndView editarEquipo(@ModelAttribute("equipo") Equipo equipo) {
//        // repositorioAdmin.guardarEquipo(equipo);
//        return new ModelAndView("redirect:/admin/equipos?editado=true");
//    }

    // Controller para la eliminacion de equipos
//    @GetMapping("/equipos/eliminar/{id}")
//    public ModelAndView eliminarEquipo(@PathVariable("id") Long id) {
//        Equipo equipo = repositorioAdmin.obtenerEquipoPorId(id);
//        if (equipo != null) {
//            repositorioAdmin.eliminarEquipo(equipo);
//        }
//        return new ModelAndView("redirect:/admin/equipos?eliminado=true");
//    }



   // Controller para la creacion de jugadores
//    @PostMapping("/jugadores/crear")
//    public ModelAndView crearJugador(@ModelAttribute("jugador") Jugador jugador) {
//        repositorioAdmin.guardarJugador(jugador);
//        return new ModelAndView("redirect:/admin/jugadores?creado=true");
//    }

//    // Controller para la vista de edicion de jugadores
//    @GetMapping("/jugadores/editar/{id}")
//    public ModelAndView mostrarFormularioEdicionJugador(@PathVariable("id") Long id) {
//        Jugador jugador = repositorioAdmin.obtenerJugadorPorId(id);
//        if (jugador != null) {
//            ModelAndView mav = new ModelAndView("jugadores");
//            mav.addObject("jugador", jugador);
//            mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
//            mav.addObject("editando", true);
//            return mav;
//        }
//        return new ModelAndView("redirect:/admin/jugadores");
//    }
//
//    // Controller para la edicion de jugadores
//    @PostMapping("/jugadores/editar")
//    public ModelAndView editarJugador(@ModelAttribute("jugador") Jugador jugador) {
//        repositorioAdmin.guardarJugador(jugador);
//        return new ModelAndView("redirect:/admin/jugadores?editado=true");
//    }
//
//    // Controller para la eliminacion de jugadores
//    @GetMapping("/jugadores/eliminar/{id}")
//    public ModelAndView eliminarJugador(@PathVariable("id") Long id) {
//        Jugador jugador = repositorioAdmin.obtenerJugadorPorId(id);
//        if (jugador != null) {
//            repositorioAdmin.eliminarJugador(jugador);
//        }
//        return new ModelAndView("redirect:/admin/jugadores?eliminado=true");
//    }

    // Controller para la vista de gestion de canchas
    @GetMapping("/canchas")
    public ModelAndView gestionCanchas() {
        ModelAndView mav = new ModelAndView("admin/canchas");
        mav.addObject("cancha", new Cancha());
        mav.addObject("canchas", servicioAdmin.obtenerCanchas());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la creacion de canchas
    @PostMapping("/canchas/crear")
    public ModelAndView crearCancha(@ModelAttribute("cancha") Cancha cancha) {
       servicioAdmin.guardarCancha(cancha);
        return new ModelAndView("redirect:/admin/canchas?creada=true");
    }

    // Controller para la edicion de canchas
    @PostMapping("/canchas/editar")
    public ModelAndView editarCancha(@ModelAttribute("cancha") Cancha cancha) {
        servicioAdmin.guardarCancha(cancha);
        return new ModelAndView("redirect:/admin/canchas?editada=true");
    }

    // Controller para la vista de edicion de canchas
    @GetMapping("/canchas/editar/{id}")
    public ModelAndView mostrarFormularioEdicion(@PathVariable("id") Integer id) {
        Cancha cancha = servicioAdmin.obtenerCanchas().get(id);
        if (cancha != null) {
            ModelAndView mav = new ModelAndView("admin/canchas");
            mav.addObject("cancha", cancha);
            mav.addObject("canchas", servicioAdmin.obtenerCanchas());
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/admin/canchas");
    }

    // Controller para la eliminacion de canchas
    @GetMapping("/canchas/eliminar/{id}")
    public ModelAndView eliminarCancha(@PathVariable("id") Integer id) {
        Cancha cancha = servicioAdmin.obtenerCanchas().get(id);
        if (cancha != null) {
            servicioAdmin.elimiarCancha(cancha);
        }
        return new ModelAndView("redirect:/admin/canchas?eliminada=true");
    }

    @GetMapping("/arbitros")
    public ModelAndView gestionArbitros() {
        ModelAndView mav = new ModelAndView("admin/arbitros");
        mav.addObject("arbitro", new Arbitro());
        mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la creacion de arbitros
    @PostMapping("/arbitros/crear")
    public ModelAndView crearArbitro(@ModelAttribute("arbitro") Arbitro arbitro) {
        servicioAdmin.guardarArbitro(arbitro);
        return new ModelAndView("redirect:/admin/arbitros?creado=true");
    }

    // Controller para la vista de edicion de arbitros
    @GetMapping("/arbitros/editar/{id}")
    public ModelAndView mostrarFormularioEdicionArbitro(@PathVariable("id") Integer id) {
        Arbitro arbitro = servicioAdmin.obtenerArbitros().get(id);
        if (arbitro != null) {
            ModelAndView mav = new ModelAndView("admin/arbitros");
            mav.addObject("arbitro", arbitro);
            mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/admin/arbitros");
    }

    // Controller para la edicion de arbitros
    @PostMapping("/arbitros/editar")
    public ModelAndView editarArbitro(@ModelAttribute("arbitro") Arbitro arbitro) {
        servicioAdmin.guardarArbitro(arbitro);
        return new ModelAndView("redirect:/admin/arbitros?editado=true");
    }

    // Controller para la eliminacion de arbitros
    @GetMapping("/arbitros/eliminar/{id}")
    public ModelAndView eliminarArbitro(@PathVariable("id") Integer id) {
        Arbitro arbitro = servicioAdmin.obtenerArbitros().get(id);
        if (arbitro != null) {
            servicioAdmin.eliminarArbitro(arbitro);
        }
        return new ModelAndView("redirect:/admin/arbitros?eliminado=true");
    }
////////////////////////////////////////////////////////////partidos arreglar
    // Controller para la vista de gestion de partidos
    @GetMapping("/partidos")
    public ModelAndView gestionPartidos() {
        ModelAndView mav = new ModelAndView("admin/partidos");
     //    mav.addObject("partidos", servicioAdmin.obtenerPartidos());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la vista de gestion de Sanciones
    @GetMapping("/sanciones")
    public ModelAndView gestionSanciones() {
        ModelAndView mav = new ModelAndView("admin/sanciones");
        // mav.addObject("sanciones", repositorioAdmin.obtenerSanciones());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la vista de gestion de Resultados
    @GetMapping("/resultados")
    public ModelAndView gestionResultados() {
        ModelAndView mav = new ModelAndView("admin/resultados");
        // mav.addObject("resultados", repositorioAdmin.obtenerResultados());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la vista de historial de transacciones
    @GetMapping("/transacciones")
    public ModelAndView historialTransacciones() {
        ModelAndView mav = new ModelAndView("admin/transacciones");
        // mav.addObject("transacciones", repositorioAdmin.obtenerTransacciones());
        mav.addObject("editando", false);
        return mav;
    }
}