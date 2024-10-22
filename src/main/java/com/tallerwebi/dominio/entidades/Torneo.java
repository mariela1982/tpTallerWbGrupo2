package com.tallerwebi.dominio.entidades;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.tallerwebi.dominio.enums.PartidosDeBsAs;

@Entity
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer premio;
    private Integer precioEntrada;
    private Date fechaInicio;
    private Integer cantidadEquipos;
    private PartidosDeBsAs partido;

    @OneToMany(mappedBy = "torneo",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "orden")
    private List<Equipo> equipos = Collections.emptyList();

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

    public Integer getPremio() {
        return premio;
    }

    public void setPremio(Integer premio) {
        this.premio = premio;
    }

    public Integer getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(Integer precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(Integer cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public PartidosDeBsAs getPartido() {
        return partido;
    }

    public void setPartido(PartidosDeBsAs partido) {
        this.partido = partido;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void sortearEquipos() {
        if (equipos != null && !equipos.isEmpty()) {
            Collections.shuffle(equipos);

            for (int i = 0; i < equipos.size(); i++) {
                equipos.get(i).setOrden(i + 1);
            }
        }
    }

    public void agregarEquipo(Equipo equipo) {
        // if (equipos == null) {
        // equipos = Collections.emptyList();
        // }
        if (cantidadEquipos < equipos.size()) {
            equipos.add(equipo);
        }

    }

    public void eliminarEquipo(Equipo equipo) {
        if (equipos != null) {
            equipos.remove(equipo);
        }
    }

}