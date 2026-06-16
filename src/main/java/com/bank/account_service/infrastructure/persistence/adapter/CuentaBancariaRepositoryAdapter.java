package com.bank.account_service.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;
import com.bank.account_service.infrastructure.persistence.entity.ClienteEntity;
import com.bank.account_service.infrastructure.persistence.mapper.CuentaBancariaEntityMapper;
import com.bank.account_service.infrastructure.persistence.repository.ClienteJpaRepository;
import com.bank.account_service.infrastructure.persistence.repository.CuentaBancariaJpaRepository;

@Repository
public class CuentaBancariaRepositoryAdapter implements CuentaBancariaRepositoryPort {

    private CuentaBancariaJpaRepository jpaRepository;
    private ClienteJpaRepository clienteJpaRepository;

    public CuentaBancariaRepositoryAdapter(
            CuentaBancariaJpaRepository jpaRepository,
            ClienteJpaRepository clienteJpaRepository
    ) {
        this.jpaRepository = jpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Optional<CuentaBancaria> findById(Long id) {
        return jpaRepository.findById(id.toString()).map(CuentaBancariaEntityMapper::toDomain);
    }

    @Override
    public CuentaBancaria save(CuentaBancaria cuentaBancaria) {
        ClienteEntity cliente = this.clienteJpaRepository.findById(cuentaBancaria.getDniCliente())
                .orElseGet(() -> clienteJpaRepository.save(new ClienteEntity(cuentaBancaria.getDniCliente())));

        return CuentaBancariaEntityMapper.toDomain(jpaRepository.save(CuentaBancariaEntityMapper.toEntity(cuentaBancaria, cliente)));
    }
}
