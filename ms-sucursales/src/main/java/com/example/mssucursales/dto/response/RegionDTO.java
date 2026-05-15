package com.example.mssucursales.dto.response;

import java.time.LocalDate;

public class RegionDTO {
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer numeroRegion;
    private boolean activa;
    private LocalDate fechaCreacion;
    private Integer cantidadComunas;

    // Metodos generados sin Lombok
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
