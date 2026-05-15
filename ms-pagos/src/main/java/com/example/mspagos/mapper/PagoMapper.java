package com.example.mspagos.mapper;

import com.example.mspagos.dto.request.PagoRequestDTO;
import com.example.mspagos.dto.response.PagoDTO;
import com.example.mspagos.entity.Pago;

public class PagoMapper {
    private PagoMapper() {}

    public static PagoDTO toDTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setCodigoTransaccion(pago.getCodigoTransaccion());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());
        dto.setNumeroCuotas(pago.getNumeroCuotas());
        dto.setPagado(pago.isPagado());
        dto.setFechaPago(pago.getFechaPago());
        dto.setReservaId(pago.getReservaId());
        return dto;
    }

    public static Pago toEntity(PagoRequestDTO dto) {
        Pago pago = new Pago();
        pago.setCodigoTransaccion(dto.getCodigoTransaccion());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMonto(dto.getMonto());
        pago.setNumeroCuotas(dto.getNumeroCuotas());
        pago.setPagado(dto.isPagado());
        pago.setFechaPago(dto.getFechaPago());
        pago.setReservaId(dto.getReservaId());
        return pago;
    }
}
