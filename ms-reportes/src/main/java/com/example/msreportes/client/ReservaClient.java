package com.example.msreportes.client;

import com.example.msreportes.client.dto.ReservaResumenDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-reservas")
public interface ReservaClient {
    @GetMapping("/api/v1/reservas")
    List<ReservaResumenDTO> listarReservas();
}
