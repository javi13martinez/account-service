package com.bank.account_service;

import java.time.LocalDate;
import java.util.List;

import com.bank.account_service.infrastructure.entrypoint.dto.ClienteDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaBaseDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;
import com.bank.account_service.shared.TipoCuentaBancariaEnum;

public class TestData {
    public static List<ClienteDTO> initialClientes() {
        return List.of(
                new ClienteDTO("11111111A", "Juan", "Pérez", "López", LocalDate.of(1959,9,12), List.of(
                        new CuentaBancariaBaseDTO(1L, TipoCuentaBancariaEnum.PREMIUM.toString(), 150000.0),
                        new CuentaBancariaBaseDTO(2L, TipoCuentaBancariaEnum.NORMAL.toString(), 20000.0)
                )),
                new ClienteDTO("22222222B", "Raúl", "Canales", "Rodríguez", LocalDate.of(1985,3,1), List.of(
                        new CuentaBancariaBaseDTO(3L, TipoCuentaBancariaEnum.NORMAL.toString(), 50000.0),
                        new CuentaBancariaBaseDTO(4L, TipoCuentaBancariaEnum.JUNIOR.toString(), 300.0)
                )),
                new ClienteDTO("33333333C", "Elena", "Ruiz", "Herrera", LocalDate.of(2010,5,10), List.of(
                        new CuentaBancariaBaseDTO(5L, TipoCuentaBancariaEnum.JUNIOR.toString(), 300.0)
                )),
                new ClienteDTO("44444444D", "Raquel", "Ruiz", "Herrera", LocalDate.of(2002,6,21), List.of(
                        new CuentaBancariaBaseDTO(6L, TipoCuentaBancariaEnum.NORMAL.toString(), 75000.0)
                )),
                new ClienteDTO("55555555E", "María", "Sánchez", "Torres", LocalDate.of(1999,8,8), List.of(
                        new CuentaBancariaBaseDTO(7L, TipoCuentaBancariaEnum.PREMIUM.toString(), 120000.0)
                ))
        );
    }

    public static List<CuentaBancariaDTO> initialCuentas() {
        return List.of(
                new CuentaBancariaDTO(1L, TipoCuentaBancariaEnum.PREMIUM.toString(), 150000.0, "11111111A"),
                new CuentaBancariaDTO(2L, TipoCuentaBancariaEnum.NORMAL.toString(), 20000.0, "11111111A"),
                new CuentaBancariaDTO(3L, TipoCuentaBancariaEnum.NORMAL.toString(), 50000.0, "22222222B"),
                new CuentaBancariaDTO(4L, TipoCuentaBancariaEnum.JUNIOR.toString(), 300.0, "22222222B"),
                new CuentaBancariaDTO(5L, TipoCuentaBancariaEnum.JUNIOR.toString(), 300.0, "33333333C"),
                new CuentaBancariaDTO(6L, TipoCuentaBancariaEnum.NORMAL.toString(), 75000.0, "44444444D"),
                new CuentaBancariaDTO(7L, TipoCuentaBancariaEnum.PREMIUM.toString(), 120000.0, "55555555E")
        );
    }
}
