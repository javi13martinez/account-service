package com.bank.account_service.shared.mapper;

import com.bank.account_service.domain.model.Cliente;
import com.bank.account_service.shared.dto.ClienteDTO;

public class ClienteMapper {
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
