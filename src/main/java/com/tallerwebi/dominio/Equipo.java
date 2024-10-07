package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;
    private String nombre;
    private String cbu;
    private Long dtDni;
    private List<Torneo> torneos;

    @OneToMany
    private List<Jugador> jugadores;

    public Equipo(String nombre, String cbu, Long dni){
        this.nombre = nombre;
        this.cbu = cbu;
        this.dtDni = dni;
        this.jugadores = new ArrayList<Jugador>();
        this.torneos = new ArrayList<>();

    }

    public Equipo() {

    }


    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDtDni() {
        return dtDni;
    }

    public void setDtDni(Long dtDni) {
        this.dtDni = dtDni;
    }

    public List<Torneo> getTorneo() {
        return torneos;
    }

    public void setTorneo(Torneo torneo) {
        torneos.add(torneo);
    }
}
