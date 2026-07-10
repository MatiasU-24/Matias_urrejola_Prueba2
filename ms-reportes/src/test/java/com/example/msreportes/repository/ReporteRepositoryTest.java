package com.example.msreportes.repository;

import com.example.msreportes.entity.Reporte;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class ReporteRepositoryTest {
    @Autowired
    private ReporteRepository reporteRepository;

    @Test
    void savePermiteConsultarReporte() {
        Reporte reporte = new Reporte(null, "Reporte mensual", "Resumen mensual de ingresos", 10,
                new BigDecimal("1000000.00"), true, LocalDateTime.now(), "Mensual");

        Reporte guardado = reporteRepository.save(reporte);

        assertTrue(reporteRepository.findById(guardado.getId()).isPresent());
    }
}
