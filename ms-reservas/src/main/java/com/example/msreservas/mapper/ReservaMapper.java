package com.example.msreservas.mapper;

import com.example.msreservas.dto.request.ReservaRequestDTO;
import com.example.msreservas.dto.response.ReservaDTO;
import com.example.msreservas.entity.Reserva;

public class ReservaMapper {
    private ReservaMapper() {}

    public static ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setCodigo(reserva.getCodigo());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setDiasArriendo(reserva.getDiasArriendo());
        dto.setMontoTotal(reserva.getMontoTotal());
        dto.setActiva(reserva.isActiva());
        dto.setFechaCreacion(reserva.getFechaCreacion());
        dto.setClienteId(reserva.getClienteId());
        dto.setVehiculoId(reserva.getVehiculoId());
        if (reserva.getEstadoReserva() != null) { dto.setEstadoReservaId(reserva.getEstadoReserva().getId()); }
        return dto;
    }

    public static Reserva toEntity(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setCodigo(dto.getCodigo());
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setDiasArriendo(dto.getDiasArriendo());
        reserva.setMontoTotal(dto.getMontoTotal());
        reserva.setActiva(dto.isActiva());
        reserva.setFechaCreacion(dto.getFechaCreacion());
        reserva.setClienteId(dto.getClienteId());
        reserva.setVehiculoId(dto.getVehiculoId());
        return reserva;
    }
}
