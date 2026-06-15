package com.bank.account_service.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateSaldoCuentaDTO")
public record UpdateSaldoCuentaDTO(
        @Schema(example = "20000")
        Double total
) {}
