package com.tallerwebi.dominio;

import com.tallerwebi.dominio.enums.PartidosDeBsAs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Equipo {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;
    private String logo;
    private String nombre;
    private String cbu;
    private Long dtDni;
    private PartidosDeBsAs partido;


    @OneToMany
    private List<Jugador> jugadores;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public PartidosDeBsAs getPartido() {
        return partido;
    }

    public void setPartido(PartidosDeBsAs partido) {
        this.partido = partido;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", cbu='" + cbu + '\'' +
                ", dtDni=" + dtDni +
                ", idEquipo=" + idEquipo +
                '}';
    }
}
