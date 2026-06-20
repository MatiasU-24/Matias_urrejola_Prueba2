package com.example.msvehiculos.controller;

import com.example.msvehiculos.dto.request.CategoriaRequestDTO;
import com.example.msvehiculos.dto.response.CategoriaDTO;
import com.example.msvehiculos.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "CRUD de categorias de vehiculos")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Listar categorias")
    public ResponseEntity<CollectionModel<EntityModel<CategoriaDTO>>> listar() {
        List<EntityModel<CategoriaDTO>> categorias = categoriaService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(categorias, linkTo(methodOn(CategoriaController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria por ID")
    public ResponseEntity<EntityModel<CategoriaDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(categoriaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear categoria")
    public ResponseEntity<EntityModel<CategoriaDTO>> crear(@Valid @RequestBody CategoriaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(categoriaService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoria")
    public ResponseEntity<EntityModel<CategoriaDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaRequestDTO request) {
        return ResponseEntity.ok(toModel(categoriaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoria")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<CategoriaDTO> toModel(CategoriaDTO categoria) {
        return EntityModel.of(categoria,
                linkTo(methodOn(CategoriaController.class).obtenerPorId(categoria.getId())).withSelfRel(),
                linkTo(methodOn(CategoriaController.class).listar()).withRel("categorias"),
                linkTo(methodOn(CategoriaController.class).actualizar(categoria.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(CategoriaController.class).eliminar(categoria.getId())).withRel("eliminar"));
    }
}
