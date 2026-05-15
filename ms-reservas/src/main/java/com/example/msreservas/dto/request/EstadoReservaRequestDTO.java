package com.example.msreservas.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EstadoReservaRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 10, max = 200)
    private String descripcion;

    @NotNull
    @Positive
    private Integer prioridad;

    private boolean permiteCancelacion;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

    @NotNull
    @Min(1)
    private Integer ordenVisual;

    // Metodos generados sin Lombok
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
