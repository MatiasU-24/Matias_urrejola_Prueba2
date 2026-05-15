package com.example.mssucursales.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SucursalRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 150)
    private String direccion;

    @NotBlank
    @Size(min = 8, max = 20)
    private String telefono;

    @NotNull
    @Positive
    private Integer capacidadVehiculos;

    private boolean operativa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaApertura;

    @NotNull
    @Positive
    private Integer regionId;

    // Metodos generados sin Lombok
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCapacidadVehiculos() {
        return capacidadVehiculos;
    }

    public void setCapacidadVehiculos(Integer capacidadVehiculos) {
        this.capacidadVehiculos = capacidadVehiculos;
    }

    public boolean isOperativa() {
        return operativa;
    }

    public void setOperativa(boolean operativa) {
        this.operativa = operativa;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
