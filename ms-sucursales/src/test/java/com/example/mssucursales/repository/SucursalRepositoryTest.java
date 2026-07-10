package com.example.mssucursales.repository;

import com.example.mssucursales.entity.Region;
import com.example.mssucursales.entity.Sucursal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class SucursalRepositoryTest {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void listarSucursalesOperativasRetornaSoloOperativas() {
        Region region = regionRepository.save(new Region(null, "Metropolitana", "RM", 13, true, LocalDate.now(), 52));
        sucursalRepository.save(new Sucursal(null, "Sucursal Activa", "Alameda 1500", "225551111", 80, true, LocalDate.now(), region));
        sucursalRepository.save(new Sucursal(null, "Sucursal Cerrada", "Brasil 1200", "322441111", 20, false, LocalDate.now(), region));

        List<Sucursal> resultado = sucursalRepository.listarSucursalesOperativas();

        assertEquals(1, resultado.size());
        assertEquals("Sucursal Activa", resultado.get(0).getNombre());
    }
}
