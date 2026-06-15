package com.bank.account_service.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.infrastructure.persistence.mapper.ClienteEntityMapper;
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
                .map(ClienteEntityMapper::toDomain);
    }

    @Override
    public Cliente create(Cliente cliente) {
        return ClienteEntityMapper.toDomain(jpaRepository.save(ClienteEntityMapper.toEntity(cliente)));
    }
}
