package com.example.msreservas.dto.response;

import com.example.msreservas.client.dto.ClienteDTO;
import java.time.LocalDate;
import java.math.BigDecimal;

public class ReservaDTO {
    private Integer id;
    private String codigo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer diasArriendo;
    private BigDecimal montoTotal;
    private boolean activa;
    private LocalDate fechaCreacion;
    private Integer clienteId;
    private ClienteDTO cliente;
    private Integer vehiculoId;
    private Integer estadoReservaId;

    // Metodos generados sin Lombok
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
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
