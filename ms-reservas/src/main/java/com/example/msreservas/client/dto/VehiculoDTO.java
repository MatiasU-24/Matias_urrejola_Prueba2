package com.example.msreservas.client.dto;

import java.math.BigDecimal;

public class VehiculoDTO {
    private Integer id;
    private String patente;
    private BigDecimal precioArriendoDiario;
    private boolean disponible;

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
}
