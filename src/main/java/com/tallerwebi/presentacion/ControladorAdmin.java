package com.tallerwebi.presentacion;

<<<<<<< HEAD
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

=======
import com.tallerwebi.dominio.*;
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.tallerwebi.dominio.Arbitro;
import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Partido;
import com.tallerwebi.dominio.RepositorioAdmin;
import com.tallerwebi.dominio.Torneo;
=======
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
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
    @GetMapping("/torneosAdmin")
    public ModelAndView gestionTorneos() {
        ModelAndView mav = new ModelAndView("/torneosAdmin");
        mav.addObject("canchas", servicioAdmin.obtenerCanchas());
        mav.addObject("arbitros", servicioAdmin.obtenerArbitros());

        mav.addObject("torneo", new Torneo());
<<<<<<< HEAD
        mav.addObject("torneos", repositorioAdmin.obtenerTorneos());

=======
        mav.addObject("torneos", servicioAdmin.obtenerTorneos());
        
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());

        mav.addObject("editando", false);

        return mav;
    }

    // Controller para la creacion de torneos
    @PostMapping("/torneosAdmin/crear")
    public ModelAndView crearTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servicioAdmin.guardarTorneo(torneo); //faltaria una exepxion en guardartorneo de torneo existente;
        return new ModelAndView("redirect:/admin/torneosAdmin?creado=true");
    }

    // Controller para la vista de edicion de torneos
    @GetMapping("/torneosAdmin/editar/{id}")
    public ModelAndView mostrarFormularioEdicionTorneo(@PathVariable("id") Integer id) {
        Torneo torneo = servicioAdmin.obtenerTorneos().get(id);
        if (torneo != null) {
            ModelAndView mav = new ModelAndView("torneosAdmin");
            mav.addObject("canchas", servicioAdmin.obtenerCanchas());
            mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
            mav.addObject("torneo", torneo);
            mav.addObject("torneos", servicioAdmin.obtenerTorneos());
            mav.addObject("editando", true);
            return mav;
        }
        return new ModelAndView("redirect:/admin/torneosAdmin");
    }

    // Controller para la edicion de torneos
    @PostMapping("/torneosAdmin/editar")
    public ModelAndView editarTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servicioAdmin.guardarTorneo(torneo);
        return new ModelAndView("redirect:/admin/torneos?editado=true");
    }

    // Controller para la eliminacion de torneos
<<<<<<< HEAD
    @GetMapping("/torneos/eliminar/{id}")
    public ModelAndView eliminarTorneo(@PathVariable("id") Long id) {
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(id);

=======
    @GetMapping("/torneosAdmin/eliminar/{id}")
    public ModelAndView eliminarTorneo(@PathVariable("id") Integer id) {
        Torneo torneo = servicioAdmin.obtenerTorneos().get(id);
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
        if (torneo != null) {
            servicioAdmin.eliminarTorneo(torneo);
        }
<<<<<<< HEAD

        return new ModelAndView("redirect:/admin/torneos?eliminado=true");
=======
        return new ModelAndView("redirect:/admin/torneosAdmin?eliminado=true");
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
    }

    // Controller para la vista de detalles de torneo
    @GetMapping("/torneos/{id}")
    public ModelAndView verTorneo(@PathVariable("id") Long torneoId) {
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(torneoId);

        if (torneo != null) {
            ModelAndView mav = new ModelAndView("admin/torneo");

            Integer cuposOcupados = torneo.getEquipos().size();
            Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;
            List<Partido> partidos = repositorioAdmin.obtenerPartidosPorTorneo(torneo);

            mav.addObject("partidos", partidos);
            mav.addObject("cuposOcupados", cuposOcupados);
            mav.addObject("cuposDisponibles", cuposDisponibles);
            mav.addObject("torneo", torneo);
            return mav;
        }

        return new ModelAndView("redirect:/admin/torneos");
    }

    // Controller para el sorteo de equipos
    @PostMapping("/torneos/sortear")
    public ModelAndView sortearEquipos(@RequestParam("id") Long id) {
        Torneo torneo = repositorioAdmin.obtenerTorneoPorId(id);

        if (torneo != null) {
            List<Equipo> equipos = torneo.getEquipos();
            int cantidadEquiposInscriptos = equipos.size();

            // Verificar si la cantidad de equipos es menor que el número de equipos
            // inscriptos
            if (cantidadEquiposInscriptos < torneo.getCantidadEquipos()) {
                return new ModelAndView("redirect:/admin/torneos/" + id + "?faltanEquipos=true");
            }

            // Verificar si ya existen partidos asociados al torneo
            List<Partido> partidosExistentes = repositorioAdmin.obtenerPartidosPorTorneo(torneo);
            if (!partidosExistentes.isEmpty()) {
                return new ModelAndView("redirect:/admin/torneos/" + id + "?partidosExistentes=true");
            }

            torneo.sortearEquipos();
            repositorioAdmin.guardarTorneo(torneo);

            int cantidadPartidos = cantidadEquiposInscriptos / 2;

            for (int i = 0; i < cantidadPartidos; i++) {
                Partido partido = new Partido();
                partido.setTorneo(torneo);

                Equipo equipoLocal = equipos.get(i * 2);
                Equipo equipoVisitante = equipos.get(i * 2 + 1);

                // Logica para las faces del torneo
                if (cantidadPartidos == 8) {
                    partido.setFase("Octavos de Final");
                } else if (cantidadPartidos == 4) {
                    partido.setFase("Cuartos de Final");
                } else if (cantidadPartidos == 2) {
                    partido.setFase("Semifinal");
                }

                partido.setEquipoLocal(equipoLocal);
                partido.setEquipoVisitante(equipoVisitante);

                repositorioAdmin.guardarPartido(partido);
            }

            return new ModelAndView("redirect:/admin/torneos/" + id + "?sorteado=true");
        }

        return new ModelAndView("redirect:/admin/torneos");
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
<<<<<<< HEAD
    @GetMapping("/equipos/editar/{id}")
    public ModelAndView mostrarFormularioEdicionEquipo(@PathVariable("id") Long id) {
        Equipo equipo = repositorioAdmin.obtenerEquipoPorId(id);

        if (equipo != null) {
            ModelAndView mav = new ModelAndView("admin/equipos");
            mav.addObject("equipos", repositorioAdmin.obtenerEquipos());
            mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
            mav.addObject("equipo", equipo);
            mav.addObject("editando", true);
            return mav;
        }

        return new ModelAndView("redirect:/admin/equipos");
    }
=======
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
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b

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

<<<<<<< HEAD
    // Controller para la vista de gestion de jugadores
    @GetMapping("/jugadores")
    public ModelAndView gestionJugadores() {
        ModelAndView mav = new ModelAndView("admin/jugadores");
        mav.addObject("jugador", new Jugador());
        mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
        mav.addObject("editando", false);
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());
        return mav;
    }
=======
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b


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
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());
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
<<<<<<< HEAD
        mav.addObject("partidos", repositorioAdmin.obtenerPartidos());
