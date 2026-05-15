package com.example.msreportes.mapper;

import com.example.msreportes.dto.request.ReporteRequestDTO;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.entity.Reporte;

public class ReporteMapper {
    private ReporteMapper() {}

    public static ReporteDTO toDTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId(reporte.getId());
        dto.setTitulo(reporte.getTitulo());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setTotalReservas(reporte.getTotalReservas());
        dto.setTotalIngresos(reporte.getTotalIngresos());
        dto.setPublicado(reporte.isPublicado());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setTipoReporte(reporte.getTipoReporte());
        return dto;
    }

    public static Reporte toEntity(ReporteRequestDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setTotalReservas(dto.getTotalReservas());
        reporte.setTotalIngresos(dto.getTotalIngresos());
        reporte.setPublicado(dto.isPublicado());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());
        reporte.setTipoReporte(dto.getTipoReporte());
        return reporte;
    }
}
