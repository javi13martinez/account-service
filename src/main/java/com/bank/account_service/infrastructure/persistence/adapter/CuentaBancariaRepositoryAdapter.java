package com.bank.account_service.infrastructure.persistence.adapter;

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
    public CuentaBancaria create(final CuentaBancaria cuentaBancaria) {
        return CuentaBancariaEntityMapper.toDomain(jpaRepository.save(CuentaBancariaEntityMapper.toEntity(cuentaBancaria)));
    }


}
