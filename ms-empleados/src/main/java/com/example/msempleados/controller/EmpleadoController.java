package com.example.msempleados.controller;

import com.example.msempleados.dto.request.EmpleadoRequestDTO;
import com.example.msempleados.dto.response.EmpleadoDTO;
import com.example.msempleados.service.EmpleadoService;
import com.example.msempleados.mapper.EmpleadoMapper;
import com.example.msempleados.repository.EmpleadoRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoService empleadoService, EmpleadoRepository empleadoRepository) {
        this.empleadoService = empleadoService;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(empleadoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@Valid @RequestBody EmpleadoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO request) {
        return ResponseEntity.ok(empleadoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos/anio/{anio}")
    public ResponseEntity<List<EmpleadoDTO>> activosPorAnio(@PathVariable Integer anio) {
        return ResponseEntity.ok(empleadoRepository.listarEmpleadosActivosPorAnio(anio).stream().map(EmpleadoMapper::toDTO).toList());
    }

}
