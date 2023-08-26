package org.devsu;

import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.exception.DevsuError;
import org.devsu.models.Cuenta;
import org.devsu.repository.CuentaRepository;
import org.devsu.service.implementation.CuentaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    @InjectMocks
    CuentaServiceImpl service;

    @Mock
    CuentaRepository repository;

    @Test
    void testFindAllCuentas() throws DevsuError {
        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuenta1 = new Cuenta(1, "4546648", "Ahorro", BigDecimal.valueOf(2000), true, 1);
        Cuenta cuenta2 = new Cuenta(2, "2000187", "Corriente", BigDecimal.valueOf(0), true, 2);
        Cuenta cuenta3 = new Cuenta(3, "4547711", "Ahorro", BigDecimal.valueOf(500), true, 2);
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        Mockito.when(repository.findAll()).thenReturn(cuentas);
        List<CuentaResponse> cuentasList = service.getAll();
        assertEquals(3, cuentasList.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void testCreateCuenta() throws DevsuError {
        CuentaRequest request = new CuentaRequest("4546648", "Ahorro", BigDecimal.valueOf(2000), true, 1);
        CuentaResponse response = service.crear(request);
        Assertions.assertNotNull(response.getId());
    }


}
