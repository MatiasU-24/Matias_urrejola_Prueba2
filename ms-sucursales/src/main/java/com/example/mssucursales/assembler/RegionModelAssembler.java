package com.example.mssucursales.assembler;

import com.example.mssucursales.controller.RegionController;
import com.example.mssucursales.dto.response.RegionDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<RegionDTO, EntityModel<RegionDTO>> {
    @Override
    public EntityModel<RegionDTO> toModel(RegionDTO region) {
        return EntityModel.of(region,
                linkTo(methodOn(RegionController.class).obtenerPorId(region.getId())).withSelfRel(),
                linkTo(methodOn(RegionController.class).listar()).withRel("regiones"),
                linkTo(methodOn(RegionController.class).actualizar(region.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(RegionController.class).eliminar(region.getId())).withRel("eliminar"));
    }
}
