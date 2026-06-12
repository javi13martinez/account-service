package com.bank.account_service.shared.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cliente DTO")
public class ClienteDTO {

    @Schema(example = "11111111A")
    public String dni;

    @Schema(example = "Juan")
    public String nombre;

    @Schema(example = "Pérez")
    public String apellido1;

    @Schema(example = "López")
    public String apellido2;

    @Schema(example = "1959-09-12")
    public LocalDate fechaNacimiento;
}
