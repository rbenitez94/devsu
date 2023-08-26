package org.devsu.controller;

import org.devsu.DTO.ClienteRequest;
import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.service.ClienteService;
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
@RequestMapping(value = "/clientes/")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClienteResponse>> getCuenta() throws DevsuError {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ClienteResponse> getCliente(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest request) throws DevsuError {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(@RequestBody ClienteRequest request) throws DevsuError {
        return ResponseEntity.ok(service.editar(request));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ClienteResponse> eliminarCliente(@PathVariable long id) throws DevsuError {
        return ResponseEntity.ok(service.eliminar(id));
    }
}
