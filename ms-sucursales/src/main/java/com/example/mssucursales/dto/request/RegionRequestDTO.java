package com.example.mssucursales.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class RegionRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 10)
    private String codigo;

    @NotNull
    @Positive
    private Integer numeroRegion;

    private boolean activa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

    @NotNull
    @Positive
    private Integer cantidadComunas;

    // Metodos generados sin Lombok
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
