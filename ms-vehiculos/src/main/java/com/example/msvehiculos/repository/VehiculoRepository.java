package com.example.msvehiculos.repository;

import com.example.msvehiculos.entity.Vehiculo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByDisponibleTrueAndPrecioArriendoDiarioLessThan(BigDecimal precio);
}
