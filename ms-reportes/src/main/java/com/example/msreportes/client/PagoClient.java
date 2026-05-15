package com.example.msreportes.client;

import com.example.msreportes.client.dto.PagoResumenDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-pagos", url = "http://localhost:8084")
public interface PagoClient {
    @GetMapping("/api/v1/pagos")
    List<PagoResumenDTO> listarPagos();
}
