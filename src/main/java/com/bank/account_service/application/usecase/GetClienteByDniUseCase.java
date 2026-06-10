package com.bank.account_service.application.usecase;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;

public class GetClienteByDniUseCase {
    private final ClienteRepositoryPort repository;

    public GetClienteByDniUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public Cliente execute(String dni) {
        return repository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
    }
}
