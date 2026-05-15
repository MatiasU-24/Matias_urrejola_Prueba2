package com.example.msreservas.repository;

import com.example.msreservas.entity.Reserva;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query("SELECT r FROM Reserva r WHERE r.fechaInicio >= :fecha ORDER BY r.fechaInicio DESC")
    List<Reserva> buscarReservasDesdeFecha(@Param("fecha") LocalDate fecha);
}
