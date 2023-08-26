package org.devsu.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MovimientoResponse {

    private long id;

    private Date fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

}
