package com.bank.account_service.application.usecase;

import java.util.regex.Pattern;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.exception.ResourceNotFoundException;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;

public class GetClienteByDniUseCase {
    private final ClienteRepositoryPort repository;

    public GetClienteByDniUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public Cliente execute(String dni) {

        if (!DNI_PATTERN.matcher(dni).matches()) {
            throw new InvalidValueException("CLIENTE", "DNI", dni);
        }

        return repository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("CLIENTE", "DNI", dni));
    }

    private static final Pattern DNI_PATTERN =
            Pattern.compile("\\d{8}[A-Z]");
}
