package com.example.msreportes.assembler;

import com.example.msreportes.controller.ReporteController;
import com.example.msreportes.dto.response.ReporteDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<ReporteDTO, EntityModel<ReporteDTO>> {
    @Override
    public EntityModel<ReporteDTO> toModel(ReporteDTO reporte) {
        return EntityModel.of(reporte,
                linkTo(methodOn(ReporteController.class).obtenerPorId(reporte.getId())).withSelfRel(),
                linkTo(methodOn(ReporteController.class).listar()).withRel("reportes"),
                linkTo(methodOn(ReporteController.class).generarConsolidado()).withRel("consolidado"),
                linkTo(methodOn(ReporteController.class).actualizar(reporte.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(ReporteController.class).eliminar(reporte.getId())).withRel("eliminar"));
    }
}
