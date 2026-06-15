package com.bank.account_service.infrastructure.entrypoint.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.application.usecase.GetClientesAdultosUseCase;
import com.bank.account_service.application.usecase.GetClientesUseCase;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.infrastructure.entrypoint.controller.GlobalExceptionHandler.ErrorResponse;
import com.bank.account_service.infrastructure.entrypoint.dto.ClienteDTO;
import com.bank.account_service.infrastructure.entrypoint.mapper.ClienteDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {
    private final GetClienteByDniUseCase getClienteByDniUseCase;
    private final GetClientesUseCase getClientesUseCase;
    private final GetClientesAdultosUseCase getClientesAdultosUseCase;

    public ClienteController(
            GetClienteByDniUseCase getClienteByDniUseCase,
            GetClientesUseCase getClientesUseCase,
            GetClientesAdultosUseCase getClientesAdultosUseCase
    ) {
        this.getClientesUseCase = getClientesUseCase;
        this.getClienteByDniUseCase = getClienteByDniUseCase;
        this.getClientesAdultosUseCase = getClientesAdultosUseCase;
    }

    @Operation(
            summary = "GET CLIENTE BY DNI",
            description = "Devuelve un cliente identificado por su DNI"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CLIENTE FOUND",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CLIENTE NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "INVALID DNI",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{dni}")
    public ClienteDTO getByDni(
            @Parameter(
                description = "DNI",
                example = "11111111A"
            )
            @PathVariable String dni) {
        Cliente cliente = getClienteByDniUseCase.execute(dni);
        return ClienteDtoMapper.toDTO(cliente);
    }

    @Operation(
            summary = "GET CLIENTES",
            description = "Devuelve lista de los clientes activos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CLIENTES FOUND",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            )
    })
    @GetMapping
    public List<ClienteDTO> getClientes() {
        return getClientesUseCase.execute().stream().map(ClienteDtoMapper::toDTO).toList();
    }

    @Operation(
            summary = "GET CLIENTES",
            description = "Devuelve lista de los clientes mayores de edad"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CLIENTES FOUND",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            )
    })
    @GetMapping("/mayores-de-edad")
    public List<ClienteDTO> getClientesMayoresDeEdad() {
        return getClientesAdultosUseCase.execute().stream().map(ClienteDtoMapper::toDTO).toList();
    }
}
