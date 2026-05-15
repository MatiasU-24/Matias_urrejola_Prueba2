package com.example.msreservas.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estados_reserva")
public class EstadoReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer prioridad;

    @Column(nullable = false)
    private boolean permiteCancelacion;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private Integer ordenVisual;

    @OneToMany(mappedBy = "estadoReserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public EstadoReserva(Integer id, String nombre, String descripcion, Integer prioridad,
            boolean permiteCancelacion, LocalDate fechaCreacion, Integer ordenVisual) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.permiteCancelacion = permiteCancelacion;
        this.fechaCreacion = fechaCreacion;
        this.ordenVisual = ordenVisual;
    }

    // Metodos generados sin Lombok
    public EstadoReserva() {
    }

    public EstadoReserva(Integer id, String nombre, String descripcion, Integer prioridad, boolean permiteCancelacion, LocalDate fechaCreacion, Integer ordenVisual, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.permiteCancelacion = permiteCancelacion;
        this.fechaCreacion = fechaCreacion;
        this.ordenVisual = ordenVisual;
        this.reservas = reservas;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isPermiteCancelacion() {
        return permiteCancelacion;
    }

    public void setPermiteCancelacion(boolean permiteCancelacion) {
        this.permiteCancelacion = permiteCancelacion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getOrdenVisual() {
        return ordenVisual;
    }

    public void setOrdenVisual(Integer ordenVisual) {
        this.ordenVisual = ordenVisual;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
