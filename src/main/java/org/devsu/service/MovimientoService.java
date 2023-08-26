package org.devsu.service;

import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.MovimientoRequest;
import org.devsu.DTO.MovimientoResponse;
import org.devsu.exception.DevsuError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovimientoService {

    List<MovimientoResponse> getAll() throws DevsuError;

    MovimientoResponse getById(long id) throws DevsuError;

    MovimientoResponse crear(MovimientoRequest request) throws DevsuError;

    MovimientoResponse editar(MovimientoRequest request) throws DevsuError;

    MovimientoResponse eliminar(long id) throws DevsuError;

}
