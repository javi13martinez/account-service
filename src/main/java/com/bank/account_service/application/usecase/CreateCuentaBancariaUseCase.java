package com.bank.account_service.application.usecase;

import org.springframework.transaction.annotation.Transactional;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

import static com.bank.account_service.shared.SharedRegex.DNI_PATTERN;

public class CreateCuentaBancariaUseCase {

    private CuentaBancariaRepositoryPort cuentaRepository;

    public CreateCuentaBancariaUseCase(CuentaBancariaRepositoryPort cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public CuentaBancaria execute(String dniCliente, String tipoCuenta, Double total) {

        if (!DNI_PATTERN.matcher(dniCliente).matches()) {
            throw new InvalidValueException("CUENTA BANCARIA", "DNI CLIENTE", dniCliente);
        }

        if (total == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "TOTAL", String.valueOf(total));
        }

        return cuentaRepository.save(new CuentaBancaria(dniCliente, tipoCuenta, total));
    }
}
