package com.tallerwebi.dominio.entidades;

import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private Long dni;
    private String direccion;
    private String email;
    private String password;
    private Boolean admin = false;
    private Integer saldo = 0;

    @Enumerated(EnumType.STRING)
    private PartidosDeBsAs partido;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TorneoPago> torneosInscriptos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Equipo> equipos = new ArrayList<>();




//    @Enumerated(EnumType.STRING)
//    private Localidades localidad;

    public Usuario() {

    }

    public Usuario(String nombre, String apellido, Long dni, String direccion, String email, String password, PartidosDeBsAs partido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.admin = false;
        this.saldo = 0;
        this.partido = partido;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }



    public Integer getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public PartidosDeBsAs getPartido() {
        return partido;
    }

    public void setPartido(PartidosDeBsAs partido) {
        this.partido = partido;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public List<TorneoPago> getTorneosInscriptos() {
        return torneosInscriptos;
    }

    public void setTorneosInscriptos(List<TorneoPago> torneosPagos) {
        this.torneosInscriptos = torneosPagos;
    }

    public void agregarTorneoPago(TorneoPago torneoPago) {
        this.torneosInscriptos.add(torneoPago);
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
    }
}
