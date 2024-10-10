package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    private String nombre;
    private String apellido;
    private String posicion;
    private PartidosDeBsAs partido;
    private String sancion;
=======
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

>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;


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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public PartidosDeBsAs getPartido() {
        return partido;
    }

    public void setPartido(PartidosDeBsAs partido) {
        this.partido = partido;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    @JsonIgnore
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

<<<<<<< HEAD
=======
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




>>>>>>> ad86a6bf20047f5053dcd25662c3169ea931aa4b
}
