package com.example.msreservas.dto.response;

import java.time.LocalDate;

public class EstadoReservaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer prioridad;
    private boolean permiteCancelacion;
    private LocalDate fechaCreacion;
    private Integer ordenVisual;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isPermiteCancelacion() {
        return permiteCancelacion;
    }

    public void setPermiteCancelacion(boolean permiteCancelacion) {
        this.permiteCancelacion = permiteCancelacion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getOrdenVisual() {
        return ordenVisual;
    }

    public void setOrdenVisual(Integer ordenVisual) {
        this.ordenVisual = ordenVisual;
    }
}
