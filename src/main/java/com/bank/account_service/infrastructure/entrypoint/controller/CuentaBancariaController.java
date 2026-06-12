package com.bank.account_service.infrastructure.entrypoint.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.application.usecase.CreateCuentaBancariaUseCase;
import com.bank.account_service.domain.model.CuentaBancaria;
import com.bank.account_service.infrastructure.entrypoint.controller.GlobalExceptionHandler.ErrorResponse;
import com.bank.account_service.infrastructure.entrypoint.dto.ClienteDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CreateCuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.mapper.CuentaBancariaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cuentas")
@Tag(name = "CuentasBancarias", description = "Gestión de cuentas bancarias")
public class CuentaBancariaController {

    private final CreateCuentaBancariaUseCase useCase;

    public CuentaBancariaController(CreateCuentaBancariaUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "POST CUENTA BANCARIA",
            description = "Alta de una cuenta bancaria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CUENTA BANCARIA CREATED",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "INVALID CUENTA BANCARIA DATA",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public CuentaBancariaDTO create(
            @RequestBody CreateCuentaBancariaDTO createDTO
    ) {
        CuentaBancaria cuentaBancaria = useCase.execute(
                createDTO.dniCliente(),
                createDTO.tipoCuenta().name(),
                createDTO.total()
        );
        return CuentaBancariaMapper.toDTO(cuentaBancaria);
    }

}
