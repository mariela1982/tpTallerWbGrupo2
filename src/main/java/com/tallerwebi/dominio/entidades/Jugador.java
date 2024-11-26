package com.tallerwebi.dominio.entidades;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreYapellido;
    private Date fechaNacimiento;
    private String posicion;
    private String dni;
    private String direccion;
    private String email;
    private String telefono;
    private Integer numeroCamiseta;
    private Boolean tarjetaAmarilla;
    private Boolean tarjetaRoja;



    @ManyToOne
    @JoinColumn(name = "equipo_id")
    @JsonIgnore
    private Equipo equipo = null;

    @ManyToOne
    @JoinColumn(name = "dt_id")
    @JsonIgnore
    private Usuario directorTecnico = null;


    public Jugador(String nombre, Date fechaNacimiento, String posicion, String dni, String direccion, String email, String telefono, Integer numeroCamiseta) {
        this.nombreYapellido = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.posicion = posicion;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.numeroCamiseta = numeroCamiseta;
        this.tarjetaAmarilla = false;
        this.tarjetaRoja = false;
        this.directorTecnico = new Usuario();
    }

    public Jugador() {

    }

    public Long getId() {
        return id;
    }



    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }


    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @JsonIgnore
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }


    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getTarjetaAmarilla() {
        return tarjetaAmarilla;
    }

    public void setTarjetaAmarilla(Boolean tarjetaAmarilla) {
        this.tarjetaAmarilla = tarjetaAmarilla;
    }

    public Boolean getTarjetaRoja() {
        return tarjetaRoja;
    }

    public void setTarjetaRoja(Boolean tarjetaRoja) {
        this.tarjetaRoja = tarjetaRoja;
    }

    public Usuario getDirectorTecnico() {
        return directorTecnico;
    }
    public void setDirectorTecnico(Usuario directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNumeroCamiseta(int i) {
        this.numeroCamiseta = i;
    }
}
