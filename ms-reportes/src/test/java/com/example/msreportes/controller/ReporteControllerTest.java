package com.example.msreportes.controller;

import com.example.msreportes.assembler.ReporteModelAssembler;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.service.ReporteService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReporteControllerTest {
    @Test
    void listarRetornaReportesConLinks() throws Exception {
        ReporteService service = new StubReporteService();
        ReporteController controller = new ReporteController(service, new ReporteModelAssembler());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/reportes"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Reporte mensual enero")));
    }

    private ReporteDTO reporte() {
        ReporteDTO dto = new ReporteDTO();
        dto.setId(1);
        dto.setTitulo("Reporte mensual enero");
        dto.setDescripcion("Resumen operativo mensual de reservas y pagos");
        dto.setTotalReservas(12);
        dto.setTotalIngresos(new BigDecimal("1250000.00"));
        dto.setPublicado(true);
        dto.setFechaGeneracion(LocalDateTime.now());
        dto.setTipoReporte("Mensual");
        return dto;
    }

    private class StubReporteService extends ReporteService {
        StubReporteService() {
            super(null, null, null);
        }

        @Override
        public List<ReporteDTO> findAll() {
            return List.of(reporte());
        }
    }
}
