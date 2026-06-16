package com.bank.account_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CuentaBancaria {
    private Long id;
    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CuentaBancaria(String tipoCuenta, Double total) {
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }

    public CuentaBancaria(String dniCliente, String tipoCuenta, Double total) {
        this.dniCliente = dniCliente;
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }
}
