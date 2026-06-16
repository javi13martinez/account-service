package com.bank.account_service.application.usecase;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.exception.ResourceNotFoundException;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

public class UpdateSaldoCuentaUseCase {
    private CuentaBancariaRepositoryPort repository;

    public UpdateSaldoCuentaUseCase(CuentaBancariaRepositoryPort repository) {
        this.repository = repository;
    }

    public CuentaBancaria execute(Long id, Double total) {

        if (id == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "ID", String.valueOf((Object) null));
        }

        if (total == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "TOTAL", String.valueOf((Object) null));
        }

        CuentaBancaria cuentaBancaria = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUENTA BANCARIA", "ID", id.toString()));
        cuentaBancaria.setTotal(total);
        return repository.save(cuentaBancaria);
    }
}
