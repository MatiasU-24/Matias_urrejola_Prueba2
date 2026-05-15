package com.example.msreservas.service;

import com.example.msreservas.dto.request.EstadoReservaRequestDTO;
import com.example.msreservas.dto.response.EstadoReservaDTO;
import com.example.msreservas.entity.EstadoReserva;
import com.example.msreservas.exception.ResourceNotFoundException;
import com.example.msreservas.mapper.EstadoReservaMapper;
import com.example.msreservas.repository.EstadoReservaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EstadoReservaService {
    private static final Logger log = LoggerFactory.getLogger(EstadoReservaService.class);
    private final EstadoReservaRepository estadoReservaRepository;

    public EstadoReservaService(EstadoReservaRepository estadoReservaRepository) {
        this.estadoReservaRepository = estadoReservaRepository;
    }

    public List<EstadoReservaDTO> findAll() {
        log.info("Listando estados_reserva");
        return estadoReservaRepository.findAll().stream().map(EstadoReservaMapper::toDTO).toList();
    }

    public EstadoReservaDTO findById(Integer id) {
        log.info("Buscando EstadoReserva con id {}", id);
        return EstadoReservaMapper.toDTO(buscarEntidad(id));
    }

    public EstadoReservaDTO save(EstadoReservaRequestDTO request) {
        try {
            log.info("Creando EstadoReserva");
            EstadoReserva estadoReserva = EstadoReservaMapper.toEntity(request);
            return EstadoReservaMapper.toDTO(estadoReservaRepository.save(estadoReserva));
        } catch (RuntimeException ex) {
            log.error("Error al crear EstadoReserva", ex);
            throw ex;
        }
    }

    public EstadoReservaDTO update(Integer id, EstadoReservaRequestDTO request) {
        try {
            log.info("Actualizando EstadoReserva con id {}", id);
            EstadoReserva estadoReserva = buscarEntidad(id);
            estadoReserva.setNombre(request.getNombre());
            estadoReserva.setDescripcion(request.getDescripcion());
            estadoReserva.setPrioridad(request.getPrioridad());
            estadoReserva.setPermiteCancelacion(request.isPermiteCancelacion());
            estadoReserva.setFechaCreacion(request.getFechaCreacion());
            estadoReserva.setOrdenVisual(request.getOrdenVisual());
            return EstadoReservaMapper.toDTO(estadoReservaRepository.save(estadoReserva));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar EstadoReserva", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando EstadoReserva con id {}", id);
        estadoReservaRepository.delete(buscarEntidad(id));
    }

    private EstadoReserva buscarEntidad(Integer id) {
        return estadoReservaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EstadoReserva no encontrado con id " + id));
    }
}
