package com.example.msvehiculos.service;

import com.example.msvehiculos.dto.request.VehiculoRequestDTO;
import com.example.msvehiculos.dto.response.VehiculoDTO;
import com.example.msvehiculos.entity.Categoria;
import com.example.msvehiculos.entity.Vehiculo;
import com.example.msvehiculos.repository.CategoriaRepository;
import com.example.msvehiculos.repository.VehiculoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehiculoServiceTest {
    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private VehiculoService vehiculoService;

    @Test
    void saveAsociaCategoriaExistente() {
        // Given
        Categoria categoria = categoria();
        VehiculoRequestDTO request = vehiculoRequest();
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenAnswer(invocation -> {
            Vehiculo vehiculo = invocation.getArgument(0);
            vehiculo.setId(5);
            return vehiculo;
        });

        // When
        VehiculoDTO resultado = vehiculoService.save(request);

        // Then
        assertEquals(5, resultado.getId());
        assertEquals(1, resultado.getCategoriaId());
    }

    @Test
    void findDisponiblesPorPrecioRetornaVehiculos() {
        // Given
        when(vehiculoRepository.findByDisponibleTrueAndPrecioArriendoDiarioLessThan(new BigDecimal("60000")))
                .thenReturn(List.of(vehiculo()));

        // When
        List<VehiculoDTO> resultado = vehiculoService.findDisponiblesPorPrecio(new BigDecimal("60000"));

        // Then
        assertEquals(1, resultado.size());
        assertEquals("ABCD12", resultado.get(0).getPatente());
    }

    private Categoria categoria() {
        return new Categoria(1, "SUV", "Vehiculos amplios", new BigDecimal("54990"), 7, true, LocalDate.now());
    }

    private Vehiculo vehiculo() {
        return new Vehiculo(5, "ABCD12", "Toyota", "Rav4", 2024, new BigDecimal("59000"), true, LocalDate.now(), categoria());
    }

    private VehiculoRequestDTO vehiculoRequest() {
        VehiculoRequestDTO request = new VehiculoRequestDTO();
        request.setPatente("ABCD12");
        request.setMarca("Toyota");
        request.setModelo("Rav4");
        request.setAnio(2024);
        request.setPrecioArriendoDiario(new BigDecimal("59000"));
        request.setDisponible(true);
        request.setFechaIngreso(LocalDate.now());
        request.setCategoriaId(1);
        return request;
    }
}
