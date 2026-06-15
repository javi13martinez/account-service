package com.bank.account_service.domain.port.out;

import java.util.Optional;

import com.bank.account_service.domain.model.CuentaBancaria;

public interface CuentaBancariaRepositoryPort {
    Optional<CuentaBancaria> findById(Long id);
    CuentaBancaria save(CuentaBancaria cuentaBancaria);
}
