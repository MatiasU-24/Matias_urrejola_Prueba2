package com.example.msempleados.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoRequestDTO {
    @NotBlank
    @Size(min = 8, max = 12)
    private String rut;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal sueldo;

    @NotNull
    @Positive
    private Integer horasSemanales;

    private boolean activo;

    @NotNull
    @PastOrPresent
    private LocalDate fechaContratacion;

    @NotBlank
    @Size(min = 3, max = 80)
    private String cargo;

    // Metodos generados sin Lombok
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
