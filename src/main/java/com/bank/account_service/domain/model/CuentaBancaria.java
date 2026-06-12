package com.bank.account_service.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaBancaria {
    private final Long id;
    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CuentaBancaria(Long id, String dni, String tipoCuenta, Double total) {
        this.id = id;
        this.dniCliente = dni;
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }
}
