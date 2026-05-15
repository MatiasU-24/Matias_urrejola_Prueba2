package com.example.msvehiculos.service;

import com.example.msvehiculos.dto.request.CategoriaRequestDTO;
import com.example.msvehiculos.dto.response.CategoriaDTO;
import com.example.msvehiculos.entity.Categoria;
import com.example.msvehiculos.exception.ResourceNotFoundException;
import com.example.msvehiculos.mapper.CategoriaMapper;
import com.example.msvehiculos.repository.CategoriaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> findAll() {
        log.info("Listando categorias");
        return categoriaRepository.findAll().stream().map(CategoriaMapper::toDTO).toList();
    }

    public CategoriaDTO findById(Integer id) {
        log.info("Buscando Categoria con id {}", id);
        return CategoriaMapper.toDTO(buscarEntidad(id));
    }

    public CategoriaDTO save(CategoriaRequestDTO request) {
        try {
            log.info("Creando Categoria");
            Categoria categoria = CategoriaMapper.toEntity(request);
            return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
        } catch (RuntimeException ex) {
            log.error("Error al crear Categoria", ex);
            throw ex;
        }
    }

    public CategoriaDTO update(Integer id, CategoriaRequestDTO request) {
        try {
            log.info("Actualizando Categoria con id {}", id);
            Categoria categoria = buscarEntidad(id);
            categoria.setNombre(request.getNombre());
            categoria.setDescripcion(request.getDescripcion());
            categoria.setTarifaBase(request.getTarifaBase());
            categoria.setCapacidadPasajeros(request.getCapacidadPasajeros());
            categoria.setActiva(request.isActiva());
            categoria.setFechaCreacion(request.getFechaCreacion());
            return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Categoria", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Categoria con id {}", id);
        categoriaRepository.delete(buscarEntidad(id));
    }

    private Categoria buscarEntidad(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado con id " + id));
    }
}
