package com.bank.account_service.domain.port.out;

import com.bank.account_service.domain.model.CuentaBancaria;

public interface CuentaBancariaRepositoryPort {
    CuentaBancaria create(final CuentaBancaria cuentaBancaria);
}
