package com.example.msvehiculos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VehiculoDTO {
    private Integer id;
    private String patente;
    private String marca;
    private String modelo;
    private Integer anio;
    private BigDecimal precioArriendoDiario;
    private boolean disponible;
    private LocalDate fechaIngreso;
    private Integer categoriaId;

    // Metodos generados sin Lombok
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
