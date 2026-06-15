package com.bank.account_service.infrastructure.persistence.mapper;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.infrastructure.persistence.entity.ClienteEntity;
import com.bank.account_service.infrastructure.persistence.entity.CuentaBancariaEntity;

public class CuentaBancariaEntityMapper {

    public static CuentaBancaria toDomain(CuentaBancariaEntity entity) {
        return new CuentaBancaria(
                entity.getId(),
                entity.getCliente().getDni(),
                entity.getTipoCuenta(),
                entity.getTotal()
        );
    }

    public static CuentaBancariaEntity toEntity(CuentaBancaria cuenta, ClienteEntity cliente) {
        return new CuentaBancariaEntity(
                cuenta.getId(),
                cliente,
                cuenta.getTipoCuenta(),
                cuenta.getTotal()
        );
    }

}
