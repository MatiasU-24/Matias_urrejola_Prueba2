package com.example.msvehiculos.controller;

import com.example.msvehiculos.dto.request.VehiculoRequestDTO;
import com.example.msvehiculos.dto.response.VehiculoDTO;
import com.example.msvehiculos.service.VehiculoService;
import com.example.msvehiculos.mapper.VehiculoMapper;
import com.example.msvehiculos.repository.VehiculoRepository;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    private final VehiculoRepository vehiculoRepository;

    public VehiculoController(VehiculoService vehiculoService, VehiculoRepository vehiculoRepository) {
        this.vehiculoService = vehiculoService;
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listar() {
        return ResponseEntity.ok(vehiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.ok(vehiculoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles/precio")
    public ResponseEntity<List<VehiculoDTO>> disponiblesPorPrecio(@RequestParam BigDecimal precio) {
        return ResponseEntity.ok(vehiculoRepository.findByDisponibleTrueAndPrecioArriendoDiarioLessThan(precio).stream().map(VehiculoMapper::toDTO).toList());
    }

}
