package com.bank.account_service.infrastructure.entrypoint.mapper;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.infrastructure.entrypoint.dto.ClienteDTO;

public class ClienteDtoMapper {
    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getFechaNacimiento()
        );
    }
}
