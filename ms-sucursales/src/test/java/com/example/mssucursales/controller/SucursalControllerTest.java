package com.example.mssucursales.controller;

import com.example.mssucursales.assembler.SucursalModelAssembler;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.service.SucursalService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SucursalControllerTest {
    @Test
    void listarRetornaSucursalesConLinks() throws Exception {
        SucursalService service = new StubSucursalService();
        SucursalController controller = new SucursalController(service, new SucursalModelAssembler());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/sucursales"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sucursal Santiago Centro")));
    }

    private SucursalDTO sucursal() {
        SucursalDTO dto = new SucursalDTO();
        dto.setId(1);
        dto.setNombre("Sucursal Santiago Centro");
        dto.setDireccion("Alameda 1500");
        dto.setTelefono("225551111");
        dto.setCapacidadVehiculos(80);
        dto.setOperativa(true);
        dto.setFechaApertura(LocalDate.now().minusYears(4));
        dto.setRegionId(1);
        return dto;
    }

    private class StubSucursalService extends SucursalService {
        StubSucursalService() {
            super(null, null);
        }

        @Override
        public List<SucursalDTO> findAll() {
            return List.of(sucursal());
        }
    }
}
