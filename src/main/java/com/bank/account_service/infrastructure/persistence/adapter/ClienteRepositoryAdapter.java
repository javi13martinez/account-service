package com.bank.account_service.infrastructure.persistence.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.infrastructure.persistence.mapper.ClienteEntityMapper;
import com.bank.account_service.infrastructure.persistence.repository.ClienteJpaRepository;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private ClienteJpaRepository jpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return jpaRepository.findById(dni)
                .map(ClienteEntityMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream().map(ClienteEntityMapper::toDomain).toList();
    }

    @Override
    public List<Cliente> findAdultos() {
        return jpaRepository.findByFechaNacimientoBefore(LocalDate.now().minusYears(18))
                .stream().map(ClienteEntityMapper::toDomain).toList();
    }

    @Override
    public List<Cliente> findClientesConSaldoMayorA(Double total) {
        return jpaRepository.findClientesConSaldoMayorA(total).stream().map(ClienteEntityMapper::toDomain).toList();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return ClienteEntityMapper.toDomain(jpaRepository.save(ClienteEntityMapper.toEntity(cliente)));
    }
}
