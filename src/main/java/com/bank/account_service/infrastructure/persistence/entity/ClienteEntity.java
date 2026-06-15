package com.bank.account_service.infrastructure.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import com.bank.account_service.domain.model.CuentaBancaria;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    private String dni;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "cliente")
    List<CuentaBancariaEntity> cuentas;

    public ClienteEntity(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
    }

    public ClienteEntity(String dni) {
        this.dni = dni;
    }
}