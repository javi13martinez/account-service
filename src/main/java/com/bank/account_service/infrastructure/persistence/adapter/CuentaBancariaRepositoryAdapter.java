package com.bank.account_service.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;
import com.bank.account_service.infrastructure.persistence.mapper.CuentaBancariaEntityMapper;
import com.bank.account_service.infrastructure.persistence.repository.CuentaBancariaJpaRepository;

@Repository
public class CuentaBancariaRepositoryAdapter implements CuentaBancariaRepositoryPort {

    private CuentaBancariaJpaRepository jpaRepository;

    public CuentaBancariaRepositoryAdapter(CuentaBancariaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<CuentaBancaria> findById(final Long id) {
        return jpaRepository.findById(id.toString()).map(CuentaBancariaEntityMapper::toDomain);
    }

    @Override
    public CuentaBancaria save(final CuentaBancaria cuentaBancaria) {
        return CuentaBancariaEntityMapper.toDomain(jpaRepository.save(CuentaBancariaEntityMapper.toEntity(cuentaBancaria)));
    }


}
