package org.devsu.repository;

import org.devsu.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Cliente, Long> {



    @Query("SELECT sum(m.valor) FROM Movimiento m WHERE date(m.fecha) = date(:fecha) AND m.cuentaId = :cuentaId" +
            " AND m.valor < 0")
    Optional<BigDecimal> getAmountFromDate(long cuentaId, Date fecha);
}
