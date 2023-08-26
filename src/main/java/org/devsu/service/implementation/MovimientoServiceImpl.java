package org.devsu.service.implementation;

import lombok.extern.log4j.Log4j2;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.MovimientoRequest;
import org.devsu.DTO.MovimientoResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.exception.DevsuTypeException;
import org.devsu.models.Cuenta;
import org.devsu.models.Movimiento;
import org.devsu.repository.CuentaRepository;
import org.devsu.repository.MovimientoRepository;
import org.devsu.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository repository;

    @Autowired
    CuentaRepository cuentaRepository;

    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(1000);

    @Override
    public List<MovimientoResponse> getAll() throws DevsuError{
        try {
            List<Movimiento> cuentas = repository.findAll();
            return cuentas.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public MovimientoResponse getById(long id) throws DevsuError {
        try {
            log.info("[getById]: {}", id);
            MovimientoResponse response = convertToDTO(repository.findById(id).orElse(null));
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public MovimientoResponse crear(MovimientoRequest request) throws DevsuError {
        log.info("[crear]: {}, {}", request.getFecha().toString(), request.getValor());
        Cuenta cuenta = cuentaRepository.findByNroCuenta(request.getNroCuenta());
        if (cuenta == null) {
            log.error("Cuenta {} no encontrada", request.getNroCuenta());
            throw new DevsuError("4003", "Cuenta Inválida", DevsuTypeException.APPLICATION);
        }
        Movimiento lastMovimiento = repository.findLastMovement(cuenta.getId());
        Movimiento movimiento = convertToEntity(request);
        movimiento.setCuentaId(cuenta.getId());
        BigDecimal saldo = lastMovimiento != null ? lastMovimiento.getSaldo() : cuenta.getSaldoInicial();
        if (movimiento.getValor().compareTo(BigDecimal.ZERO) > 0) {
            movimiento.setSaldo(saldo.add(movimiento.getValor()));
            Movimiento entity = repository.save(movimiento);
            return convertToDTO(entity);
        }
        if (saldo.compareTo(movimiento.getValor().abs()) < 0) {
            log.error("Validación fallida");
            throw new DevsuError("4001", "Saldo insuficiente", DevsuTypeException.APPLICATION);
        }
        BigDecimal todayAmount = repository.getAmountFromDate(cuenta.getId(), request.getFecha()).orElse(BigDecimal.ZERO);
        if (todayAmount.abs().compareTo(MAX_AMOUNT) >= 0) {
            log.error("Validación fallida");
            throw new DevsuError("4002", "Máximo permitido por día superado", DevsuTypeException.APPLICATION);
        }
        movimiento.setSaldo(saldo.add(movimiento.getValor()));

        Movimiento entity = repository.save(movimiento);
        return convertToDTO(entity);
    }

    @Override
    public MovimientoResponse editar(MovimientoRequest request) throws DevsuError {
        log.info("[editar]");
        try {
            Movimiento entity = repository.save(convertToEntity(request));
            return convertToDTO(entity);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public MovimientoResponse eliminar(long id) throws DevsuError {
        log.info("[eliminar]: {}", id);
        try {
            repository.deleteById(id);
            MovimientoResponse response = new MovimientoResponse();
            response.setId(id);
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    private Movimiento convertToEntity(MovimientoRequest request) {
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(request.getFecha());
        movimiento.setSaldo(request.getSaldo());
        movimiento.setValor(request.getValor());
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        return movimiento;
    }

    private MovimientoResponse convertToDTO(Movimiento movimiento) {
        MovimientoResponse response = new MovimientoResponse();
        if (movimiento == null) return response;
        response.setId(movimiento.getId());
        response.setTipoMovimiento(movimiento.getTipoMovimiento());
        response.setFecha(movimiento.getFecha());
        response.setValor(movimiento.getValor());
        response.setSaldo(movimiento.getSaldo());
        return response;
    }
}
