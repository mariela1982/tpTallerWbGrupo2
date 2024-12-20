package com.tallerwebi.presentacion;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

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

import com.tallerwebi.dominio.ServicioAdmin;
import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidades.Arbitro;
import com.tallerwebi.dominio.entidades.Cancha;
import com.tallerwebi.dominio.entidades.Equipo;
import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Partido;
import com.tallerwebi.dominio.entidades.Torneo;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.EquipoInexistente;
import com.tallerwebi.dominio.excepcion.JugadorExistente;
import com.tallerwebi.dominio.excepcion.JugadorInexistente;
import com.tallerwebi.dominio.excepcion.TorneoExistente;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {
    private ServicioJugador servicioJugador;
    private ServicioAdmin servicioAdmin;
    private ServicioEquipo servicioEquipo;

    @Autowired
    public ControladorAdmin(ServicioJugador servicioJugador, ServicioEquipo servicioEquipo,
            ServicioAdmin servicioAdmin) {
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
        ModelAndView mav = new ModelAndView("admin/torneos");

        mav.addObject("canchas", servicioAdmin.obtenerCanchas());
        mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
        mav.addObject("torneo", new Torneo());
        mav.addObject("torneos", servicioAdmin.obtenerTorneos());
        mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());

        return mav;
    }

    // Controller para la creacion de torneos
    @PostMapping("/torneos/crear")
    public ModelAndView crearTorneo(@ModelAttribute("torneo") Torneo torneo) {
        try {
            servicioAdmin.guardarTorneo(torneo);
            return new ModelAndView("redirect:/admin/torneos?creado=true");
        } catch (TorneoExistente e) {
            return new ModelAndView("redirect:/admin/torneos?existente=true");
        }
    }

    // Controller para la eliminacion de torneos
    @GetMapping("/torneos/eliminar/{id}")
    public ModelAndView eliminarTorneo(@PathVariable("id") Long id) {
        Torneo torneo = servicioAdmin.buscarTorneoPorId(id);

        if (torneo != null) {
            servicioAdmin.eliminarTorneo(torneo);
            return new ModelAndView("redirect:/admin/torneos?eliminado=true");
        }

        return new ModelAndView("redirect:/admin/torneos");
    }

    // Controller para la vista de edicion de torneos
    @GetMapping("/torneos/editar/{id}")
    public ModelAndView mostrarFormularioEdicionTorneo(@PathVariable("id") Long id) {
        Torneo torneo = servicioAdmin.buscarTorneoPorId(id);

        if (torneo != null) {
            ModelAndView mav = new ModelAndView("admin/torneos");
            mav.addObject("torneo", torneo);
            mav.addObject("canchas", servicioAdmin.obtenerCanchas());
            mav.addObject("arbitros", servicioAdmin.obtenerArbitros());
            mav.addObject("torneos", servicioAdmin.obtenerTorneos());
            mav.addObject("partidosDeBsAs", PartidosDeBsAs.values());
            mav.addObject("editando", true);
            return mav;
        }

        return new ModelAndView("redirect:/admin/torneos");
    }

    // Controller para la edicion de torneos
    @PostMapping("/torneos/editar")
    public ModelAndView editarTorneo(@ModelAttribute("torneo") Torneo torneo) {
        try {
            servicioAdmin.guardarTorneo(torneo);
            return new ModelAndView("redirect:/admin/torneos?editado=true");
        } catch (TorneoExistente e) {
            return new ModelAndView("redirect:/admin/torneos?existente=true");
        }
    }

    // Controller para la vista de detalles de torneo
    @GetMapping("/torneos/{id}")
    public ModelAndView verTorneo(@PathVariable("id") Long id) {
        Torneo torneo = servicioAdmin.buscarTorneoPorId(id);

        if (torneo != null) {
            ModelAndView mav = new ModelAndView("admin/torneo");

            Integer cuposOcupados = torneo.getEquipos().size();
            Integer cuposDisponibles = torneo.getCantidadEquipos() - cuposOcupados;
            List<Partido> partidos = servicioAdmin.obtenerPartidosPorTorneo(torneo);

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
        Torneo torneo = servicioAdmin.buscarTorneoPorId(id);

        if (torneo != null) {
            List<Equipo> equipos = torneo.getEquipos();
            Integer cantidadEquiposInscriptos = equipos.size();

            // Verificar si la cantidad de equipos es menor que el número de equipos
            // inscriptos
            if (cantidadEquiposInscriptos < torneo.getCantidadEquipos()) {
                return new ModelAndView("redirect:/admin/torneos/" + id + "?faltanEquipos=true");
            }

            // Verificar si ya existen partidos asociados al torneo
            List<Partido> partidosExistentes = servicioAdmin.obtenerPartidosPorTorneo(torneo);
            if (!partidosExistentes.isEmpty()) {
                return new ModelAndView("redirect:/admin/torneos/" + id + "?partidosExistentes=true");
            }

            // Sortear equipos y guardar el torneo
            torneo.sortearEquipos();
            servicioAdmin.actualizarTorneo(torneo);

            // Crear partidos
            Integer cantidadPartidos = cantidadEquiposInscriptos / 2;
            for (Integer i = 0; i < cantidadPartidos; i++) {
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

                servicioAdmin.guardarPartido(partido);
            }

            return new ModelAndView("redirect:/admin/torneos/" + id + "?sorteado=true");
        }

        return new ModelAndView("redirect:/admin/torneos");
    }

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

    // Controller para la vista de gestion de partidos
    @GetMapping("/partidos")
    public ModelAndView gestionPartidos() {
        ModelAndView mav = new ModelAndView("admin/partidos");

        mav.addObject("partidos", servicioAdmin.obtenerPartidos());
        mav.addObject("editando", false);
        return mav;
    }

    // Controller para la vista de detalles del partido
    @GetMapping("/partidos/{id}")
    public ModelAndView verPartido(@PathVariable("id") Long id) {
        Partido partido = servicioAdmin.obtenerPartidoPorId(id);

        if (partido != null) {
            ModelAndView mav = new ModelAndView("admin/partido");
            List<Cancha> canchas = servicioAdmin.obtenerCanchas();
            List<Arbitro> arbitros = servicioAdmin.obtenerArbitros();

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
        Partido partido = servicioAdmin.obtenerPartidoPorId(partidoId);

        if (partido != null) {
            if (partido.estaFinalizado()) {
                return new ModelAndView("redirect:/admin/partidos/" + partidoId + "?estaFinalizado=true");
            }

            if (partido.getEquipoVisitante() == null) {
                return new ModelAndView("redirect:/admin/partidos/" + partidoId + "?sinEquipoVisitante=true");
            }

            partido.jugarPartido();

            // Verificar si hay un partido pendiente de asignar equipo visitante
            Torneo torneo = partido.getTorneo();
            Partido partidoEsperandoRival = servicioAdmin.obtenerPartidoEsperandoRival(torneo,
                    partido.avanzarFase(partido.getFase()));

            if (partidoEsperandoRival != null) {
                // Asignar el ganador al equipo visitante en el partido existente
                partidoEsperandoRival.setEquipoVisitante(partido.getGanador());
                servicioAdmin.guardarPartido(partidoEsperandoRival);
            } else {
                // Si la fase es la final, asignar el ganador al torneo
                if (!partido.getFase().equals("Final")) {
                    // Si no hay partido esperando rival y la fase no es la final, crear un nuevo
                    Partido nuevoPartido = new Partido();
                    nuevoPartido.setTorneo(torneo);
                    nuevoPartido.setEquipoLocal(partido.getGanador());
                    nuevoPartido.setFase(partido.avanzarFase(partido.getFase()));
                    servicioAdmin.guardarPartido(nuevoPartido);
                }
            }

            servicioAdmin.guardarPartido(partido);
            servicioAdmin.actualizarTorneo(torneo);
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
        Partido partido = servicioAdmin.obtenerPartidoPorId(id);

        if (partido != null) {
            Cancha cancha = servicioAdmin.obtenerCanchaPorId(canchaId);
            Arbitro arbitro = servicioAdmin.obtenerArbitroPorId(arbitroId);

            if (cancha != null && arbitro != null) {
                partido.setFecha(fecha);
                partido.setHora(hora);
                partido.setCancha(cancha);
                partido.setArbitro(arbitro);

                servicioAdmin.guardarPartido(partido);
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
        Partido partido = servicioAdmin.obtenerPartidoPorId(id);

        if (partido != null) {
            if (partido.getEquipoVisitante() == null) {
                return new ModelAndView("redirect:/admin/partidos/" + id + "?sinEquipoVisitante=true");
            }

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
            Partido partidoEsperandoRival = servicioAdmin.obtenerPartidoEsperandoRival(
                    torneo,
                    partido.avanzarFase(partido.getFase()));

            if (partidoEsperandoRival != null) {
                // Asignar el ganador al equipo visitante en el partido existente
                partidoEsperandoRival.setEquipoVisitante(partido.getGanador());
                servicioAdmin.guardarPartido(partidoEsperandoRival);
            } else {
                // Si la fase es la final, asignar el ganador al torneo
                if (!partido.getFase().equals("Final")) {
                    // Si no hay partido esperando rival y la fase no es la final, crear un nuevo
                    Partido nuevoPartido = new Partido();
                    nuevoPartido.setTorneo(torneo);
                    nuevoPartido.setEquipoLocal(partido.getGanador());
                    nuevoPartido.setFase(partido.avanzarFase(partido.getFase()));
                    servicioAdmin.guardarPartido(nuevoPartido);
                }
            }

            servicioAdmin.guardarPartido(partido);
            servicioAdmin.actualizarTorneo(torneo);
        }

        return new ModelAndView("redirect:/admin/partidos/" + id);
    }

    // Controller para la vista de gestion de Sanciones
    @GetMapping("/sanciones")
    public ModelAndView gestionSanciones() {
        ModelAndView mav = new ModelAndView("admin/sanciones");
        mav.addObject("equipos", servicioEquipo.obtenerEquipos());
        mav.addObject("jugadores", servicioJugador.obtenerJugadores());
        mav.addObject("jugadoresConSancion",servicioJugador.obtenerJugadoresConSancion());
        return mav;
    }

    // Metodo para obtener los jugadores de un equipo
    @GetMapping("/sanciones/jugadores/{id}")
    @ResponseBody
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable Long id) throws EquipoInexistente {
        Equipo equipo = servicioEquipo.buscarEquipoPorId(id);
        List<Jugador> jugadores = servicioJugador.obtenerJugadoresPorEquipo(equipo);
        return ResponseEntity.ok(jugadores);
    }

    // Controller para asignar sancion a un jugador
    @PostMapping("/sanciones/asignar")
    public ModelAndView asignarSancion(
            @RequestParam("equipo") Long equipoId,
            @RequestParam("jugador") Long jugadorId,
            @RequestParam("sancion") String sancion) throws EquipoInexistente, JugadorInexistente, JugadorExistente {
        Equipo equipo = servicioEquipo.buscarEquipoPorId(equipoId);
        Jugador jugador = servicioJugador.buscarJugador(jugadorId);

        if (equipo != null && jugador != null) {
            if (sancion.equals("Amarilla")) {
                jugador.setTarjetaAmarilla(true);
            } else if (sancion.equals("Roja")) {
                jugador.setTarjetaRoja(true);
            }

            servicioJugador.actualizarJugador(jugador);
        }

        return new ModelAndView("redirect:/admin/sanciones?asignada=true");
    }

    // Controller para quitar sancion a un jugador
    @GetMapping("/sanciones/quitar/{id}")
    public ModelAndView quitarSancion(@PathVariable("id") Long id) throws JugadorInexistente {
        Jugador jugador = servicioJugador.buscarJugador(id);

        if (jugador != null) {
            jugador.setTarjetaAmarilla(false);
            jugador.setTarjetaRoja(false);
            servicioJugador.actualizarJugador(jugador);
        }

        return new ModelAndView("redirect:/admin/sanciones?quitada=true");
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