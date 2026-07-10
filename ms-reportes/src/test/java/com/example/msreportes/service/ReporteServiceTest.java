package com.example.msreportes.service;

import com.example.msreportes.client.PagoClient;
import com.example.msreportes.client.ReservaClient;
import com.example.msreportes.client.dto.PagoResumenDTO;
import com.example.msreportes.client.dto.ReservaResumenDTO;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.entity.Reporte;
import com.example.msreportes.exception.ResourceNotFoundException;
import com.example.msreportes.repository.ReporteRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {
    @Mock
    private ReporteRepository reporteRepository;

    @Mock
    private ReservaClient reservaClient;

    @Mock
    private PagoClient pagoClient;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    void generarConsolidadoSumaPagosConfirmados() {
        when(reservaClient.listarReservas()).thenReturn(CollectionModel.of(List.of(EntityModel.of(new ReservaResumenDTO()), EntityModel.of(new ReservaResumenDTO()))));
        when(pagoClient.listarPagos()).thenReturn(List.of(pago(true, "1000.00"), pago(false, "500.00"), pago(true, "2500.00")));
        when(reporteRepository.save(any(Reporte.class))).thenAnswer(invocation -> {
            Reporte reporte = invocation.getArgument(0);
            reporte.setId(3);
            return reporte;
        });

        ReporteDTO resultado = reporteService.generarConsolidado();

        assertEquals(2, resultado.getTotalReservas());
        assertEquals(new BigDecimal("3500.00"), resultado.getTotalIngresos());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        when(reporteRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reporteService.findById(99));
    }

    private PagoResumenDTO pago(boolean pagado, String monto) {
        PagoResumenDTO pago = new PagoResumenDTO();
        pago.setPagado(pagado);
        pago.setMonto(new BigDecimal(monto));
        return pago;
    }
}
