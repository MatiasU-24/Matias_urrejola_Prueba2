package com.example.msreservas.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private Integer diasArriendo;

    @Column(nullable = false)
    private BigDecimal montoTotal;

    @Column(nullable = false)
    private boolean activa;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private Integer clienteId;

    @Column(nullable = false)
    private Integer vehiculoId;

    @ManyToOne
    @JoinColumn(name = "estadoReserva_id", nullable = false)
    private EstadoReserva estadoReserva;

    // Metodos generados sin Lombok
    public Reserva() {
    }

    public Reserva(Integer id, String codigo, LocalDate fechaInicio, LocalDate fechaFin, Integer diasArriendo, BigDecimal montoTotal, boolean activa, LocalDate fechaCreacion, Integer clienteId, Integer vehiculoId, EstadoReserva estadoReserva) {
        this.id = id;
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasArriendo = diasArriendo;
        this.montoTotal = montoTotal;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.estadoReserva = estadoReserva;
    }

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

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}
