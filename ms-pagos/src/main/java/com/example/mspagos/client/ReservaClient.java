package com.example.mspagos.client;

import com.example.mspagos.client.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-reservas", url = "http://localhost:8083")
public interface ReservaClient {
    @GetMapping("/api/v1/reservas/{id}")
    ReservaDTO obtenerReservaPorId(@PathVariable("id") Integer id);
}
