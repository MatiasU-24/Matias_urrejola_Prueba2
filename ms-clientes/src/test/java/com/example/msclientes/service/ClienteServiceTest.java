package com.example.msclientes.service;

import com.example.msclientes.dto.request.ClienteRequestDTO;
import com.example.msclientes.dto.response.ClienteDTO;
import com.example.msclientes.entity.Cliente;
import com.example.msclientes.exception.ResourceNotFoundException;
import com.example.msclientes.repository.ClienteRepository;
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
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void findAllRetornaClientes() {
        // Given
        when(clienteRepository.findAll()).thenReturn(List.of(cliente()));

        // When
        List<ClienteDTO> resultado = clienteService.findAll();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> clienteService.findById(99));
    }

    @Test
    void savePersisteClienteValidado() {
        // Given
        ClienteRequestDTO request = clienteRequest();
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente cliente = invocation.getArgument(0);
            cliente.setId(1);
            return cliente;
        });

        // When
        ClienteDTO resultado = clienteService.save(request);

        // Then
        assertEquals(1, resultado.getId());
        assertEquals("Chilena", resultado.getNacionalidad());
    }

    @Test
    void findByEmailRetornaCoincidencias() {
        // Given
        when(clienteRepository.findByEmailContainingIgnoreCase("ana")).thenReturn(List.of(cliente()));

        // When
        List<ClienteDTO> resultado = clienteService.findByEmail("ana");

        // Then
        assertEquals(1, resultado.size());
        assertEquals("ana.lopez@mail.com", resultado.get(0).getEmail());
    }

    private Cliente cliente() {
        return new Cliente(1, "Ana", "Lopez", "ana.lopez@mail.com", "912345678", 120, true, LocalDate.now(), "Chilena");
    }

    private ClienteRequestDTO clienteRequest() {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setNombre("Ana");
        request.setApellido("Lopez");
        request.setNacionalidad("Chilena");
        request.setEmail("ana.lopez@mail.com");
        request.setTelefono("912345678");
        request.setPuntosFidelidad(120);
        request.setActivo(true);
        request.setFechaRegistro(LocalDate.now());
        return request;
    }
}
