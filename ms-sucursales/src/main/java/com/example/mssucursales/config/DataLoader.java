package com.example.mssucursales.config;

import com.example.mssucursales.entity.Region;
import com.example.mssucursales.entity.Sucursal;
import com.example.mssucursales.repository.RegionRepository;
import com.example.mssucursales.repository.SucursalRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(RegionRepository regionRepository, SucursalRepository sucursalRepository) {
        return args -> {
            if (regionRepository.count() == 0) {
                Region metropolitana = regionRepository.save(new Region(null, "Metropolitana", "RM", 13, true, LocalDate.now().minusYears(10), 52));
                Region biobio = regionRepository.save(new Region(null, "Biobio", "BIO", 8, true, LocalDate.now().minusYears(10), 33));
                Region araucania = regionRepository.save(new Region(null, "Araucania", "ARA", 9, true, LocalDate.now().minusYears(10), 32));
                Region valparaiso = regionRepository.save(new Region(null, "Valparaiso", "VAL", 5, true, LocalDate.now().minusYears(10), 38));
                Region losLagos = regionRepository.save(new Region(null, "Los Lagos", "LAG", 10, true, LocalDate.now().minusYears(10), 30));
                sucursalRepository.save(new Sucursal(null, "Sucursal Santiago Centro", "Alameda 1500", "225551111", 80, true, LocalDate.now().minusYears(4), metropolitana));
                sucursalRepository.save(new Sucursal(null, "Sucursal Concepcion", "O'Higgins 640", "412221111", 45, true, LocalDate.now().minusYears(3), biobio));
                sucursalRepository.save(new Sucursal(null, "Sucursal Temuco", "Prat 320", "452331111", 35, true, LocalDate.now().minusYears(2), araucania));
                sucursalRepository.save(new Sucursal(null, "Sucursal Valparaiso", "Brasil 1200", "322441111", 40, true, LocalDate.now().minusYears(3), valparaiso));
                sucursalRepository.save(new Sucursal(null, "Sucursal Puerto Montt", "Urmeneta 450", "652551111", 30, false, LocalDate.now().minusYears(1), losLagos));
            }
        };
    }
}
