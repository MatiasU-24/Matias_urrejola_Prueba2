package com.example.mssucursales.assembler;

import com.example.mssucursales.controller.SucursalController;
import com.example.mssucursales.dto.response.SucursalDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<SucursalDTO, EntityModel<SucursalDTO>> {
    @Override
    public EntityModel<SucursalDTO> toModel(SucursalDTO sucursal) {
        return EntityModel.of(sucursal,
                linkTo(methodOn(SucursalController.class).obtenerPorId(sucursal.getId())).withSelfRel(),
                linkTo(methodOn(SucursalController.class).listar()).withRel("sucursales"),
                linkTo(methodOn(SucursalController.class).operativas()).withRel("operativas"),
                linkTo(methodOn(SucursalController.class).actualizar(sucursal.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(SucursalController.class).eliminar(sucursal.getId())).withRel("eliminar"));
    }
}
