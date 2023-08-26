package org.devsu.service.implementation;

import lombok.extern.log4j.Log4j2;
import org.devsu.DTO.ReportePorCliente;
import org.devsu.exception.DevsuError;
import org.devsu.exception.DevsuTypeException;
import org.devsu.repository.MovimientoRepository;
import org.devsu.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Override
    public List<ReportePorCliente> getReporteTransacciones(long clienteId, Date fechaInicio, Date fechaFin) throws DevsuError {
        try {
            log.info("[getReporteTransacciones]: {}, {}, {}", clienteId, fechaInicio.toString(), fechaFin.toString());
            return movimientoRepository.getByClientAndDate(fechaInicio, fechaFin, clienteId);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }
}
