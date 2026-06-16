package com.example.msclientes.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClienteRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 80)
    private String apellido;

    @NotBlank
    @Size(min = 2, max = 80)
    private String nacionalidad;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String telefono;

    @NotNull
    @Min(0)
    private Integer puntosFidelidad;

    private boolean activo;

    @NotNull
    @PastOrPresent
    private LocalDate fechaRegistro;

    // Metodos generados sin Lombok
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Integer puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
