package com.example.msreservas.controller;

import com.example.msreservas.dto.request.EstadoReservaRequestDTO;
import com.example.msreservas.dto.response.EstadoReservaDTO;
import com.example.msreservas.service.EstadoReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/estados-reserva")
@Tag(name = "Estados de reserva", description = "CRUD de estados de reserva")
public class EstadoReservaController {
    private final EstadoReservaService estadoReservaService;

    public EstadoReservaController(EstadoReservaService estadoReservaService) {
        this.estadoReservaService = estadoReservaService;
    }

    @GetMapping
    @Operation(summary = "Listar estados de reserva")
    public ResponseEntity<CollectionModel<EntityModel<EstadoReservaDTO>>> listar() {
        List<EntityModel<EstadoReservaDTO>> estados = estadoReservaService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(estados, linkTo(methodOn(EstadoReservaController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estado de reserva por ID")
    public ResponseEntity<EntityModel<EstadoReservaDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(estadoReservaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear estado de reserva")
    public ResponseEntity<EntityModel<EstadoReservaDTO>> crear(@Valid @RequestBody EstadoReservaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(estadoReservaService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado de reserva")
    public ResponseEntity<EntityModel<EstadoReservaDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoReservaRequestDTO request) {
        return ResponseEntity.ok(toModel(estadoReservaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estado de reserva")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estadoReservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<EstadoReservaDTO> toModel(EstadoReservaDTO estado) {
        return EntityModel.of(estado,
                linkTo(methodOn(EstadoReservaController.class).obtenerPorId(estado.getId())).withSelfRel(),
                linkTo(methodOn(EstadoReservaController.class).listar()).withRel("estados-reserva"),
                linkTo(methodOn(EstadoReservaController.class).actualizar(estado.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(EstadoReservaController.class).eliminar(estado.getId())).withRel("eliminar"));
    }
}
