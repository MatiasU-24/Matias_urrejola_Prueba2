package com.example.mssucursales.controller;

import com.example.mssucursales.assembler.RegionModelAssembler;
import com.example.mssucursales.dto.request.RegionRequestDTO;
import com.example.mssucursales.dto.response.RegionDTO;
import com.example.mssucursales.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/regiones")
@Tag(name = "Regiones", description = "CRUD de regiones")
public class RegionController {
    private static final Logger log = LoggerFactory.getLogger(RegionController.class);
    private final RegionService regionService;
    private final RegionModelAssembler assembler;

    public RegionController(RegionService regionService, RegionModelAssembler assembler) {
        this.regionService = regionService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar regiones")
    public ResponseEntity<CollectionModel<EntityModel<RegionDTO>>> listar() {
        log.info("Solicitud GET /api/v1/regiones");
        List<EntityModel<RegionDTO>> regiones = regionService.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(regiones, linkTo(methodOn(RegionController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener region por ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Region encontrada"), @ApiResponse(responseCode = "404", description = "Region no encontrada")})
    public ResponseEntity<EntityModel<RegionDTO>> obtenerPorId(@PathVariable Integer id) {
        log.info("Solicitud GET /api/v1/regiones/{}", id);
        return ResponseEntity.ok(assembler.toModel(regionService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear region")
    public ResponseEntity<EntityModel<RegionDTO>> crear(@Valid @RequestBody RegionRequestDTO request) {
        log.info("Solicitud POST /api/v1/regiones");
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(regionService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar region")
    public ResponseEntity<EntityModel<RegionDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody RegionRequestDTO request) {
        log.info("Solicitud PUT /api/v1/regiones/{}", id);
        return ResponseEntity.ok(assembler.toModel(regionService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar region")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Solicitud DELETE /api/v1/regiones/{}", id);
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
