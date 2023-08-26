package org.devsu.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    private long cuentaId;
}
