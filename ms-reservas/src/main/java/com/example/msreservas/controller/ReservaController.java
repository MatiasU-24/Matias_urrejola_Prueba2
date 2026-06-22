package com.example.msreservas.controller;

import com.example.msreservas.dto.request.ReservaRequestDTO;
import com.example.msreservas.dto.response.ReservaDTO;
import com.example.msreservas.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
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
@RequestMapping("/api/v1/reservas")
@Tag(name = "Reservas", description = "CRUD, filtros y comunicacion Feign de reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Listar reservas", description = "Retorna reservas enriquecidas con datos del cliente.")
    public ResponseEntity<CollectionModel<EntityModel<ReservaDTO>>> listar() {
        List<EntityModel<ReservaDTO>> reservas = reservaService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(reservas, linkTo(methodOn(ReservaController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID")
    public ResponseEntity<EntityModel<ReservaDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(reservaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear reserva", description = "Valida cliente y vehiculo mediante Feign antes de guardar.")
    public ResponseEntity<EntityModel<ReservaDTO>> crear(@Valid @RequestBody ReservaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(reservaService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva")
    public ResponseEntity<EntityModel<ReservaDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ReservaRequestDTO request) {
        return ResponseEntity.ok(toModel(reservaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/desde")
    @Operation(summary = "Buscar reservas desde fecha")
    public ResponseEntity<CollectionModel<EntityModel<ReservaDTO>>> reservasDesde(@RequestParam LocalDate fecha) {
        List<EntityModel<ReservaDTO>> reservas = reservaService.findDesdeFecha(fecha).stream()
                .map(this::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(reservas, linkTo(methodOn(ReservaController.class).reservasDesde(fecha)).withSelfRel()));
    }

    private EntityModel<ReservaDTO> toModel(ReservaDTO reserva) {
        return EntityModel.of(reserva,
                linkTo(methodOn(ReservaController.class).obtenerPorId(reserva.getId())).withSelfRel(),
                linkTo(methodOn(ReservaController.class).listar()).withRel("reservas"),
                linkTo(methodOn(ReservaController.class).actualizar(reserva.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(ReservaController.class).eliminar(reserva.getId())).withRel("eliminar"));
    }
}
