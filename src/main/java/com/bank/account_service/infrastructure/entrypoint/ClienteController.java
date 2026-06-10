package com.bank.account_service.infrastructure.entrypoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.shared.dto.ClienteDTO;
import com.bank.account_service.shared.mapper.ClienteMapper;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final GetClienteByDniUseCase useCase;

    public ClienteController(GetClienteByDniUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{dni}")
    public ClienteDTO getByDni(@PathVariable String dni) {
        Cliente cliente = useCase.execute(dni);
        return ClienteMapper.toDTO(cliente);
    }
}
