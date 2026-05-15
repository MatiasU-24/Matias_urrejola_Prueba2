package com.example.msvehiculos.config;

import com.example.msvehiculos.entity.Categoria;
import com.example.msvehiculos.entity.Vehiculo;
import com.example.msvehiculos.repository.CategoriaRepository;
import com.example.msvehiculos.repository.VehiculoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(CategoriaRepository categoriaRepository, VehiculoRepository vehiculoRepository) {
        return args -> {
            if (categoriaRepository.count() == 0) {
                Categoria economico = categoriaRepository.save(new Categoria(null, "Economico", "Autos compactos de bajo consumo", new BigDecimal("29990"), 5, true, LocalDate.now().minusYears(2)));
                Categoria suv = categoriaRepository.save(new Categoria(null, "SUV", "Vehiculos amplios para familia y equipaje", new BigDecimal("54990"), 7, true, LocalDate.now().minusYears(2)));
                Categoria premium = categoriaRepository.save(new Categoria(null, "Premium", "Vehiculos de gama alta para viajes ejecutivos", new BigDecimal("89990"), 5, true, LocalDate.now().minusYears(1)));
                vehiculoRepository.save(new Vehiculo(null, "ABCD12", "Hyundai", "Accent", 2022, new BigDecimal("32000"), true, LocalDate.now().minusMonths(14), economico));
                vehiculoRepository.save(new Vehiculo(null, "EFGH34", "Toyota", "Rav4", 2023, new BigDecimal("59000"), true, LocalDate.now().minusMonths(10), suv));
                vehiculoRepository.save(new Vehiculo(null, "IJKL56", "BMW", "Serie 3", 2024, new BigDecimal("95000"), true, LocalDate.now().minusMonths(6), premium));
            }
        };
    }
}
