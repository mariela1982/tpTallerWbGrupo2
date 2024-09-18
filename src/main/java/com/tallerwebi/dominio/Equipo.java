package com.tallerwebi.dominio;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipo {



    private String nombre;
    private String cbu;
    private Long dtDni;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;


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
