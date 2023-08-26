package org.devsu.controller;

import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.service.CuentaService;
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
@RequestMapping(value = "/cuentas/")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CuentaResponse>> getCuenta() throws DevsuError {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CuentaResponse> getCuenta(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@RequestBody CuentaRequest request) throws DevsuError {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CuentaResponse> actualizarCuenta(@RequestBody CuentaRequest request) throws DevsuError {
        return ResponseEntity.ok(service.editar(request));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CuentaResponse> eliminarCuenta(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.eliminar(id));
    }
}
