package com.example.mspagos.controller;

import com.example.mspagos.dto.request.PagoRequestDTO;
import com.example.mspagos.dto.response.PagoDTO;
import com.example.mspagos.service.PagoService;
import com.example.mspagos.mapper.PagoMapper;
import com.example.mspagos.repository.PagoRepository;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    private final PagoService pagoService;
    private final PagoRepository pagoRepository;

    public PagoController(PagoService pagoService, PagoRepository pagoRepository) {
        this.pagoService = pagoService;
        this.pagoRepository = pagoRepository;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagoDTO> crear(@Valid @RequestBody PagoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO request) {
        return ResponseEntity.ok(pagoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rango-monto")
    public ResponseEntity<List<PagoDTO>> pagosPorRango(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return ResponseEntity.ok(pagoRepository.buscarPagosPorRangoMonto(min, max).stream().map(PagoMapper::toDTO).toList());
    }

}
