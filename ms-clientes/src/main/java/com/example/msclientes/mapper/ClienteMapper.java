package com.example.msclientes.mapper;

import com.example.msclientes.dto.request.ClienteRequestDTO;
import com.example.msclientes.dto.response.ClienteDTO;
import com.example.msclientes.entity.Cliente;

public class ClienteMapper {
    private ClienteMapper() {}

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setNacionalidad(cliente.getNacionalidad());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setPuntosFidelidad(cliente.getPuntosFidelidad());
        dto.setActivo(cliente.isActivo());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        return dto;
    }

    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setNacionalidad(dto.getNacionalidad());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setPuntosFidelidad(dto.getPuntosFidelidad());
        cliente.setActivo(dto.isActivo());
        cliente.setFechaRegistro(dto.getFechaRegistro());
        return cliente;
    }
}
