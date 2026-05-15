package com.example.mspagos.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String codigoTransaccion;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private Integer numeroCuotas;

    @Column(nullable = false)
    private boolean pagado;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @Column(nullable = false)
    private Integer reservaId;

    // Metodos generados sin Lombok
    public Pago() {
    }

    public Pago(Integer id, String codigoTransaccion, String metodoPago, BigDecimal monto, Integer numeroCuotas, boolean pagado, LocalDateTime fechaPago, Integer reservaId) {
        this.id = id;
        this.codigoTransaccion = codigoTransaccion;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.numeroCuotas = numeroCuotas;
        this.pagado = pagado;
        this.fechaPago = fechaPago;
        this.reservaId = reservaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }
}
