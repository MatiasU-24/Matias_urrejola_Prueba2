package com.example.mssucursales.mapper;

import com.example.mssucursales.dto.request.SucursalRequestDTO;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.entity.Sucursal;

public class SucursalMapper {
    private SucursalMapper() {}

    public static SucursalDTO toDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setTelefono(sucursal.getTelefono());
        dto.setCapacidadVehiculos(sucursal.getCapacidadVehiculos());
        dto.setOperativa(sucursal.isOperativa());
        dto.setFechaApertura(sucursal.getFechaApertura());
        if (sucursal.getRegion() != null) { dto.setRegionId(sucursal.getRegion().getId()); }
        return dto;
    }

    public static Sucursal toEntity(SucursalRequestDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setCapacidadVehiculos(dto.getCapacidadVehiculos());
        sucursal.setOperativa(dto.isOperativa());
        sucursal.setFechaApertura(dto.getFechaApertura());
        return sucursal;
    }
}
