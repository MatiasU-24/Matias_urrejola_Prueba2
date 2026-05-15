package com.example.msreservas.controller;

import com.example.msreservas.dto.request.EstadoReservaRequestDTO;
import com.example.msreservas.dto.response.EstadoReservaDTO;
import com.example.msreservas.service.EstadoReservaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estados-reserva")
public class EstadoReservaController {
    private final EstadoReservaService estadoReservaService;

    public EstadoReservaController(EstadoReservaService estadoReservaService) {
        this.estadoReservaService = estadoReservaService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoReservaDTO>> listar() {
        return ResponseEntity.ok(estadoReservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoReservaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoReservaDTO> crear(@Valid @RequestBody EstadoReservaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoReservaService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoReservaRequestDTO request) {
        return ResponseEntity.ok(estadoReservaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estadoReservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
