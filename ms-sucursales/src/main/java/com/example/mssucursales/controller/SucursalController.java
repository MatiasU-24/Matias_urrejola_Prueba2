package com.example.mssucursales.controller;

import com.example.mssucursales.dto.request.SucursalRequestDTO;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.service.SucursalService;
import com.example.mssucursales.mapper.SucursalMapper;
import com.example.mssucursales.repository.SucursalRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {
    private final SucursalService sucursalService;
    private final SucursalRepository sucursalRepository;

    public SucursalController(SucursalService sucursalService, SucursalRepository sucursalRepository) {
        this.sucursalService = sucursalService;
        this.sucursalRepository = sucursalRepository;
    }

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(sucursalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@Valid @RequestBody SucursalRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody SucursalRequestDTO request) {
        return ResponseEntity.ok(sucursalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/operativas")
    public ResponseEntity<List<SucursalDTO>> operativas() {
        return ResponseEntity.ok(sucursalRepository.listarSucursalesOperativas().stream().map(SucursalMapper::toDTO).toList());
    }

}
