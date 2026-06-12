package com.bank.account_service.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.account_service.infrastructure.persistence.entity.CuentaBancariaEntity;

public interface CuentaBancariaJpaRepository extends JpaRepository<CuentaBancariaEntity, String> {
}
