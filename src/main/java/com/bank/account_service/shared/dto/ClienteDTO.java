package com.bank.account_service.shared.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    public String dni;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public LocalDate fechaNacimiento;
}
