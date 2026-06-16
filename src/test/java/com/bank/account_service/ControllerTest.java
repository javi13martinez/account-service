package com.bank.account_service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bank.account_service.infrastructure.entrypoint.dto.ClienteDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CreateCuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaBaseDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.CuentaBancariaDTO;
import com.bank.account_service.infrastructure.entrypoint.dto.UpdateSaldoCuentaDTO;
import com.bank.account_service.shared.TipoCuentaBancariaEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetClientes() throws Exception {
        MvcResult result = mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andReturn();

        List<ClienteDTO> clientesDTO = this.responseToList(result, new TypeReference<>() {});

        assertThat(clientesDTO)
                .extracting(ClienteDTO::dni)
                .containsExactlyInAnyOrderElementsOf(TestData.initialClientes().stream().map(ClienteDTO::dni).toList());
    }

    @Test
    void shouldGetClientesAdultos() throws Exception {
        MvcResult result = mockMvc.perform(get("/clientes/mayores-de-edad"))
                .andExpect(status().isOk())
                .andReturn();

        List<ClienteDTO> clientesDTO = this.responseToList(result, new TypeReference<>() {});

        List<ClienteDTO> clientes = TestData.initialClientes().stream().filter(cliente ->
                Period.between(cliente.fechaNacimiento(), LocalDate.now()).getYears() >= 18
        ).toList();

        assertThat(clientesDTO)
                .extracting(ClienteDTO::dni)
                .containsExactlyInAnyOrderElementsOf(clientes.stream().map(ClienteDTO::dni).toList());
    }

    @Test
    void shouldGetClientesConSaldoMayorA() throws Exception {
        double minimumSaldo = 100000.0;
        MvcResult result = mockMvc.perform(get("/clientes/con-cuenta-superior-a/{cantidad}", minimumSaldo))
                .andExpect(status().isOk())
                .andReturn();

        List<ClienteDTO> clientesDTO = this.responseToList(result, new TypeReference<>() {});

        List<ClienteDTO> clientes = TestData.initialClientes().stream().filter(c ->
                c.cuentas().stream().mapToDouble(CuentaBancariaBaseDTO::total).sum() > minimumSaldo).toList();

        assertThat(clientesDTO)
                .extracting(ClienteDTO::dni)
                .containsExactlyInAnyOrderElementsOf(clientes.stream().map(ClienteDTO::dni).toList());
    }

    @Test
    void shouldCreateCuentaClienteExistente() throws Exception {
        CreateCuentaBancariaDTO cuentaExistingCliente = new CreateCuentaBancariaDTO("11111111A", TipoCuentaBancariaEnum.PREMIUM, 1000.0);

        CuentaBancariaDTO cuentaExistingClienteDTO = this.createCuenta(cuentaExistingCliente);

        assertThat(cuentaExistingClienteDTO)
                .extracting("dniCliente", "tipoCuenta", "total")
                .contains(cuentaExistingCliente.dniCliente(), cuentaExistingCliente.tipoCuenta().name(), cuentaExistingCliente.total());
    }

    @Test
    void shouldCreateCuentaNewCliente() throws Exception {
        CreateCuentaBancariaDTO cuentaNewCliente = new CreateCuentaBancariaDTO("66666666F", TipoCuentaBancariaEnum.PREMIUM, 1000000.0);

        CuentaBancariaDTO cuentaNewClienteDTO = this.createCuenta(cuentaNewCliente);

        assertThat(cuentaNewClienteDTO)
                .extracting("id", "dniCliente", "tipoCuenta", "total")
                .contains(cuentaNewCliente.dniCliente(), cuentaNewCliente.tipoCuenta().name(), cuentaNewCliente.total());

        ClienteDTO clienteDTO = this.getCliente(cuentaNewCliente.dniCliente());

        assertThat(clienteDTO.dni()).isEqualTo(cuentaNewCliente.dniCliente());
        assertThat(clienteDTO.cuentas()).extracting(CuentaBancariaBaseDTO::id).containsExactlyInAnyOrder(cuentaNewClienteDTO.id());
    }

    CuentaBancariaDTO createCuenta(CreateCuentaBancariaDTO dto) throws Exception {
        MvcResult result = mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        return this.responseToObject(result, CuentaBancariaDTO.class);
    }

    @Test
    void shouldUpdateCuenta() throws Exception {
        CuentaBancariaDTO cuenta = TestData.initialCuentas().getFirst();

        UpdateSaldoCuentaDTO updateDTO = new UpdateSaldoCuentaDTO(1000000.0);

        MvcResult result = mockMvc.perform(put("/cuentas/{id}", cuenta.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(this.responseToObject(result, CuentaBancariaDTO.class).id()).isEqualTo(cuenta.id());
    }

    @Test
    void shouldGetClienteByDni() throws Exception {
        ClienteDTO cliente = TestData.initialClientes().getFirst();

        ClienteDTO clienteDTO = this.getCliente(cliente.dni());

        assertThat(clienteDTO.dni()).isEqualTo(cliente.dni());
    }

    ClienteDTO getCliente(String dni) throws Exception {
        MvcResult result = mockMvc.perform(get("/clientes/{dni}", dni))
                .andExpect(status().isOk())
                .andReturn();

        return this.responseToObject(result, ClienteDTO.class);
    }

    private <T> T responseToObject(MvcResult result, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                result.getResponse().getContentAsByteArray(),
                clazz
        );
    }

    private <T> List<T> responseToList(MvcResult result, TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(
                result.getResponse().getContentAsByteArray(),
                typeReference
        );
    }
}
