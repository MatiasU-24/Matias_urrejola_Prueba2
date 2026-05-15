package com.example.msclientes.controller;

import com.example.msclientes.dto.request.DireccionRequestDTO;
import com.example.msclientes.dto.response.DireccionDTO;
import com.example.msclientes.service.DireccionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController {
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> listar() {
        return ResponseEntity.ok(direccionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(direccionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> crear(@Valid @RequestBody DireccionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DireccionRequestDTO request) {
        return ResponseEntity.ok(direccionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
