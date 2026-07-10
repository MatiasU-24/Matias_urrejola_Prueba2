package com.example.mssucursales.service;

import com.example.mssucursales.dto.request.SucursalRequestDTO;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.entity.Region;
import com.example.mssucursales.entity.Sucursal;
import com.example.mssucursales.exception.ResourceNotFoundException;
import com.example.mssucursales.repository.RegionRepository;
import com.example.mssucursales.repository.SucursalRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SucursalServiceTest {
    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @Test
    void findOperativasRetornaSucursalesActivas() {
        when(sucursalRepository.listarSucursalesOperativas()).thenReturn(List.of(sucursal()));

        List<SucursalDTO> resultado = sucursalService.findOperativas();

        assertEquals(1, resultado.size());
        assertEquals("Sucursal Santiago Centro", resultado.get(0).getNombre());
    }

    @Test
    void saveAsociaRegionExistente() {
        when(regionRepository.findById(1)).thenReturn(Optional.of(region()));
        when(sucursalRepository.save(any(Sucursal.class))).thenAnswer(invocation -> {
            Sucursal sucursal = invocation.getArgument(0);
            sucursal.setId(10);
            return sucursal;
        });

        SucursalDTO resultado = sucursalService.save(request());

        assertEquals(10, resultado.getId());
        assertEquals(1, resultado.getRegionId());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        when(sucursalRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> sucursalService.findById(99));
    }

    private SucursalRequestDTO request() {
        SucursalRequestDTO request = new SucursalRequestDTO();
        request.setNombre("Sucursal Santiago Centro");
        request.setDireccion("Alameda 1500");
        request.setTelefono("225551111");
        request.setCapacidadVehiculos(80);
        request.setOperativa(true);
        request.setFechaApertura(LocalDate.now().minusYears(4));
        request.setRegionId(1);
        return request;
    }

    private Sucursal sucursal() {
        return new Sucursal(10, "Sucursal Santiago Centro", "Alameda 1500", "225551111", 80, true, LocalDate.now().minusYears(4), region());
    }

    private Region region() {
        return new Region(1, "Metropolitana", "RM", 13, true, LocalDate.now().minusYears(10), 52);
    }
}
