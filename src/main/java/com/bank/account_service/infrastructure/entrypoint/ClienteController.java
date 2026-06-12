package com.bank.account_service.infrastructure.entrypoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.shared.dto.ClienteDTO;
import com.bank.account_service.shared.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {
    private final GetClienteByDniUseCase useCase;

    public ClienteController(GetClienteByDniUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Get cliente by DNI",
            description = "Devuelve un cliente identificado por su DNI"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid DNI"
            )
    })
    @GetMapping("/{dni}")
    public ClienteDTO getByDni(
            @Parameter(
                description = "Cliente DNI",
                example = "11111111A"
            )
            @PathVariable String dni) {
        Cliente cliente = useCase.execute(dni);
        return ClienteMapper.toDTO(cliente);
    }
}
