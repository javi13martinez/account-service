package com.bank.account_service.application.usecase;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.exception.ResourceNotFoundException;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

public class UpdateSaldoCuentaUseCase {
    private final CuentaBancariaRepositoryPort repository;

    public UpdateSaldoCuentaUseCase(CuentaBancariaRepositoryPort repository) {
        this.repository = repository;
    }

    public CuentaBancaria execute(final Long id, final Double total) {

        if (id == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "ID", String.valueOf(id));
        }

        if (total == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "TOTAL", String.valueOf(total));
        }

        CuentaBancaria cuentaBancaria = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUENTA BANCARIA", "ID", id.toString()));
        cuentaBancaria.setTotal(total);
        return repository.save(cuentaBancaria);
    }
}
