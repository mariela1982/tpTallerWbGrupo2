package com.tallerwebi.dominio;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer golesLocal;
    private Integer golesVisitante;
    private Date fecha;
    private LocalTime hora;
    private String fase;

    @ManyToOne
    @JoinColumn(name = "equipoLocal_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipoVisitante_id")
    private Equipo equipoVisitante;

    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

    @ManyToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro arbitro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public void jugarPartido() {
        do {
            this.golesLocal = (int) (Math.random() * 5);
            this.golesVisitante = (int) (Math.random() * 5);
        } while (this.golesLocal.equals(this.golesVisitante));
    }

    public boolean estaFinalizado() {
        return this.golesLocal != null && this.golesVisitante != null;
    }

    public Equipo getGanador() {
        if (this.estaFinalizado()) {
            return this.golesLocal > this.golesVisitante ? this.equipoLocal : this.equipoVisitante;
        }
        return null;
    }

    public Equipo getPerdedor() {
        if (this.estaFinalizado()) {
            return this.golesLocal < this.golesVisitante ? this.equipoLocal : this.equipoVisitante;
        }
        return null;
    }

    public String avanzarFase(String faseActual) {
        switch (faseActual) {
            case "Octavos de Final":
                return "Cuartos de Final";
            case "Cuartos de Final":
                return "Semifinal";
            case "Semifinal":
                return "Final";
            case "Final":
                return "Campeon";
            default:
                return faseActual;
        }
    }
    
}
