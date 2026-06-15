package com.bank.account_service.domain.model;

import java.time.LocalDate;
import java.util.List;

import com.bank.account_service.infrastructure.persistence.entity.CuentaBancariaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Cliente {
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;
    private List<CuentaBancaria> cuentas;

    public Cliente(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
    }
}
