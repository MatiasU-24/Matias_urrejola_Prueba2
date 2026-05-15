package com.example.msvehiculos.mapper;

import com.example.msvehiculos.dto.request.CategoriaRequestDTO;
import com.example.msvehiculos.dto.response.CategoriaDTO;
import com.example.msvehiculos.entity.Categoria;

public class CategoriaMapper {
    private CategoriaMapper() {}

    public static CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setTarifaBase(categoria.getTarifaBase());
        dto.setCapacidadPasajeros(categoria.getCapacidadPasajeros());
        dto.setActiva(categoria.isActiva());
        dto.setFechaCreacion(categoria.getFechaCreacion());
        return dto;
    }

    public static Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setTarifaBase(dto.getTarifaBase());
        categoria.setCapacidadPasajeros(dto.getCapacidadPasajeros());
        categoria.setActiva(dto.isActiva());
        categoria.setFechaCreacion(dto.getFechaCreacion());
        return categoria;
    }
}
