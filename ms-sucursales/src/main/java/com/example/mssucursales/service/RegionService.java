package com.example.mssucursales.service;

import com.example.mssucursales.dto.request.RegionRequestDTO;
import com.example.mssucursales.dto.response.RegionDTO;
import com.example.mssucursales.entity.Region;
import com.example.mssucursales.exception.ResourceNotFoundException;
import com.example.mssucursales.mapper.RegionMapper;
import com.example.mssucursales.repository.RegionRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    private static final Logger log = LoggerFactory.getLogger(RegionService.class);
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionDTO> findAll() {
        log.info("Listando regiones");
        return regionRepository.findAll().stream().map(RegionMapper::toDTO).toList();
    }

    public RegionDTO findById(Integer id) {
        log.info("Buscando Region con id {}", id);
        return RegionMapper.toDTO(buscarEntidad(id));
    }

    public RegionDTO save(RegionRequestDTO request) {
        try {
            log.info("Creando Region");
            Region region = RegionMapper.toEntity(request);
            return RegionMapper.toDTO(regionRepository.save(region));
        } catch (RuntimeException ex) {
            log.error("Error al crear Region", ex);
            throw ex;
        }
    }

    public RegionDTO update(Integer id, RegionRequestDTO request) {
        try {
            log.info("Actualizando Region con id {}", id);
            Region region = buscarEntidad(id);
            region.setNombre(request.getNombre());
            region.setCodigo(request.getCodigo());
            region.setNumeroRegion(request.getNumeroRegion());
            region.setActiva(request.isActiva());
            region.setFechaCreacion(request.getFechaCreacion());
            region.setCantidadComunas(request.getCantidadComunas());
            return RegionMapper.toDTO(regionRepository.save(region));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Region", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Region con id {}", id);
        regionRepository.delete(buscarEntidad(id));
    }

    private Region buscarEntidad(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region no encontrado con id " + id));
    }
}
