package com.example.msvehiculos.controller;

import com.example.msvehiculos.dto.request.VehiculoRequestDTO;
import com.example.msvehiculos.dto.response.VehiculoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.msvehiculos.service.VehiculoService;
import java.math.BigDecimal;
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
@RequestMapping("/api/v1/vehiculos")
@Tag(name = "Vehiculos", description = "CRUD y busquedas de vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    @Operation(summary = "Listar vehiculos")
    public ResponseEntity<CollectionModel<EntityModel<VehiculoDTO>>> listar() {
        List<EntityModel<VehiculoDTO>> vehiculos = vehiculoService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(vehiculos, linkTo(methodOn(VehiculoController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehiculo por ID")
    public ResponseEntity<EntityModel<VehiculoDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(vehiculoService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear vehiculo")
    public ResponseEntity<EntityModel<VehiculoDTO>> crear(@Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(vehiculoService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vehiculo")
    public ResponseEntity<EntityModel<VehiculoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.ok(toModel(vehiculoService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar vehiculo")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles/precio")
    @Operation(summary = "Buscar vehiculos disponibles por precio")
    public ResponseEntity<CollectionModel<EntityModel<VehiculoDTO>>> disponiblesPorPrecio(@RequestParam BigDecimal precio) {
        List<EntityModel<VehiculoDTO>> vehiculos = vehiculoService.findDisponiblesPorPrecio(precio).stream()
                .map(this::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(vehiculos, linkTo(methodOn(VehiculoController.class).disponiblesPorPrecio(precio)).withSelfRel()));
    }

    private EntityModel<VehiculoDTO> toModel(VehiculoDTO vehiculo) {
        return EntityModel.of(vehiculo,
                linkTo(methodOn(VehiculoController.class).obtenerPorId(vehiculo.getId())).withSelfRel(),
                linkTo(methodOn(VehiculoController.class).listar()).withRel("vehiculos"),
                linkTo(methodOn(VehiculoController.class).actualizar(vehiculo.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(VehiculoController.class).eliminar(vehiculo.getId())).withRel("eliminar"));
    }
}
