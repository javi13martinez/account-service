package com.bank.account_service.domain.port.out;

import java.util.Optional;

import com.bank.account_service.domain.model.Cliente;

public interface ClienteRepositoryPort {
    Optional<Cliente> findByDni(final String dni);
}
