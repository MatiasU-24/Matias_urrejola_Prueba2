package com.example.msreservas.service;

import com.example.msreservas.client.ClienteClient;
import com.example.msreservas.client.VehiculoClient;
import com.example.msreservas.client.dto.ClienteDTO;
import com.example.msreservas.dto.response.ReservaDTO;
import com.example.msreservas.entity.EstadoReserva;
import com.example.msreservas.entity.Reserva;
import com.example.msreservas.repository.EstadoReservaRepository;
import com.example.msreservas.repository.ReservaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private EstadoReservaRepository estadoReservaRepository;

    @Mock
    private ClienteClient clienteClient;

    @Mock
    private VehiculoClient vehiculoClient;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void findByIdRetornaReservaConClienteFeign() {
        // Given
        Reserva reserva = reserva();
        ClienteDTO cliente = cliente();
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(clienteClient.obtenerClientePorId(1)).thenReturn(cliente);

        // When
        ReservaDTO resultado = reservaService.findById(1);

        // Then
        assertEquals(1, resultado.getId());
        assertEquals("Ana", resultado.getCliente().getNombre());
    }

    private Reserva reserva() {
        EstadoReserva estado = new EstadoReserva(1, "Confirmada", "Reserva confirmada", 1, true, LocalDate.now(), 1);
        return new Reserva(1, "RES-1001", LocalDate.now().plusDays(5), LocalDate.now().plusDays(8), 3,
                new BigDecimal("96000"), true, LocalDate.now(), 1, 1, estado);
    }

    private ClienteDTO cliente() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(1);
        cliente.setNombre("Ana");
        cliente.setApellido("Lopez");
        cliente.setEmail("ana.lopez@mail.com");
        cliente.setActivo(true);
        return cliente;
    }
}
