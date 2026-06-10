package com.bank.account_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;

@Configuration
public class UseCaseConfig {
    @Bean
    public GetClienteByDniUseCase getClienteByDniUseCase(ClienteRepositoryPort port) {
        return new GetClienteByDniUseCase(port);
    }
}
