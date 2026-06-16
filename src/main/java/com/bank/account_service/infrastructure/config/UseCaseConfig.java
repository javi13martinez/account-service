package com.bank.account_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.account_service.application.usecase.CreateCuentaBancariaUseCase;
import com.bank.account_service.application.usecase.GetClienteByDniUseCase;
import com.bank.account_service.application.usecase.GetClientesAdultosUseCase;
import com.bank.account_service.application.usecase.GetClientesConSaldoMayorAUseCase;
import com.bank.account_service.application.usecase.GetClientesUseCase;
import com.bank.account_service.application.usecase.UpdateSaldoCuentaUseCase;
import com.bank.account_service.domain.port.out.ClienteRepositoryPort;
import com.bank.account_service.domain.port.out.CuentaBancariaRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    public GetClienteByDniUseCase getClienteByDniUseCase(ClienteRepositoryPort port) {
        return new GetClienteByDniUseCase(port);
    }

    @Bean
    public GetClientesUseCase getClientesUseCase(ClienteRepositoryPort port) {
        return new GetClientesUseCase(port);
    }

    @Bean
    public GetClientesAdultosUseCase getClientesAdultosUseCase(ClienteRepositoryPort port) {
        return new GetClientesAdultosUseCase(port);
    }

    @Bean
    public GetClientesConSaldoMayorAUseCase getClientesConSaldoMayorAUseCase(ClienteRepositoryPort port) {
        return new GetClientesConSaldoMayorAUseCase(port);
    }

    @Bean
    public CreateCuentaBancariaUseCase createCuentaBancariaUseCase(CuentaBancariaRepositoryPort cuentaBancariaRepositoryPort) {
        return new CreateCuentaBancariaUseCase(cuentaBancariaRepositoryPort);
    }

    @Bean
    public UpdateSaldoCuentaUseCase updateSaldoCuentaUseCase(
            CuentaBancariaRepositoryPort cuentaBancariaRepositoryPort
    ) {
        return new UpdateSaldoCuentaUseCase(cuentaBancariaRepositoryPort);
    }

}
