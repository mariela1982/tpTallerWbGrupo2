package com.tallerwebi.dominio;

import com.tallerwebi.dominio.enums.PartidosDeBsAs;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaNacimiento;
    private String posicion;
    private String dni;
   /*private String email;
    private String password;
    */
    private PartidosDeBsAs partido;




    @ManyToOne
    private Equipo equipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }



    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getDni() {
        return dni;
    }

/*
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
*/
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPartido(PartidosDeBsAs partidosDeBsAs) {
        this.partido = partidosDeBsAs;
    }

    public PartidosDeBsAs getPartido() {return partido;}
}
