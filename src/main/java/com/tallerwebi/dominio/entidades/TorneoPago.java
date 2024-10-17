package com.tallerwebi.dominio.entidades;

import com.tallerwebi.dominio.enums.TipoDePago;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
public class TorneoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = true)
    private Date fechaDeCreacion;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Torneo torneoApagar;

    @Column(nullable = false)
    private TipoDePago tipoDePago;

    @Column(nullable = false)
    private Integer total;

    public TorneoPago() {
        this.fechaDeCreacion = new Date();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public TipoDePago getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(TipoDePago tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }


}
