package com.bank.account_service.application.usecase;

import java.util.List;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;

public class GetClientesUseCase {

    private final ClienteRepositoryPort repository;

    public GetClientesUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Cliente> execute() {
        return repository.findAll();
    }
}
