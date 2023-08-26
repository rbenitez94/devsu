package org.devsu.service.implementation;

import lombok.extern.log4j.Log4j2;
import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.exception.DevsuTypeException;
import org.devsu.models.Cliente;
import org.devsu.models.Cuenta;
import org.devsu.repository.CuentaRepository;
import org.devsu.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    CuentaRepository repository;

    @Override
    public List<CuentaResponse> getAll() throws DevsuError{
        try {
            List<Cuenta> cuentas = repository.findAll();
            return cuentas.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public CuentaResponse getById(long id) throws DevsuError {
        try {
            log.info("[getById]: {}", id);
            CuentaResponse response = convertToDTO(repository.findById(id).orElse(null));
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public CuentaResponse crear(CuentaRequest request) throws DevsuError {
        try {
            log.info("[crear]: {}", request);
            Cuenta entity = repository.save(convertToEntity(request));
            return convertToDTO(entity);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public CuentaResponse editar(CuentaRequest request) throws DevsuError {
        try {
            log.info("[editar]: {}", request);
            Cuenta entity = repository.save(convertToEntity(request));
            return convertToDTO(entity);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public CuentaResponse eliminar(long id) throws DevsuError {
        try {
            log.info("[eliminar]: {}", id);
            repository.deleteById(id);
            CuentaResponse response = new CuentaResponse();
            response.setId(id);
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    private Cuenta convertToEntity(CuentaRequest request) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuenta(request.getNroCuenta());
        cuenta.setEstado(request.getEstado());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setClienteId(request.getClienteId());
        return cuenta;
    }

    private CuentaResponse convertToDTO(Cuenta cuenta) {
        CuentaResponse response = new CuentaResponse();
        if (cuenta == null) return response;
        response.setId(cuenta.getId());
        response.setEstado(cuenta.getEstado());
        response.setNroCuenta(cuenta.getNroCuenta());
        response.setSaldoInicial(cuenta.getSaldoInicial());
        response.setTipoCuenta(cuenta.getTipoCuenta());
        return response;
    }
}
