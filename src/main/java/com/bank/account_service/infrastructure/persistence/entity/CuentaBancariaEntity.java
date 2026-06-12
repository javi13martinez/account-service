package com.bank.account_service.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUENTAS_BANCARIAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CuentaBancariaEntity(String dniCliente, String tipoCuenta, Double total) {
        this.dniCliente = dniCliente;
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }
}
