package com.bank.account_service.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CuentaBancariaDTO")
public record CuentaBancariaDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "PREMIUM")
        String tipoCuenta,

        @Schema(example = "10000")
        Double total,

        @Schema(example = "11111111A")
        String dniCliente
) {}
