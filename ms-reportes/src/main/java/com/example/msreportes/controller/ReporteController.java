package com.example.msreportes.controller;

import com.example.msreportes.assembler.ReporteModelAssembler;
import com.example.msreportes.dto.request.ReporteRequestDTO;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.service.ReporteService;
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
@RequestMapping("/api/v1/reportes")
@Tag(name = "Reportes", description = "CRUD y consolidado de reportes")
public class ReporteController {
    private static final Logger log = LoggerFactory.getLogger(ReporteController.class);
    private final ReporteService reporteService;
    private final ReporteModelAssembler assembler;

    public ReporteController(ReporteService reporteService, ReporteModelAssembler assembler) {
        this.reporteService = reporteService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar reportes")
    public ResponseEntity<CollectionModel<EntityModel<ReporteDTO>>> listar() {
        log.info("Solicitud GET /api/v1/reportes");
        List<EntityModel<ReporteDTO>> reportes = reporteService.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(reportes, linkTo(methodOn(ReporteController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reporte por ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Reporte encontrado"), @ApiResponse(responseCode = "404", description = "Reporte no encontrado")})
    public ResponseEntity<EntityModel<ReporteDTO>> obtenerPorId(@PathVariable Integer id) {
        log.info("Solicitud GET /api/v1/reportes/{}", id);
        return ResponseEntity.ok(assembler.toModel(reporteService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear reporte")
    public ResponseEntity<EntityModel<ReporteDTO>> crear(@Valid @RequestBody ReporteRequestDTO request) {
        log.info("Solicitud POST /api/v1/reportes");
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(reporteService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reporte")
    public ResponseEntity<EntityModel<ReporteDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ReporteRequestDTO request) {
        log.info("Solicitud PUT /api/v1/reportes/{}", id);
        return ResponseEntity.ok(assembler.toModel(reporteService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reporte")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Solicitud DELETE /api/v1/reportes/{}", id);
        reporteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/consolidado")
    @Operation(summary = "Generar reporte consolidado", description = "Consulta reservas y pagos mediante Feign para crear un reporte consolidado.")
    public ResponseEntity<EntityModel<ReporteDTO>> generarConsolidado() {
        log.info("Solicitud POST /api/v1/reportes/consolidado");
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(reporteService.generarConsolidado()));
    }
}
