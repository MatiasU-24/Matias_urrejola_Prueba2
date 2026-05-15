package com.example.msreservas.controller;

import com.example.msreservas.dto.request.ReservaRequestDTO;
import com.example.msreservas.dto.response.ReservaDTO;
import com.example.msreservas.service.ReservaService;
import com.example.msreservas.mapper.ReservaMapper;
import com.example.msreservas.repository.ReservaRepository;
import java.time.LocalDate;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {
    private final ReservaService reservaService;
    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaService reservaService, ReservaRepository reservaRepository) {
        this.reservaService = reservaService;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> crear(@Valid @RequestBody ReservaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ReservaRequestDTO request) {
        return ResponseEntity.ok(reservaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/desde")
    public ResponseEntity<List<ReservaDTO>> reservasDesde(@RequestParam LocalDate fecha) {
        return ResponseEntity.ok(reservaRepository.buscarReservasDesdeFecha(fecha).stream().map(ReservaMapper::toDTO).toList());
    }

}
