package com.example.msvehiculos.mapper;

import com.example.msvehiculos.dto.request.VehiculoRequestDTO;
import com.example.msvehiculos.dto.response.VehiculoDTO;
import com.example.msvehiculos.entity.Vehiculo;

public class VehiculoMapper {
    private VehiculoMapper() {}

    public static VehiculoDTO toDTO(Vehiculo vehiculo) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(vehiculo.getId());
        dto.setPatente(vehiculo.getPatente());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setAnio(vehiculo.getAnio());
        dto.setPrecioArriendoDiario(vehiculo.getPrecioArriendoDiario());
        dto.setDisponible(vehiculo.isDisponible());
        dto.setFechaIngreso(vehiculo.getFechaIngreso());
        if (vehiculo.getCategoria() != null) { dto.setCategoriaId(vehiculo.getCategoria().getId()); }
        return dto;
    }

    public static Vehiculo toEntity(VehiculoRequestDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPrecioArriendoDiario(dto.getPrecioArriendoDiario());
        vehiculo.setDisponible(dto.isDisponible());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());
        return vehiculo;
    }
}
