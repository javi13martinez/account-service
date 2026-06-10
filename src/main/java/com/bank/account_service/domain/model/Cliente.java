package com.bank.account_service.domain.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cliente {
    private final String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;

    public Cliente(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("DNI cannot be empty");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (apellido1 == null || apellido1.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be empty");
        }

        if (apellido2 == null || apellido2.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be empty");
        }

        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid fechaNacimiento");
        }

        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void changeNombreCompleto(String nombre, String apellido1, String apellido2) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Invalid nombre");
        }
        if (apellido1 == null || apellido1.isBlank()) {
            throw new IllegalArgumentException("Invalid apellido1");
        }
        if (apellido2 == null || apellido2.isBlank()) {
            throw new IllegalArgumentException("Invalid apellido2");
        }

        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public void changeFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid fechaNacimiento");
        }
        this.fechaNacimiento = fechaNacimiento;
    }
}
