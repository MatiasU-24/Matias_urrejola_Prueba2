package com.example.msclientes.controller;

import com.example.msclientes.dto.request.DireccionRequestDTO;
import com.example.msclientes.dto.response.DireccionDTO;
import com.example.msclientes.service.DireccionService;
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
@RequestMapping("/api/v1/direcciones")
@Tag(name = "Direcciones", description = "CRUD de direcciones de clientes")
public class DireccionController {
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    @Operation(summary = "Listar direcciones")
    public ResponseEntity<CollectionModel<EntityModel<DireccionDTO>>> listar() {
        List<EntityModel<DireccionDTO>> direcciones = direccionService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(direcciones, linkTo(methodOn(DireccionController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener direccion por ID")
    public ResponseEntity<EntityModel<DireccionDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(direccionService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear direccion")
    public ResponseEntity<EntityModel<DireccionDTO>> crear(@Valid @RequestBody DireccionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(direccionService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar direccion")
    public ResponseEntity<EntityModel<DireccionDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody DireccionRequestDTO request) {
        return ResponseEntity.ok(toModel(direccionService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar direccion")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<DireccionDTO> toModel(DireccionDTO direccion) {
        return EntityModel.of(direccion,
                linkTo(methodOn(DireccionController.class).obtenerPorId(direccion.getId())).withSelfRel(),
                linkTo(methodOn(DireccionController.class).listar()).withRel("direcciones"),
                linkTo(methodOn(DireccionController.class).actualizar(direccion.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(DireccionController.class).eliminar(direccion.getId())).withRel("eliminar"));
    }
}
