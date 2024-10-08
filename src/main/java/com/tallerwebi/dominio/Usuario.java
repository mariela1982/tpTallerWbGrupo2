package com.tallerwebi.dominio;

import com.tallerwebi.dominio.enums.Localidades;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String email;
    private String password;
    private Boolean admin = false;
    private Integer saldo = 0;

    @Enumerated(EnumType.STRING)
    private PartidosDeBsAs partido;


//    @Enumerated(EnumType.STRING)
//    private Localidades localidad;

    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String dni, String direccion, String email, String password, PartidosDeBsAs partido) {
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
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
}
