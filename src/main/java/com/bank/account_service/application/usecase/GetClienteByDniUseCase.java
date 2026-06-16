package com.bank.account_service.application.usecase;

import com.bank.account_service.domain.exception.InvalidValueException;
import com.bank.account_service.domain.exception.ResourceNotFoundException;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;

import static com.bank.account_service.shared.SharedRegex.DNI_PATTERN;

public class GetClienteByDniUseCase {

    private ClienteRepositoryPort repository;

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
}
