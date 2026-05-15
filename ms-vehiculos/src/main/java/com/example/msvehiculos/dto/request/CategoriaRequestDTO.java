package com.example.msvehiculos.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CategoriaRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 10, max = 200)
    private String descripcion;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal tarifaBase;

    @NotNull
    @Positive
    private Integer capacidadPasajeros;

    private boolean activa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

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

    public BigDecimal getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(BigDecimal tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
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
}
