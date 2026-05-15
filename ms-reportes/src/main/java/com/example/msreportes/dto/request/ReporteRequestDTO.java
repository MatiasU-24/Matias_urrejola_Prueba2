package com.example.msreportes.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReporteRequestDTO {
    @NotBlank
    @Size(min = 4, max = 100)
    private String titulo;

    @NotBlank
    @Size(min = 10, max = 250)
    private String descripcion;

    @NotNull
    @Min(0)
    private Integer totalReservas;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalIngresos;

    private boolean publicado;

    @NotNull
    @PastOrPresent
    private LocalDateTime fechaGeneracion;

    @NotBlank
    @Size(min = 3, max = 60)
    private String tipoReporte;

    // Metodos generados sin Lombok
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTotalReservas() {
        return totalReservas;
    }

    public void setTotalReservas(Integer totalReservas) {
        this.totalReservas = totalReservas;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
}
