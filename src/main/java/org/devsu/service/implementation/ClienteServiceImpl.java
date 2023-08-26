package org.devsu.service.implementation;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.devsu.DTO.ClienteRequest;
import org.devsu.DTO.ClienteResponse;
import org.devsu.DTO.CuentaResponse;
import org.devsu.DTO.MovimientoResponse;
import org.devsu.DTO.Response;
import org.devsu.exception.DevsuError;
import org.devsu.exception.DevsuTypeException;
import org.devsu.models.Cliente;
import org.devsu.models.Cuenta;
import org.devsu.repository.ClienteRepository;
import org.devsu.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Override
    public List<ClienteResponse> getAll() throws DevsuError{
        try {
            List<Cliente> cuentas = repository.findAll();
            return cuentas.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public ClienteResponse getById(long id) throws DevsuError {
        try {
            log.info("[getById]: {}", id);
            ClienteResponse response = convertToDTO(repository.findById(id).orElse(null));
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public ClienteResponse crear(ClienteRequest request) throws DevsuError {
        try {
            log.info("[crear]: {}", request);
            Cliente entity = repository.save(convertToEntity(request));
            return convertToDTO(entity);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public ClienteResponse editar(ClienteRequest request) throws DevsuError {
        try {
            log.info("[editar]: {}", request);
            Cliente entity = repository.save(convertToEntity(request));
            return convertToDTO(entity);
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    @Override
    public ClienteResponse eliminar(long id) throws DevsuError {
        try {
            log.info("[eliminar]: {}", id);
            repository.deleteById(id);
            ClienteResponse response = new ClienteResponse();
            response.setId(id);
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new DevsuError("500", "Ocurrió un error al realizar operación", DevsuTypeException.DATABASE);
        }
    }

    private Cliente convertToEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setContrasena(request.getContrasena());
        cliente.setEstado(request.getEstado());
        cliente.setDireccion(request.getDireccion());
        cliente.setEdad(request.getEdad());
        cliente.setGenero(request.getGenero());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        return cliente;
    }

    private ClienteResponse convertToDTO(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        if (cliente == null) return response;
        response.setId(cliente.getId());
        response.setEstado(cliente.getEstado());
        response.setDireccion(cliente.getDireccion());
        response.setEdad(cliente.getEdad());
        response.setGenero(cliente.getGenero());
        response.setIdentificacion(cliente.getIdentificacion());
        response.setNombre(cliente.getNombre());
        response.setTelefono(cliente.getTelefono());
        return response;
    }
}
