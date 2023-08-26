package org.devsu.service;

import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.MovimientoRequest;
import org.devsu.DTO.MovimientoResponse;
import org.devsu.exception.DevsuError;

import java.util.List;

public interface MovimientoService {

    List<MovimientoResponse> getAll() throws DevsuError;

    public MovimientoResponse getById(long id) throws DevsuError;

    public MovimientoResponse crear(MovimientoRequest request) throws DevsuError;

    public MovimientoResponse editar(MovimientoRequest request) throws DevsuError;

    public MovimientoResponse eliminar(long id) throws DevsuError;

}
