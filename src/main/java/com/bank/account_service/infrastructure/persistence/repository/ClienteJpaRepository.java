package com.bank.account_service.infrastructure.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.account_service.infrastructure.persistence.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {

    @Query("""
        SELECT c FROM ClienteEntity c
        WHERE c.fechaNacimiento <= :date
    """)
    List<ClienteEntity> findByFechaNacimientoBefore(LocalDate date);

    @Query("""
        SELECT DISTINCT c
        FROM ClienteEntity c
        JOIN c.cuentas cb
        GROUP BY c
        HAVING SUM(cb.total) > :total
    """)
    List<ClienteEntity> findClientesConSaldoMayorA(Double total);

}
