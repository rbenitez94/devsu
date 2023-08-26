package org.devsu.service;

import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.exception.DevsuError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CuentaService {

    List<CuentaResponse> getAll() throws DevsuError;

    CuentaResponse getById(long id) throws DevsuError;

    CuentaResponse crear(CuentaRequest request) throws DevsuError;

    CuentaResponse editar(CuentaRequest request) throws DevsuError;

    CuentaResponse eliminar(long id) throws DevsuError;

}
