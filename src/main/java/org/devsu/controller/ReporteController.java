package org.devsu.controller;

import org.devsu.DTO.MovimientoRequest;
import org.devsu.DTO.ReportePorCliente;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.service.MovimientoService;
import org.devsu.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reportes/")
public class ReporteController {

    @Autowired
    private ReporteService service;

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @GetMapping
    public ResponseEntity<List<ReportePorCliente>> getReporte(@RequestParam("cliente") long clienteId,
                                                             @RequestParam("fecha_ini") @DateTimeFormat(pattern = DATE_FORMAT) Date fechaInicio,
                                                             @RequestParam("fecha_fin") @DateTimeFormat(pattern = DATE_FORMAT)  Date fechaFin) throws DevsuError {
        return ResponseEntity.ok(service.getReporteTransacciones(clienteId, fechaInicio, fechaFin));
    }

}
