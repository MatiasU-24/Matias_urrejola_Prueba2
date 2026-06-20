package com.example.msreservas.service;

import com.example.msreservas.dto.response.EstadoReservaDTO;
import com.example.msreservas.entity.EstadoReserva;
import com.example.msreservas.exception.ResourceNotFoundException;
import com.example.msreservas.repository.EstadoReservaRepository;
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
class EstadoReservaServiceTest {
    @Mock
    private EstadoReservaRepository estadoReservaRepository;

    @InjectMocks
    private EstadoReservaService estadoReservaService;

    @Test
    void findAllRetornaEstados() {
        // Given
        when(estadoReservaRepository.findAll()).thenReturn(List.of(estado()));

        // When
        List<EstadoReservaDTO> resultado = estadoReservaService.findAll();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("Confirmada", resultado.get(0).getNombre());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(estadoReservaRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> estadoReservaService.findById(99));
    }

    private EstadoReserva estado() {
        return new EstadoReserva(1, "Confirmada", "Reserva confirmada", 1, true, LocalDate.now(), 1);
    }
}
