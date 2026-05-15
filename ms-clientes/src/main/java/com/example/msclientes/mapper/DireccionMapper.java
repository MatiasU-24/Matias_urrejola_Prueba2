package com.example.msclientes.mapper;

import com.example.msclientes.dto.request.DireccionRequestDTO;
import com.example.msclientes.dto.response.DireccionDTO;
import com.example.msclientes.entity.Direccion;

public class DireccionMapper {
    private DireccionMapper() {}

    public static DireccionDTO toDTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setId(direccion.getId());
        dto.setCalle(direccion.getCalle());
        dto.setNumero(direccion.getNumero());
        dto.setComuna(direccion.getComuna());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setPrincipal(direccion.isPrincipal());
        dto.setFechaCreacion(direccion.getFechaCreacion());
        if (direccion.getCliente() != null) { dto.setClienteId(direccion.getCliente().getId()); }
        return dto;
    }

    public static Direccion toEntity(DireccionRequestDTO dto) {
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setComuna(dto.getComuna());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPrincipal(dto.isPrincipal());
        direccion.setFechaCreacion(dto.getFechaCreacion());
        return direccion;
    }
}
