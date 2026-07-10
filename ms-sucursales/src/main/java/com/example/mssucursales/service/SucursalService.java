package com.example.mssucursales.service;

import com.example.mssucursales.dto.request.SucursalRequestDTO;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.entity.Sucursal;
import com.example.mssucursales.exception.ResourceNotFoundException;
import com.example.mssucursales.mapper.SucursalMapper;
import com.example.mssucursales.repository.SucursalRepository;
import com.example.mssucursales.entity.Region;
import com.example.mssucursales.repository.RegionRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {
    private static final Logger log = LoggerFactory.getLogger(SucursalService.class);
    private final SucursalRepository sucursalRepository;
    private final RegionRepository regionRepository;

    public SucursalService(SucursalRepository sucursalRepository, RegionRepository regionRepository) {
        this.sucursalRepository = sucursalRepository;
        this.regionRepository = regionRepository;
    }

    public List<SucursalDTO> findAll() {
        log.info("Listando sucursales");
        return sucursalRepository.findAll().stream().map(SucursalMapper::toDTO).toList();
    }

    public SucursalDTO findById(Integer id) {
        log.info("Buscando Sucursal con id {}", id);
        return SucursalMapper.toDTO(buscarEntidad(id));
    }

    public List<SucursalDTO> findOperativas() {
        log.info("Listando sucursales operativas");
        return sucursalRepository.listarSucursalesOperativas().stream().map(SucursalMapper::toDTO).toList();
    }

    public SucursalDTO save(SucursalRequestDTO request) {
        try {
            log.info("Creando Sucursal");
            Sucursal sucursal = SucursalMapper.toEntity(request);
            Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new ResourceNotFoundException("Region no encontrado con id " + request.getRegionId()));
            sucursal.setRegion(region);
            return SucursalMapper.toDTO(sucursalRepository.save(sucursal));
        } catch (RuntimeException ex) {
            log.error("Error al crear Sucursal", ex);
            throw ex;
        }
    }

    public SucursalDTO update(Integer id, SucursalRequestDTO request) {
        try {
            log.info("Actualizando Sucursal con id {}", id);
            Sucursal sucursal = buscarEntidad(id);
            sucursal.setNombre(request.getNombre());
            sucursal.setDireccion(request.getDireccion());
            sucursal.setTelefono(request.getTelefono());
            sucursal.setCapacidadVehiculos(request.getCapacidadVehiculos());
            sucursal.setOperativa(request.isOperativa());
            sucursal.setFechaApertura(request.getFechaApertura());
            Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new ResourceNotFoundException("Region no encontrado con id " + request.getRegionId()));
            sucursal.setRegion(region);
            return SucursalMapper.toDTO(sucursalRepository.save(sucursal));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Sucursal", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Sucursal con id {}", id);
        sucursalRepository.delete(buscarEntidad(id));
    }

    private Sucursal buscarEntidad(Integer id) {
        return sucursalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrado con id " + id));
    }
}
