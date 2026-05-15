package com.example.msreservas.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.math.BigDecimal;

public class ReservaRequestDTO {
    @NotBlank
    @Size(min = 4, max = 30)
    private String codigo;

    @NotNull
    @Future
    private LocalDate fechaInicio;

    @NotNull
    @Future
    private LocalDate fechaFin;

    @NotNull
    @Positive
    private Integer diasArriendo;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal montoTotal;

    private boolean activa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

    @NotNull
    @Positive
    private Integer clienteId;

    @NotNull
    @Positive
    private Integer vehiculoId;

    @NotNull
    @Positive
    private Integer estadoReservaId;

    // Metodos generados sin Lombok
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(Integer diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getEstadoReservaId() {
        return estadoReservaId;
    }

    public void setEstadoReservaId(Integer estadoReservaId) {
        this.estadoReservaId = estadoReservaId;
    }
}
