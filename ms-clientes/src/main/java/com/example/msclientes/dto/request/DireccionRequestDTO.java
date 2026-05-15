package com.example.msclientes.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class DireccionRequestDTO {
    @NotBlank
    @Size(min = 3, max = 120)
    private String calle;

    @NotNull
    @Positive
    private Integer numero;

    @NotBlank
    @Size(min = 2, max = 80)
    private String comuna;

    @NotBlank
    @Size(min = 4, max = 12)
    private String codigoPostal;

    private boolean principal;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

    @NotNull
    @Positive
    private Integer clienteId;

    // Metodos generados sin Lombok
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
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
}