=======
     //    mav.addObject("partidos", servicioAdmin.obtenerPartidos());
>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la vista de detalles del partido
    @GetMapping("/partidos/{id}")
    public ModelAndView verPartido(@PathVariable("id") Long partidoId) {
        Partido partido = repositorioAdmin.obtenerPartidoPorId(partidoId);

        if (partido != null) {
            ModelAndView mav = new ModelAndView("admin/partido");
            List<Cancha> canchas = repositorioAdmin.obtenerCanchas();
            List<Arbitro> arbitros = repositorioAdmin.obtenerArbitros();

            mav.addObject("partido", partido);
            mav.addObject("canchas", canchas);
            mav.addObject("arbitros", arbitros);
            return mav;
        }

        return new ModelAndView("redirect:/admin/partidos");
    }

    // Controller para Jugar Partido
    @PostMapping("/partidos/jugar")
    public ModelAndView jugarPartido(@RequestParam("partidoId") Long partidoId) {
        Partido partido = repositorioAdmin.obtenerPartidoPorId(partidoId);

        if (partido != null) {
            if (partido.estaFinalizado()) {
                return new ModelAndView("redirect:/admin/partidos/" + partidoId + "?estaFinalizado=true");
            }

            partido.jugarPartido();

            // Verificar si hay un partido pendiente de asignar equipo visitante
            Torneo torneo = partido.getTorneo();
            Partido partidoEsperandoRival = repositorioAdmin.obtenerPartidoEsperandoRival(torneo,
                    partido.avanzarFase(partido.getFase()));

            if (partidoEsperandoRival != null) {
                // Asignar el ganador al equipo visitante en el partido existente
                partidoEsperandoRival.setEquipoVisitante(partido.getGanador());
                repositorioAdmin.guardarPartido(partidoEsperandoRival);
            } else {
                // Si la fase es la final, asignar el ganador al torneo
                if (!partido.getFase().equals("Final")) {
                    // Si no hay partido esperando rival y la fase no es la final, crear un nuevo
                    Partido nuevoPartido = new Partido();
                    nuevoPartido.setTorneo(torneo);
                    nuevoPartido.setEquipoLocal(partido.getGanador());
                    nuevoPartido.setFase(partido.avanzarFase(partido.getFase()));
                    repositorioAdmin.guardarPartido(nuevoPartido);
                }
            }

            repositorioAdmin.guardarPartido(partido);
            repositorioAdmin.guardarTorneo(torneo);
        }

        return new ModelAndView("redirect:/admin/partidos/" + partidoId);
    }

    // Controller para completar los datos del partido
    @PostMapping("/partidos/completar")
    public ModelAndView completarPartido(
            @RequestParam("id") Long id,
            @RequestParam("fecha") Date fecha,
            @RequestParam("hora") LocalTime hora,
            @RequestParam("cancha_id") Long canchaId,
            @RequestParam("arbitro_id") Long arbitroId) {
        Partido partido = repositorioAdmin.obtenerPartidoPorId(id);

        if (partido != null) {
            Cancha cancha = repositorioAdmin.obtenerCanchaPorId(canchaId);
            Arbitro arbitro = repositorioAdmin.obtenerArbitroPorId(arbitroId);

            if (cancha != null && arbitro != null) {
                partido.setFecha(fecha);
                partido.setHora(hora);
                partido.setCancha(cancha);
                partido.setArbitro(arbitro);

                repositorioAdmin.guardarPartido(partido);
                return new ModelAndView("redirect:/admin/partidos/" + id);
            }
        }

        return new ModelAndView("redirect:/admin/partidos");
    }

    // Controller para completar el resultado del partido
    @PostMapping("/partidos/completar-resultado")
    public ModelAndView completarResultado(
            @RequestParam("id") Long id,
            @RequestParam("golesLocal") Integer golesLocal,
            @RequestParam("golesVisitante") Integer golesVisitante) {
        Partido partido = repositorioAdmin.obtenerPartidoPorId(id);

        if (partido != null) {
            if (partido.estaFinalizado()) {
                return new ModelAndView("redirect:/admin/partidos/" + id + "?estaFinalizado=true");
            }

            if (golesLocal == golesVisitante) {
                return new ModelAndView("redirect:/admin/partidos/" + id + "?golesIguales=true");
            }

            partido.setGolesLocal(golesLocal);
            partido.setGolesVisitante(golesVisitante);

            // Verificar si hay un partido pendiente de asignar equipo visitante
            Torneo torneo = partido.getTorneo();
            Partido partidoEsperandoRival = repositorioAdmin.obtenerPartidoEsperandoRival(torneo,
                    partido.avanzarFase(partido.getFase()));

            if (partidoEsperandoRival != null) {
                // Asignar el ganador al equipo visitante en el partido existente
                partidoEsperandoRival.setEquipoVisitante(partido.getGanador());
                repositorioAdmin.guardarPartido(partidoEsperandoRival);
            } else {
                // Si la fase es la final, asignar el ganador al torneo
                if (!partido.getFase().equals("Final")) {
                    // Si no hay partido esperando rival y la fase no es la final, crear un nuevo
                    Partido nuevoPartido = new Partido();
                    nuevoPartido.setTorneo(torneo);
                    nuevoPartido.setEquipoLocal(partido.getGanador());
                    nuevoPartido.setFase(partido.avanzarFase(partido.getFase()));
                    repositorioAdmin.guardarPartido(nuevoPartido);
                }
            }

            repositorioAdmin.guardarPartido(partido);
            repositorioAdmin.guardarTorneo(torneo);
        }

        return new ModelAndView("redirect:/admin/partidos/" + id);
    }

    // Controller para la vista de gestion de Sanciones
    @GetMapping("/sanciones")
    public ModelAndView gestionSanciones() {
        ModelAndView mav = new ModelAndView("admin/sanciones");
        mav.addObject("equipos", repositorioAdmin.obtenerEquipos());
        mav.addObject("jugadores", repositorioAdmin.obtenerJugadores());
        mav.addObject("jugadoresConSancion", repositorioAdmin.obtenerJugadoresConSancion());
        return mav;
    }

    // Metodo para obtener los jugadores de un equipo
    @GetMapping("/sanciones/jugadores/{id}")
    @ResponseBody
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable Long id) {
        Equipo equipo = repositorioAdmin.obtenerEquipoPorId(id);
        List<Jugador> jugadores = repositorioAdmin.obtenerJugadoresPorEquipo(equipo);
        return ResponseEntity.ok(jugadores);
    }

    // Controller para asignar sancion a un jugador
    @PostMapping("/sanciones/asignar")
    public ModelAndView asignarSancion(
            @RequestParam("equipo") Long equipoId,
            @RequestParam("jugador") Long jugadorId,
            @RequestParam("sancion") String sancion) {
        Equipo equipo = repositorioAdmin.obtenerEquipoPorId(equipoId);
        Jugador jugador = repositorioAdmin.obtenerJugadorPorId(jugadorId);

        if (equipo != null && jugador != null) {
            jugador.setSancion(sancion);
            repositorioAdmin.guardarJugador(jugador);
        }

        return new ModelAndView("redirect:/admin/sanciones?asignada=true");
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