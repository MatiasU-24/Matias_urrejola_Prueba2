package com.example.mspagos.service;

import com.example.mspagos.client.ReservaClient;
import com.example.mspagos.client.dto.ReservaDTO;
import com.example.mspagos.dto.request.PagoRequestDTO;
import com.example.mspagos.dto.response.PagoDTO;
import com.example.mspagos.entity.Pago;
import com.example.mspagos.exception.ResourceNotFoundException;
import com.example.mspagos.mapper.PagoMapper;
import com.example.mspagos.repository.PagoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PagoService {
    private static final Logger log = LoggerFactory.getLogger(PagoService.class);
    private final PagoRepository pagoRepository;
    private final ReservaClient reservaClient;

    public PagoService(PagoRepository pagoRepository, ReservaClient reservaClient) {
        this.pagoRepository = pagoRepository;
        this.reservaClient = reservaClient;
    }

    public List<PagoDTO> findAll() {
        log.info("Listando pagos");
        return pagoRepository.findAll().stream().map(PagoMapper::toDTO).toList();
    }

    public PagoDTO findById(Integer id) {
        log.info("Buscando Pago con id {}", id);
        return PagoMapper.toDTO(buscarEntidad(id));
    }

    public PagoDTO save(PagoRequestDTO request) {
        try {
            log.info("Creando Pago");
            validarMontoReserva(request);
            Pago pago = PagoMapper.toEntity(request);
            return PagoMapper.toDTO(pagoRepository.save(pago));
        } catch (RuntimeException ex) {
            log.error("Error al crear Pago", ex);
            throw ex;
        }
    }

    public PagoDTO update(Integer id, PagoRequestDTO request) {
        try {
            log.info("Actualizando Pago con id {}", id);
            validarMontoReserva(request);
            Pago pago = buscarEntidad(id);
            pago.setCodigoTransaccion(request.getCodigoTransaccion());
            pago.setMetodoPago(request.getMetodoPago());
            pago.setMonto(request.getMonto());
            pago.setNumeroCuotas(request.getNumeroCuotas());
            pago.setPagado(request.isPagado());
            pago.setFechaPago(request.getFechaPago());
            pago.setReservaId(request.getReservaId());
            return PagoMapper.toDTO(pagoRepository.save(pago));
        } catch (RuntimeException ex) {
            log.error("Error al actualizar Pago", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Pago con id {}", id);
        pagoRepository.delete(buscarEntidad(id));
    }

    private Pago buscarEntidad(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id " + id));
    }

    private void validarMontoReserva(PagoRequestDTO request) {
        ReservaDTO reserva = reservaClient.obtenerReservaPorId(request.getReservaId());
        BigDecimal montoReserva = reserva.getMontoTotal();
        if (montoReserva != null && request.getMonto().compareTo(montoReserva) != 0) {
            throw new IllegalStateException("El monto del pago debe coincidir con el monto total de la reserva");
        }
    }
}
