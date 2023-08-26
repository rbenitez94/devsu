package org.devsu.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReportePorCliente {

    private Date fecha;

    private String cliente;

    private String nroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estado;

    private BigDecimal valor;

    private BigDecimal saldo;

}
