package com.tallerwebi.dominio;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@DynamicUpdate
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =30,nullable = false)
    private String nombre;

    @Column(length =30,nullable = false)
    private String apellido;

    @Column(length =30,nullable = false,unique = true,updatable = false)
    private String dni;

    @Column(nullable = false)
    private String direccion;

    @Column(length =30,nullable = false,unique = true,updatable = false)
    private String email;

    @Column(length =15,nullable = false)
    private String password;

    @Column(length =30,nullable = false)
    private Boolean admin;

    public Usuario() {
        this.admin = false;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}

    public String getApellido() { return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDni() { return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getDireccion() { return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

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

    public Boolean getAdmin() {return admin;}
    public void setAdmin(Boolean admin) {this.admin = admin;}

}
