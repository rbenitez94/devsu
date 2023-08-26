package org.devsu.repository;

import org.devsu.DTO.ReportePorCliente;
import org.devsu.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimiento m WHERE m.id = (select max(mm.id) FROM Movimiento mm WHERE mm.cuentaId = :cuentaId)")
    Movimiento findLastMovement(long cuentaId);

    @Query("SELECT sum(m.valor) FROM Movimiento m WHERE date(m.fecha) = date(:fecha) AND m.cuentaId = :cuentaId" +
            " AND m.valor < 0")
    Optional<BigDecimal> getAmountFromDate(long cuentaId, Date fecha);

    @Query("SELECT new org.devsu.DTO.ReportePorCliente(m.fecha AS fecha, cl.nombre AS cliente," +
            " c.nroCuenta AS nroCuenta, c.tipoCuenta AS tipoCuenta, c.saldoInicial AS saldoInicial," +
            " c.estado AS estado, m.valor AS valor, m.saldo AS saldo)" +
            " FROM Movimiento m" +
            " JOIN Cuenta c on m.cuentaId = c.id" +
            " JOIN Cliente cl on c.clienteId = cl.id" +
            " WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin" +
            " AND cl.id = :clienteId" +
            " ORDER BY m.fecha DESC")
    List<ReportePorCliente> getByClientAndDate(Date fechaInicio, Date fechaFin, long clienteId);
}
