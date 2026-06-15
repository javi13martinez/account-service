package com.bank.account_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.account_service.application.usecase.CreateCuentaBancariaUseCase;
import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    public GetClienteByDniUseCase getClienteByDniUseCase(ClienteRepositoryPort port) {
        return new GetClienteByDniUseCase(port);
    }

    @Bean
    public CreateCuentaBancariaUseCase createCuentaBancariaUseCase(
            CuentaBancariaRepositoryPort cuentaBancariaRepositoryPort,
            ClienteRepositoryPort clienteRepositoryPort
    ) {
        return new CreateCuentaBancariaUseCase(cuentaBancariaRepositoryPort, clienteRepositoryPort);
    }

}
