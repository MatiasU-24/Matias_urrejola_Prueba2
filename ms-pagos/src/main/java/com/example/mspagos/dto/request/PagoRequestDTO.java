package com.example.mspagos.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoRequestDTO {
    @NotBlank
    @Size(min = 5, max = 80)
    private String codigoTransaccion;

    @NotBlank
    @Size(min = 3, max = 40)
    private String metodoPago;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal monto;

    @NotNull
    @Positive
    private Integer numeroCuotas;

    private boolean pagado;

    @NotNull
    @PastOrPresent
    private LocalDateTime fechaPago;

    @NotNull
    @Positive
    private Integer reservaId;

    // Metodos generados sin Lombok
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
