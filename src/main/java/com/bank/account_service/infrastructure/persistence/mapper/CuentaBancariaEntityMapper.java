package com.bank.account_service.infrastructure.persistence.mapper;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.infrastructure.persistence.entity.CuentaBancariaEntity;

public class CuentaBancariaEntityMapper {

    public static CuentaBancaria toDomain(CuentaBancariaEntity entity) {
        return new CuentaBancaria(
                entity.getId(),
                entity.getDniCliente(),
                entity.getTipoCuenta(),
                entity.getTotal()
        );
    }

    public static CuentaBancariaEntity toEntity(CuentaBancaria cliente) {
        return new CuentaBancariaEntity(
                cliente.getId(),
                cliente.getDniCliente(),
                cliente.getTipoCuenta(),
                cliente.getTotal()
        );
    }

}
