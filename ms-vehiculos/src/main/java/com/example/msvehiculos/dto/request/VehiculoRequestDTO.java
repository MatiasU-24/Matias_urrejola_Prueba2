package com.example.msvehiculos.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class VehiculoRequestDTO {
    @NotBlank
    @Size(min = 6, max = 10)
    private String patente;

    @NotBlank
    @Size(min = 2, max = 60)
    private String marca;

    @NotBlank
    @Size(min = 2, max = 60)
    private String modelo;

    @NotNull
    @Min(1990)
    private Integer anio;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precioArriendoDiario;

    private boolean disponible;

    @NotNull
    @PastOrPresent
    private LocalDate fechaIngreso;

    @NotNull
    @Positive
    private Integer categoriaId;

    // Metodos generados sin Lombok
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getPrecioArriendoDiario() {
        return precioArriendoDiario;
    }

    public void setPrecioArriendoDiario(BigDecimal precioArriendoDiario) {
        this.precioArriendoDiario = precioArriendoDiario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
}
