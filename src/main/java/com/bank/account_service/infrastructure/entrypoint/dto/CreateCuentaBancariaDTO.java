package com.bank.account_service.infrastructure.entrypoint.dto;

import com.bank.account_service.shared.TipoCuentaBancariaEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CreateCuentaBancariaDTO")
public record CreateCuentaBancariaDTO(
        @Schema(example = "11111111A")
        String dniCliente,

        @Schema(example = "NORMAL")
        TipoCuentaBancariaEnum tipoCuenta,

        @Schema(example = "20000")
        Double total
) {}
