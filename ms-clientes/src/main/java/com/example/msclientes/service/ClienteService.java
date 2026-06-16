package com.example.msclientes.service;

import com.example.msclientes.dto.request.ClienteRequestDTO;
import com.example.msclientes.dto.response.ClienteDTO;
import com.example.msclientes.entity.Cliente;
import com.example.msclientes.exception.ResourceNotFoundException;
import com.example.msclientes.mapper.ClienteMapper;
import com.example.msclientes.repository.ClienteRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> findAll() {
        log.info("Listando clientes");
        return clienteRepository.findAll().stream().map(ClienteMapper::toDTO).toList();
    }

    public ClienteDTO findById(Integer id) {
        log.info("Buscando Cliente con id {}", id);
        return ClienteMapper.toDTO(buscarEntidad(id));
    }

    public ClienteDTO save(ClienteRequestDTO request) {
        try {
            log.info("Creando Cliente");
            Cliente cliente = ClienteMapper.toEntity(request);
            return ClienteMapper.toDTO(clienteRepository.save(cliente));
        } catch (RuntimeException ex) {
            log.error("Error al crear Cliente", ex);
            throw ex;
        }
    }

    public ClienteDTO update(Integer id, ClienteRequestDTO request) {
        try {
            log.info("Actualizando Cliente con id {}", id);
            Cliente cliente = buscarEntidad(id);
            cliente.setNombre(request.getNombre());
            cliente.setApellido(request.getApellido());
            cliente.setNacionalidad(request.getNacionalidad());
            cliente.setEmail(request.getEmail());
            cliente.setTelefono(request.getTelefono());
            cliente.setPuntosFidelidad(request.getPuntosFidelidad());
            cliente.setActivo(request.isActivo());
            cliente.setFechaRegistro(request.getFechaRegistro());
            return ClienteMapper.toDTO(clienteRepository.save(cliente));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Cliente", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Cliente con id {}", id);
        clienteRepository.delete(buscarEntidad(id));
    }

    private Cliente buscarEntidad(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
    }
}
