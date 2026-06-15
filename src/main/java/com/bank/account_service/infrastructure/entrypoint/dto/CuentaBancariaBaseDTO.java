package com.bank.account_service.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CuentaBancariaBaseDTO")
public record CuentaBancariaBaseDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "PREMIUM")
        String tipoCuenta,

        @Schema(example = "10000")
        Double total
) {}