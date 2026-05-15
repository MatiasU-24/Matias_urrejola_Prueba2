package com.example.msempleados.service;

import com.example.msempleados.dto.request.EmpleadoRequestDTO;
import com.example.msempleados.dto.response.EmpleadoDTO;
import com.example.msempleados.entity.Empleado;
import com.example.msempleados.exception.ResourceNotFoundException;
import com.example.msempleados.mapper.EmpleadoMapper;
import com.example.msempleados.repository.EmpleadoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    private static final Logger log = LoggerFactory.getLogger(EmpleadoService.class);
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<EmpleadoDTO> findAll() {
        log.info("Listando empleados");
        return empleadoRepository.findAll().stream().map(EmpleadoMapper::toDTO).toList();
    }

    public EmpleadoDTO findById(Integer id) {
        log.info("Buscando Empleado con id {}", id);
        return EmpleadoMapper.toDTO(buscarEntidad(id));
    }

    public EmpleadoDTO save(EmpleadoRequestDTO request) {
        try {
            log.info("Creando Empleado");
            Empleado empleado = EmpleadoMapper.toEntity(request);
            return EmpleadoMapper.toDTO(empleadoRepository.save(empleado));
        } catch (RuntimeException ex) {
            log.error("Error al crear Empleado", ex);
            throw ex;
        }
    }

    public EmpleadoDTO update(Integer id, EmpleadoRequestDTO request) {
        try {
            log.info("Actualizando Empleado con id {}", id);
            Empleado empleado = buscarEntidad(id);
            empleado.setRut(request.getRut());
            empleado.setNombre(request.getNombre());
            empleado.setEmail(request.getEmail());
            empleado.setSueldo(request.getSueldo());
            empleado.setHorasSemanales(request.getHorasSemanales());
            empleado.setActivo(request.isActivo());
            empleado.setFechaContratacion(request.getFechaContratacion());
            empleado.setCargo(request.getCargo());
            return EmpleadoMapper.toDTO(empleadoRepository.save(empleado));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Empleado", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Empleado con id {}", id);
        empleadoRepository.delete(buscarEntidad(id));
    }

    private Empleado buscarEntidad(Integer id) {
        return empleadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id " + id));
    }
}
