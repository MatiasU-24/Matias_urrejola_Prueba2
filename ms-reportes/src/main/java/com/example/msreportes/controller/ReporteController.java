package com.example.msreportes.controller;

import com.example.msreportes.dto.request.ReporteRequestDTO;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.service.ReporteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(reporteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> crear(@Valid @RequestBody ReporteRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ReporteRequestDTO request) {
        return ResponseEntity.ok(reporteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reporteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/consolidado")
    public ResponseEntity<ReporteDTO> generarConsolidado() {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.generarConsolidado());
    }
}
