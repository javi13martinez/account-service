package com.bank.account_service.application.usecase;

import java.util.regex.Pattern;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

public class CreateCuentaBancariaUseCase {

    private final CuentaBancariaRepositoryPort repository;

    public CreateCuentaBancariaUseCase(CuentaBancariaRepositoryPort repository) {
        this.repository = repository;
    }

    public CuentaBancaria execute(String dniCliente, String tipoCuenta, Double total) {

        if (!DNI_PATTERN.matcher(dniCliente).matches()) {
            throw new InvalidValueException("CUENTA BANCARIA", "DNI CLIENTE", dniCliente);
        }

        if (total == null || total < 0) {
            throw new InvalidValueException("CUENTA BANCARIA", "TOTAL", String.valueOf(total));
        }

        return repository.create(dniCliente, tipoCuenta, total);
    }

    private static final Pattern DNI_PATTERN =
            Pattern.compile("\\d{8}[A-Z]");
}
