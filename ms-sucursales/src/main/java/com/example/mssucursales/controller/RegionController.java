package com.example.mssucursales.controller;

import com.example.mssucursales.dto.request.RegionRequestDTO;
import com.example.mssucursales.dto.response.RegionDTO;
import com.example.mssucursales.service.RegionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
        return ResponseEntity.ok(regionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RegionDTO> crear(@Valid @RequestBody RegionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody RegionRequestDTO request) {
        return ResponseEntity.ok(regionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
