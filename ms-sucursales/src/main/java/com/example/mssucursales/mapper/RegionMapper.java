package com.example.mssucursales.mapper;

import com.example.mssucursales.dto.request.RegionRequestDTO;
import com.example.mssucursales.dto.response.RegionDTO;
import com.example.mssucursales.entity.Region;

public class RegionMapper {
    private RegionMapper() {}

    public static RegionDTO toDTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombre(region.getNombre());
        dto.setCodigo(region.getCodigo());
        dto.setNumeroRegion(region.getNumeroRegion());
        dto.setActiva(region.isActiva());
        dto.setFechaCreacion(region.getFechaCreacion());
        dto.setCantidadComunas(region.getCantidadComunas());
        return dto;
    }

    public static Region toEntity(RegionRequestDTO dto) {
        Region region = new Region();
        region.setNombre(dto.getNombre());
        region.setCodigo(dto.getCodigo());
        region.setNumeroRegion(dto.getNumeroRegion());
        region.setActiva(dto.isActiva());
        region.setFechaCreacion(dto.getFechaCreacion());
        region.setCantidadComunas(dto.getCantidadComunas());
        return region;
    }
}
