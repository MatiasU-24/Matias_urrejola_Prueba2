package com.example.mspagos.repository;

import com.example.mspagos.entity.Pago;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    @Query("SELECT p FROM Pago p WHERE p.monto BETWEEN :min AND :max ORDER BY p.fechaPago DESC")
    List<Pago> buscarPagosPorRangoMonto(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
