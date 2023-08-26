package org.devsu.controller;

import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.MovimientoRequest;
import org.devsu.DTO.MovimientoResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movimientos/")
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<MovimientoResponse>> getCuenta() throws DevsuError {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MovimientoResponse> getMovimiento(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MovimientoResponse> crearMovimiento(@RequestBody MovimientoRequest request) throws DevsuError {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<MovimientoResponse> actualizarMovimiento(@RequestBody MovimientoRequest request) throws DevsuError {
        return ResponseEntity.ok(service.editar(request));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<MovimientoResponse> eliminarMovimiento(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.eliminar(id));
    }
}
