package org.devsu.service;

import org.devsu.DTO.ClienteRequest;
import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    List<ClienteResponse> getAll() throws DevsuError;

    ClienteResponse getById(long id) throws DevsuError;

    ClienteResponse crear(ClienteRequest request) throws DevsuError;

    ClienteResponse editar(ClienteRequest request) throws DevsuError;

    ClienteResponse eliminar(long id) throws DevsuError;

}
