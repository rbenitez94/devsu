package org.devsu.service;

import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;

import java.util.List;

public interface CuentaService {

    public List<CuentaResponse> getAll() throws DevsuError;

    public CuentaResponse getById(long id) throws DevsuError;

    public CuentaResponse crear(CuentaRequest request) throws DevsuError;

    public CuentaResponse editar(CuentaRequest request) throws DevsuError;

    public CuentaResponse eliminar(long id) throws DevsuError;

}
