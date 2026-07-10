package com.example.msreportes.service;

import com.example.msreportes.client.PagoClient;
import com.example.msreportes.client.ReservaClient;
import com.example.msreportes.client.dto.PagoResumenDTO;
import com.example.msreportes.dto.request.ReporteRequestDTO;
import com.example.msreportes.dto.response.ReporteDTO;
import com.example.msreportes.entity.Reporte;
import com.example.msreportes.exception.ResourceNotFoundException;
import com.example.msreportes.mapper.ReporteMapper;
import com.example.msreportes.repository.ReporteRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {
    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);
    private final ReporteRepository reporteRepository;
    private final ReservaClient reservaClient;
    private final PagoClient pagoClient;

    public ReporteService(ReporteRepository reporteRepository, ReservaClient reservaClient, PagoClient pagoClient) {
        this.reporteRepository = reporteRepository;
        this.reservaClient = reservaClient;
        this.pagoClient = pagoClient;
    }

    public List<ReporteDTO> findAll() {
        log.info("Listando reportes");
        return reporteRepository.findAll().stream().map(ReporteMapper::toDTO).toList();
    }

    public ReporteDTO findById(Integer id) {
        log.info("Buscando Reporte con id {}", id);
        return ReporteMapper.toDTO(buscarEntidad(id));
    }

    public ReporteDTO save(ReporteRequestDTO request) {
        try {
            log.info("Creando Reporte");
            Reporte reporte = ReporteMapper.toEntity(request);
            return ReporteMapper.toDTO(reporteRepository.save(reporte));
        } catch (RuntimeException ex) {
            log.error("Error al crear Reporte", ex);
            throw ex;
        }
    }

    public ReporteDTO update(Integer id, ReporteRequestDTO request) {
        try {
            log.info("Actualizando Reporte con id {}", id);
            Reporte reporte = buscarEntidad(id);
            reporte.setTitulo(request.getTitulo());
            reporte.setDescripcion(request.getDescripcion());
            reporte.setTotalReservas(request.getTotalReservas());
            reporte.setTotalIngresos(request.getTotalIngresos());
            reporte.setPublicado(request.isPublicado());
            reporte.setFechaGeneracion(request.getFechaGeneracion());
            reporte.setTipoReporte(request.getTipoReporte());
            return ReporteMapper.toDTO(reporteRepository.save(reporte));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Reporte", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Reporte con id {}", id);
        reporteRepository.delete(buscarEntidad(id));
    }

    public ReporteDTO generarConsolidado() {
        try {
            log.info("Generando reporte consolidado desde reservas y pagos");
            var reservas = reservaClient.listarReservas();
            var pagos = pagoClient.listarPagos();
            BigDecimal totalIngresos = pagos.stream()
                    .filter(PagoResumenDTO::isPagado)
                    .map(PagoResumenDTO::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Reporte reporte = new Reporte();
            reporte.setTitulo("Reporte consolidado");
            reporte.setDescripcion("Consolidado automatico de reservas y pagos");
            reporte.setTotalReservas(reservas.getContent().size());
            reporte.setTotalIngresos(totalIngresos);
            reporte.setPublicado(false);
            reporte.setFechaGeneracion(LocalDateTime.now());
            reporte.setTipoReporte("Consolidado");
            return ReporteMapper.toDTO(reporteRepository.save(reporte));
        } catch (RuntimeException ex) {
            log.error("Error al generar reporte consolidado", ex);
            throw ex;
        }
    }

    private Reporte buscarEntidad(Integer id) {
        return reporteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con id " + id));
    }
}
