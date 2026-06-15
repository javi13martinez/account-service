package com.bank.account_service.infrastructure.persistence.mapper;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.infrastructure.persistence.entity.ClienteEntity;

public class ClienteEntityMapper {

    public static Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getDni(),
                entity.getNombre(),
                entity.getApellido1(),
                entity.getApellido2(),
                entity.getFechaNacimiento(),
                entity.getCuentas().stream().map(CuentaBancariaEntityMapper::toDomain).toList()
        );
    }

    public static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getFechaNacimiento()
        );
    }

}
