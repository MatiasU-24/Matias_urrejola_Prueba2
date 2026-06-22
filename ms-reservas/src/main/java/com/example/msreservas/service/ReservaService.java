package com.example.msreservas.service;

import com.example.msreservas.dto.request.ReservaRequestDTO;
import com.example.msreservas.dto.response.ReservaDTO;
import com.example.msreservas.entity.Reserva;
import com.example.msreservas.exception.ResourceNotFoundException;
import com.example.msreservas.mapper.ReservaMapper;
import com.example.msreservas.repository.ReservaRepository;
import com.example.msreservas.entity.EstadoReserva;
import com.example.msreservas.repository.EstadoReservaRepository;
import com.example.msreservas.client.ClienteClient;
import com.example.msreservas.client.VehiculoClient;
import com.example.msreservas.client.dto.ClienteDTO;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);
    private final ReservaRepository reservaRepository;
    private final EstadoReservaRepository estadoReservaRepository;
    private final ClienteClient clienteClient;
    private final VehiculoClient vehiculoClient;

    public ReservaService(ReservaRepository reservaRepository, EstadoReservaRepository estadoReservaRepository, ClienteClient clienteClient, VehiculoClient vehiculoClient) {
        this.reservaRepository = reservaRepository;
        this.estadoReservaRepository = estadoReservaRepository;
        this.clienteClient = clienteClient;
        this.vehiculoClient = vehiculoClient;
    }

    public List<ReservaDTO> findAll() {
        log.info("Listando reservas");
        return reservaRepository.findAll().stream().map(this::toDTOConCliente).toList();
    }

    public ReservaDTO findById(Integer id) {
        log.info("Buscando Reserva con id {}", id);
        return toDTOConCliente(buscarEntidad(id));
    }

    public List<ReservaDTO> findDesdeFecha(LocalDate fecha) {
        log.info("Buscando reservas desde fecha {}", fecha);
        return reservaRepository.buscarReservasDesdeFecha(fecha).stream()
                .map(this::toDTOConCliente)
                .toList();
    }

    public ReservaDTO save(ReservaRequestDTO request) {
        try {
            log.info("Creando Reserva");
            verificarClienteYVehiculo(request);
            Reserva reserva = ReservaMapper.toEntity(request);
            EstadoReserva estadoReserva = estadoReservaRepository.findById(request.getEstadoReservaId()).orElseThrow(() -> new ResourceNotFoundException("EstadoReserva no encontrado con id " + request.getEstadoReservaId()));
            reserva.setEstadoReserva(estadoReserva);
            return toDTOConCliente(reservaRepository.save(reserva));
        } catch (RuntimeException ex) {
            log.error("Error al crear Reserva", ex);
            throw ex;
        }
    }

    public ReservaDTO update(Integer id, ReservaRequestDTO request) {
        try {
            log.info("Actualizando Reserva con id {}", id);
            verificarClienteYVehiculo(request);
            Reserva reserva = buscarEntidad(id);
            reserva.setCodigo(request.getCodigo());
            reserva.setFechaInicio(request.getFechaInicio());
            reserva.setFechaFin(request.getFechaFin());
            reserva.setDiasArriendo(request.getDiasArriendo());
            reserva.setMontoTotal(request.getMontoTotal());
            reserva.setActiva(request.isActiva());
            reserva.setFechaCreacion(request.getFechaCreacion());
            reserva.setClienteId(request.getClienteId());
            reserva.setVehiculoId(request.getVehiculoId());
            EstadoReserva estadoReserva = estadoReservaRepository.findById(request.getEstadoReservaId()).orElseThrow(() -> new ResourceNotFoundException("EstadoReserva no encontrado con id " + request.getEstadoReservaId()));
            reserva.setEstadoReserva(estadoReserva);
            return toDTOConCliente(reservaRepository.save(reserva));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Reserva", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Reserva con id {}", id);
        reservaRepository.delete(buscarEntidad(id));
    }

    private Reserva buscarEntidad(Integer id) {
        return reservaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrado con id " + id));
    }

    public ReservaDTO toDTOConCliente(Reserva reserva) {
        ReservaDTO dto = ReservaMapper.toDTO(reserva);
        ClienteDTO cliente = clienteClient.obtenerClientePorId(reserva.getClienteId());
        dto.setCliente(cliente);
        return dto;
    }

    private void verificarClienteYVehiculo(ReservaRequestDTO request) {
        clienteClient.obtenerClientePorId(request.getClienteId());
        var vehiculo = vehiculoClient.obtenerVehiculoPorId(request.getVehiculoId());
        if (!vehiculo.isDisponible()) {
            throw new IllegalStateException("Vehiculo no disponible para reserva");
        }
    }
}
