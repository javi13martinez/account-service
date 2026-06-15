package com.bank.account_service.application.usecase;

import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

public class CreateCuentaBancariaUseCase {

    private final ClienteRepositoryPort clienteRepository;
    private final CuentaBancariaRepositoryPort cuentaRepository;

    public CreateCuentaBancariaUseCase(CuentaBancariaRepositoryPort cuentaRepository, ClienteRepositoryPort clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public CuentaBancaria execute(final String dniCliente, final String tipoCuenta, final Double total) {

        if (!DNI_PATTERN.matcher(dniCliente).matches()) {
            throw new InvalidValueException("CUENTA BANCARIA", "DNI CLIENTE", dniCliente);
        }

        if (total == null) {
            throw new InvalidValueException("CUENTA BANCARIA", "TOTAL", String.valueOf(total));
        }

        if (clienteRepository.findByDni(dniCliente).isEmpty()) {
            clienteRepository.save(new Cliente(dniCliente));
        }

        return cuentaRepository.save(new CuentaBancaria(dniCliente, tipoCuenta, total));
    }

    private static final Pattern DNI_PATTERN =
            Pattern.compile("\\d{8}[A-Z]");
}
