package org.devsu.service;

import org.devsu.DTO.ReportePorCliente;
import org.devsu.exception.DevsuError;

import java.util.Date;
import java.util.List;

public interface ReporteService {
    List<ReportePorCliente> getReporteTransacciones(long clienteId, Date fechaInicio, Date fechaFin) throws DevsuError;

}
