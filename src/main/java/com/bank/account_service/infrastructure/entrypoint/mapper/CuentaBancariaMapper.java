package com.bank.account_service.infrastructure.entrypoint.mapper;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaBaseDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;

public class CuentaBancariaMapper {
    public static CuentaBancariaBaseDTO toBaseDTO(CuentaBancaria cuentaBancaria) {
        return new CuentaBancariaBaseDTO(
                cuentaBancaria.getId(),
                cuentaBancaria.getTipoCuenta(),
                cuentaBancaria.getTotal()
        );
    }

    public static CuentaBancariaDTO toDTO(CuentaBancaria cuentaBancaria) {
        return new CuentaBancariaDTO(
                cuentaBancaria.getId(),
                cuentaBancaria.getTipoCuenta(),
                cuentaBancaria.getTotal(),
                cuentaBancaria.getDniCliente()
        );
    }
}
