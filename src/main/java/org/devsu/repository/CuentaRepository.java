package org.devsu.repository;

import org.devsu.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.nroCuenta = ?1")
    Cuenta findByNroCuenta(String nroCuenta);
}
