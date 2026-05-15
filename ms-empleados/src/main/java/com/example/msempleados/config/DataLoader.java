package com.example.msempleados.config;

import com.example.msempleados.entity.Empleado;
import com.example.msempleados.repository.EmpleadoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(EmpleadoRepository empleadoRepository) {
        return args -> {
            if (empleadoRepository.count() == 0) {
                empleadoRepository.save(new Empleado(null, "11111111-1", "Daniel Perez", "daniel.perez@rentacar.cl", new BigDecimal("850000"), 44, true, LocalDate.of(2023, 3, 15), "Ejecutivo de ventas"));
                empleadoRepository.save(new Empleado(null, "22222222-2", "Maria Torres", "maria.torres@rentacar.cl", new BigDecimal("920000"), 44, true, LocalDate.of(2024, 1, 10), "Supervisora"));
                empleadoRepository.save(new Empleado(null, "33333333-3", "Felipe Castro", "felipe.castro@rentacar.cl", new BigDecimal("780000"), 40, true, LocalDate.of(2024, 6, 20), "Asistente operativo"));
            }
        };
    }
}
