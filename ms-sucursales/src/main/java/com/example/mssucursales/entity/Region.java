package com.example.mssucursales.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "regiones")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Integer numeroRegion;

    @Column(nullable = false)
    private boolean activa;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private Integer cantidadComunas;

    // Metodos generados sin Lombok
    public Region() {
    }

    public Region(Integer id, String nombre, String codigo, Integer numeroRegion, boolean activa, LocalDate fechaCreacion, Integer cantidadComunas) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.numeroRegion = numeroRegion;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
        this.cantidadComunas = cantidadComunas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNumeroRegion() {
        return numeroRegion;
    }

    public void setNumeroRegion(Integer numeroRegion) {
        this.numeroRegion = numeroRegion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidadComunas() {
        return cantidadComunas;
    }

    public void setCantidadComunas(Integer cantidadComunas) {
        this.cantidadComunas = cantidadComunas;
    }
}
