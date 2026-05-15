package com.example.msreservas.mapper;

import com.example.msreservas.dto.request.EstadoReservaRequestDTO;
import com.example.msreservas.dto.response.EstadoReservaDTO;
import com.example.msreservas.entity.EstadoReserva;

public class EstadoReservaMapper {
    private EstadoReservaMapper() {}

    public static EstadoReservaDTO toDTO(EstadoReserva estadoReserva) {
        EstadoReservaDTO dto = new EstadoReservaDTO();
        dto.setId(estadoReserva.getId());
        dto.setNombre(estadoReserva.getNombre());
        dto.setDescripcion(estadoReserva.getDescripcion());
        dto.setPrioridad(estadoReserva.getPrioridad());
        dto.setPermiteCancelacion(estadoReserva.isPermiteCancelacion());
        dto.setFechaCreacion(estadoReserva.getFechaCreacion());
        dto.setOrdenVisual(estadoReserva.getOrdenVisual());
        return dto;
    }

    public static EstadoReserva toEntity(EstadoReservaRequestDTO dto) {
        EstadoReserva estadoReserva = new EstadoReserva();
        estadoReserva.setNombre(dto.getNombre());
        estadoReserva.setDescripcion(dto.getDescripcion());
        estadoReserva.setPrioridad(dto.getPrioridad());
        estadoReserva.setPermiteCancelacion(dto.isPermiteCancelacion());
        estadoReserva.setFechaCreacion(dto.getFechaCreacion());
        estadoReserva.setOrdenVisual(dto.getOrdenVisual());
        return estadoReserva;
    }
}
