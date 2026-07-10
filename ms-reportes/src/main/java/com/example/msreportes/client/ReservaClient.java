package com.example.msreportes.client;

import com.example.msreportes.client.dto.ReservaResumenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-reservas")
public interface ReservaClient {
    @GetMapping("/api/v1/reservas")
    CollectionModel<EntityModel<ReservaResumenDTO>> listarReservas();
}
