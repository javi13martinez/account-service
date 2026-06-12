package com.bank.account_service.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "CuentaBancariaDTO")
public record CuentaBancariaDTO(
        @Schema(example = "11111111A")
        String dniCliente,

        @Schema(example = "PREMIUM")
        String tipoCuenta,

        @Schema(example = "10000")
        Double total
) {}
