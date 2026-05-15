package com.example.msempleados.mapper;

import com.example.msempleados.dto.request.EmpleadoRequestDTO;
import com.example.msempleados.dto.response.EmpleadoDTO;
import com.example.msempleados.entity.Empleado;

public class EmpleadoMapper {
    private EmpleadoMapper() {}

    public static EmpleadoDTO toDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setRut(empleado.getRut());
        dto.setNombre(empleado.getNombre());
        dto.setEmail(empleado.getEmail());
        dto.setSueldo(empleado.getSueldo());
        dto.setHorasSemanales(empleado.getHorasSemanales());
        dto.setActivo(empleado.isActivo());
        dto.setFechaContratacion(empleado.getFechaContratacion());
        dto.setCargo(empleado.getCargo());
        return dto;
    }

    public static Empleado toEntity(EmpleadoRequestDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setRut(dto.getRut());
        empleado.setNombre(dto.getNombre());
        empleado.setEmail(dto.getEmail());
        empleado.setSueldo(dto.getSueldo());
        empleado.setHorasSemanales(dto.getHorasSemanales());
        empleado.setActivo(dto.isActivo());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        empleado.setCargo(dto.getCargo());
        return empleado;
    }
}
