package com.example.msclientes.service;

import com.example.msclientes.dto.request.DireccionRequestDTO;
import com.example.msclientes.dto.response.DireccionDTO;
import com.example.msclientes.entity.Cliente;
import com.example.msclientes.entity.Direccion;
import com.example.msclientes.repository.ClienteRepository;
import com.example.msclientes.repository.DireccionRepository;
import java.time.LocalDate;
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
class DireccionServiceTest {
    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private DireccionService direccionService;

    @Test
    void saveAsociaClienteExistente() {
        // Given
        Cliente cliente = cliente();
        DireccionRequestDTO request = direccionRequest();
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(direccionRepository.save(any(Direccion.class))).thenAnswer(invocation -> {
            Direccion direccion = invocation.getArgument(0);
            direccion.setId(10);
            return direccion;
        });

        // When
        DireccionDTO resultado = direccionService.save(request);

        // Then
        assertEquals(10, resultado.getId());
        assertEquals(1, resultado.getClienteId());
    }

    private Cliente cliente() {
        return new Cliente(1, "Ana", "Lopez", "ana.lopez@mail.com", "912345678", 120, true, LocalDate.now(), "Chilena");
    }

    private DireccionRequestDTO direccionRequest() {
        DireccionRequestDTO request = new DireccionRequestDTO();
        request.setCalle("Av Providencia");
        request.setNumero(1200);
        request.setComuna("Providencia");
        request.setCodigoPostal("7500000");
        request.setPrincipal(true);
        request.setFechaCreacion(LocalDate.now());
        request.setClienteId(1);
        return request;
    }
}
