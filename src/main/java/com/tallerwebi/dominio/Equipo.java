package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private Long id;
    private String nombre;
    private String cbu;
    private Long dtDni;

    @ManyToOne
    private Torneo torneo;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER)
    private List<Jugador> jugadores;


    @Column(name = "orden")
    private Integer orden;



    public Equipo(String nombre, String cbu, Long dni) {
            this.nombre = nombre;
            this.cbu = cbu;
            this.dtDni = dni;
            this.jugadores = new ArrayList<>();
            this.orden = 0;
            this.torneo = new Torneo();
        }

    public Equipo() {
        this.jugadores = new ArrayList<>();
        this.orden = 0;
        this.torneo = new Torneo();

    }

        public Long getId() {
        return id;
    }


        public void setId(Long id) {
        this.id = id;}


        public List<Jugador> getJugadores () {
            return jugadores;
        }

        public void setJugadores (List < Jugador > jugadores) {
            this.jugadores = jugadores;

        }

        public void agregarJugador (Jugador jugador){
            if (this.jugadores == null) {
                this.jugadores = new ArrayList<>();
            }
            this.jugadores.add(jugador);
        }

        public String getCbu () {
            return cbu;
        }

        public void setCbu (String cbu){
            this.cbu = cbu;

        }

        public String getNombre () {
            return nombre;
        }

        public void setNombre (String nombre){
            this.nombre = nombre;
        }


        public Long getDtDni () {
            return dtDni;
        }

        public void setDtDni (Long dtDni){
            this.dtDni = dtDni;
        }

        public Torneo getTorneo () {
            return torneo;
        }

        public void setTorneo (Torneo torneo){
            this.torneo = torneo;
        }

        public Integer getOrden () {
            return orden;
        }

        public void setOrden (Integer orden){
            this.orden = orden;
        }

    }