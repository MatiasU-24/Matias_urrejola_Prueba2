package com.example.mssucursales.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sucursales")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private Integer capacidadVehiculos;

    @Column(nullable = false)
    private boolean operativa;

    @Column(nullable = false)
    private LocalDate fechaApertura;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    // Metodos generados sin Lombok
    public Sucursal() {
    }

    public Sucursal(Integer id, String nombre, String direccion, String telefono, Integer capacidadVehiculos, boolean operativa, LocalDate fechaApertura, Region region) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.capacidadVehiculos = capacidadVehiculos;
        this.operativa = operativa;
        this.fechaApertura = fechaApertura;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
