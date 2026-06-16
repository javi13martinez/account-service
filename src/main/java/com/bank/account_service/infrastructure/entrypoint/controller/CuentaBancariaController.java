package com.bank.account_service.infrastructure.entrypoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.application.usecase.CreateCuentaBancariaUseCase;
import com.bank.account_service.application.usecase.UpdateSaldoCuentaUseCase;
import com.bank.account_service.infrastructure.entrypoint.controller.GlobalExceptionHandler.ErrorResponse;
import com.bank.account_service.infrastructure.entrypoint.dto.CreateCuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.UpdateSaldoCuentaDTO;
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

    private CreateCuentaBancariaUseCase createCuentaBancariaUseCase;
    private UpdateSaldoCuentaUseCase updateSaldoCuentaUseCase;

    public CuentaBancariaController(
            CreateCuentaBancariaUseCase createCuentaBancariaUseCase,
            UpdateSaldoCuentaUseCase updateSaldoCuentaUseCase
    ) {
        this.createCuentaBancariaUseCase = createCuentaBancariaUseCase;
        this.updateSaldoCuentaUseCase = updateSaldoCuentaUseCase;
    }

    @Operation(
            summary = "POST CUENTA BANCARIA",
            description = "Alta de una cuenta bancaria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CUENTA BANCARIA CREATED",
                    content = @Content(schema = @Schema(implementation = CuentaBancariaDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "INVALID BODY DATA",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public CuentaBancariaDTO create(
            @RequestBody CreateCuentaBancariaDTO createDTO
    ) {
        return CuentaBancariaMapper.toDTO(createCuentaBancariaUseCase.execute(
                createDTO.dniCliente(),
                createDTO.tipoCuenta().name(),
                createDTO.total()
        ));
    }

    @Operation(
            summary = "PUT SALDO CUENTA BANCARIA",
            description = "Actualización de saldo de cuenta bancaria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CUENTA BANCARIA UPDATED",
                    content = @Content(schema = @Schema(implementation = CuentaBancariaDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CUENTA BANCARIA NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "INVALID BODY DATA",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{id}")
    public CuentaBancariaDTO updateSaldo(
            @PathVariable Long id,
            @RequestBody UpdateSaldoCuentaDTO updateDTO
    ) {
        return CuentaBancariaMapper.toDTO(updateSaldoCuentaUseCase.execute(id, updateDTO.total()));
    }

}
