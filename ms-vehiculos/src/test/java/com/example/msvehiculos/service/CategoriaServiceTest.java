package com.example.msvehiculos.service;

import com.example.msvehiculos.dto.response.CategoriaDTO;
import com.example.msvehiculos.entity.Categoria;
import com.example.msvehiculos.exception.ResourceNotFoundException;
import com.example.msvehiculos.repository.CategoriaRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void findAllRetornaCategorias() {
        // Given
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria()));

        // When
        List<CategoriaDTO> resultado = categoriaService.findAll();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("SUV", resultado.get(0).getNombre());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> categoriaService.findById(99));
    }

    private Categoria categoria() {
        return new Categoria(1, "SUV", "Vehiculos amplios", new BigDecimal("54990"), 7, true, LocalDate.now());
    }
}
