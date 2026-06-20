package com.example.msclientes.controller;

import com.example.msclientes.dto.request.ClienteRequestDTO;
import com.example.msclientes.dto.response.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.msclientes.service.ClienteService;
import com.example.msclientes.mapper.ClienteMapper;
import com.example.msclientes.repository.ClienteRepository;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "CRUD y busquedas de clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna todos los clientes con enlaces HATEOAS.")
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO>>> listar() {
        List<EntityModel<ClienteDTO>> clientes = clienteService.findAll().stream().map(this::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(clientes, linkTo(methodOn(ClienteController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente segun su identificador.")
    public ResponseEntity<EntityModel<ClienteDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toModel(clienteService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Crea un cliente validando los datos recibidos.")
    public ResponseEntity<EntityModel<ClienteDTO>> crear(@Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(clienteService.save(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza todos los campos de un cliente existente.")
    public ResponseEntity<EntityModel<ClienteDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.ok(toModel(clienteService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por su identificador.")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/email")
    @Operation(summary = "Buscar clientes por email", description = "Busca clientes cuyo email contenga el texto indicado.")
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO>>> buscarPorEmail(@RequestParam String texto) {
        List<EntityModel<ClienteDTO>> clientes = clienteRepository.findByEmailContainingIgnoreCase(texto).stream()
                .map(ClienteMapper::toDTO)
                .map(this::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(clientes, linkTo(methodOn(ClienteController.class).buscarPorEmail(texto)).withSelfRel()));
    }

    private EntityModel<ClienteDTO> toModel(ClienteDTO cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).obtenerPorId(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).listar()).withRel("clientes"),
                linkTo(methodOn(ClienteController.class).actualizar(cliente.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(ClienteController.class).eliminar(cliente.getId())).withRel("eliminar"));
    }
}
