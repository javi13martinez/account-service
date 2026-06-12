package com.bank.account_service.infrastructure.entrypoint.mapper;

import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;

public class CuentaBancariaMapper {
    public static CuentaBancariaDTO toDTO(CuentaBancaria cuentaBancaria) {
        return new CuentaBancariaDTO(
                cuentaBancaria.getDniCliente(),
                cuentaBancaria.getTipoCuenta(),
                cuentaBancaria.getTotal()
        );
    }
}
