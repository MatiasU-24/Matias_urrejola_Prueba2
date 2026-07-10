package com.example.mssucursales.controller;

import com.example.mssucursales.assembler.SucursalModelAssembler;
import com.example.mssucursales.dto.request.SucursalRequestDTO;
import com.example.mssucursales.dto.response.SucursalDTO;
import com.example.mssucursales.service.SucursalService;
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
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursales", description = "CRUD y busquedas de sucursales")
public class SucursalController {
    private static final Logger log = LoggerFactory.getLogger(SucursalController.class);
    private final SucursalService sucursalService;
    private final SucursalModelAssembler assembler;

    public SucursalController(SucursalService sucursalService, SucursalModelAssembler assembler) {
        this.sucursalService = sucursalService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar sucursales", description = "Retorna todas las sucursales con enlaces HATEOAS.")
    public ResponseEntity<CollectionModel<EntityModel<SucursalDTO>>> listar() {
        log.info("Solicitud GET /api/v1/sucursales");
        List<EntityModel<SucursalDTO>> sucursales = sucursalService.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(sucursales, linkTo(methodOn(SucursalController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sucursal por ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Sucursal encontrada"), @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    public ResponseEntity<EntityModel<SucursalDTO>> obtenerPorId(@PathVariable Integer id) {
        log.info("Solicitud GET /api/v1/sucursales/{}", id);
        return ResponseEntity.ok(assembler.toModel(sucursalService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear sucursal")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Sucursal creada"), @ApiResponse(responseCode = "400", description = "Datos invalidos")})
    public ResponseEntity<EntityModel<SucursalDTO>> crear(@Valid @RequestBody SucursalRequestDTO request) {
        log.info("Solicitud POST /api/v1/sucursales");
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(sucursalService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar sucursal")
    public ResponseEntity<EntityModel<SucursalDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody SucursalRequestDTO request) {
        log.info("Solicitud PUT /api/v1/sucursales/{}", id);
        return ResponseEntity.ok(assembler.toModel(sucursalService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sucursal")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Solicitud DELETE /api/v1/sucursales/{}", id);
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/operativas")
    @Operation(summary = "Listar sucursales operativas")
    public ResponseEntity<CollectionModel<EntityModel<SucursalDTO>>> operativas() {
        log.info("Solicitud GET /api/v1/sucursales/operativas");
        List<EntityModel<SucursalDTO>> sucursales = sucursalService.findOperativas().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(sucursales, linkTo(methodOn(SucursalController.class).operativas()).withSelfRel()));
    }

}
