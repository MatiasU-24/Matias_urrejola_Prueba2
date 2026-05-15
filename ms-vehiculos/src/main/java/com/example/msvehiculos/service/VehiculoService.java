package com.example.msvehiculos.service;

import com.example.msvehiculos.dto.request.VehiculoRequestDTO;
import com.example.msvehiculos.dto.response.VehiculoDTO;
import com.example.msvehiculos.entity.Vehiculo;
import com.example.msvehiculos.exception.ResourceNotFoundException;
import com.example.msvehiculos.mapper.VehiculoMapper;
import com.example.msvehiculos.repository.VehiculoRepository;
import com.example.msvehiculos.entity.Categoria;
import com.example.msvehiculos.repository.CategoriaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    private static final Logger log = LoggerFactory.getLogger(VehiculoService.class);
    private final VehiculoRepository vehiculoRepository;
    private final CategoriaRepository categoriaRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, CategoriaRepository categoriaRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<VehiculoDTO> findAll() {
        log.info("Listando vehiculos");
        return vehiculoRepository.findAll().stream().map(VehiculoMapper::toDTO).toList();
    }

    public VehiculoDTO findById(Integer id) {
        log.info("Buscando Vehiculo con id {}", id);
        return VehiculoMapper.toDTO(buscarEntidad(id));
    }

    public VehiculoDTO save(VehiculoRequestDTO request) {
        try {
            log.info("Creando Vehiculo");
            Vehiculo vehiculo = VehiculoMapper.toEntity(request);
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado con id " + request.getCategoriaId()));
        vehiculo.setCategoria(categoria);
            return VehiculoMapper.toDTO(vehiculoRepository.save(vehiculo));
        } catch (RuntimeException ex) {
            log.error("Error al crear Vehiculo", ex);
            throw ex;
        }
    }

    public VehiculoDTO update(Integer id, VehiculoRequestDTO request) {
        try {
            log.info("Actualizando Vehiculo con id {}", id);
            Vehiculo vehiculo = buscarEntidad(id);
            vehiculo.setPatente(request.getPatente());
            vehiculo.setMarca(request.getMarca());
            vehiculo.setModelo(request.getModelo());
            vehiculo.setAnio(request.getAnio());
            vehiculo.setPrecioArriendoDiario(request.getPrecioArriendoDiario());
            vehiculo.setDisponible(request.isDisponible());
            vehiculo.setFechaIngreso(request.getFechaIngreso());
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado con id " + request.getCategoriaId()));
        vehiculo.setCategoria(categoria);
            return VehiculoMapper.toDTO(vehiculoRepository.save(vehiculo));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Vehiculo", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Vehiculo con id {}", id);
        vehiculoRepository.delete(buscarEntidad(id));
    }

    private Vehiculo buscarEntidad(Integer id) {
        return vehiculoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id " + id));
    }
}
