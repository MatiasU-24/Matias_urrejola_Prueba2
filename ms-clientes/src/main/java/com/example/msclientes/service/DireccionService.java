package com.example.msclientes.service;

import com.example.msclientes.dto.request.DireccionRequestDTO;
import com.example.msclientes.dto.response.DireccionDTO;
import com.example.msclientes.entity.Direccion;
import com.example.msclientes.exception.ResourceNotFoundException;
import com.example.msclientes.mapper.DireccionMapper;
import com.example.msclientes.repository.DireccionRepository;
import com.example.msclientes.entity.Cliente;
import com.example.msclientes.repository.ClienteRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    private static final Logger log = LoggerFactory.getLogger(DireccionService.class);
    private final DireccionRepository direccionRepository;
    private final ClienteRepository clienteRepository;

    public DireccionService(DireccionRepository direccionRepository, ClienteRepository clienteRepository) {
        this.direccionRepository = direccionRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<DireccionDTO> findAll() {
        log.info("Listando direcciones");
        return direccionRepository.findAll().stream().map(DireccionMapper::toDTO).toList();
    }

    public DireccionDTO findById(Integer id) {
        log.info("Buscando Direccion con id {}", id);
        return DireccionMapper.toDTO(buscarEntidad(id));
    }

    public DireccionDTO save(DireccionRequestDTO request) {
        try {
            log.info("Creando Direccion");
            Direccion direccion = DireccionMapper.toEntity(request);
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + request.getClienteId()));
        direccion.setCliente(cliente);
            return DireccionMapper.toDTO(direccionRepository.save(direccion));
        } catch (RuntimeException ex) {
            log.error("Error al crear Direccion", ex);
            throw ex;
        }
    }

    public DireccionDTO update(Integer id, DireccionRequestDTO request) {
        try {
            log.info("Actualizando Direccion con id {}", id);
            Direccion direccion = buscarEntidad(id);
            direccion.setCalle(request.getCalle());
            direccion.setNumero(request.getNumero());
            direccion.setComuna(request.getComuna());
            direccion.setCodigoPostal(request.getCodigoPostal());
            direccion.setPrincipal(request.isPrincipal());
            direccion.setFechaCreacion(request.getFechaCreacion());
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + request.getClienteId()));
        direccion.setCliente(cliente);
            return DireccionMapper.toDTO(direccionRepository.save(direccion));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Direccion", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Direccion con id {}", id);
        direccionRepository.delete(buscarEntidad(id));
    }

    private Direccion buscarEntidad(Integer id) {
        return direccionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Direccion no encontrado con id " + id));
    }
}
