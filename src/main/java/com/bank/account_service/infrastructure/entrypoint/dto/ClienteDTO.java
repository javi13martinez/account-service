package com.bank.account_service.infrastructure.entrypoint.dto;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ClienteDTO")
public record ClienteDTO(
        @Schema(example = "11111111A")
        String dni,

        @Schema(example = "Juan")
        String nombre,

        @Schema(example = "Pérez")
        String apellido1,

        @Schema(example = "López")
        String apellido2,

        @Schema(example = "1959-09-12")
        LocalDate fechaNacimiento,

        @Schema(description = "CuentaBancariaDTO")
        List<CuentaBancariaBaseDTO> cuentas
) {}
