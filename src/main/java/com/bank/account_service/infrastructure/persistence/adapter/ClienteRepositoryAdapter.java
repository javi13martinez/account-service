package com.bank.account_service.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.infrastructure.persistence.entity.ClienteEntity;
import com.bank.account_service.infrastructure.persistence.repository.ClienteJpaRepository;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {
    private final ClienteJpaRepository jpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return jpaRepository.findById(dni)
                .map(this::toDomain);
    }

    private Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getDni(),
                entity.getNombre(),
                entity.getApellido1(),
                entity.getApellido2(),
                entity.getFechaNacimiento()
        );
    }

    private ClienteEntity toEntity(Cliente customer) {
        return new ClienteEntity(
                customer.getDni(),
                customer.getNombre(),
                customer.getApellido1(),
                customer.getApellido2(),
                customer.getFechaNacimiento()
        );
    }
}
