package org.devsu.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MovimientoRequest {


    @NotNull
    private Date fecha;

    private String tipoMovimiento;

    @NotNull
    private BigDecimal valor;

    private BigDecimal saldo;

    private String nroCuenta;
}
