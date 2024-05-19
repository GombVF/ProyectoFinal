package org.example.proyectofinal.service;

import org.example.proyectofinal.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClienteServiceImpTest {
    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    void addCliente() {
    }

    @Test
    void getClientes() {
    }

    @Test
    void getClienteByTipoPersonaRfc() {
        String rfc = "GOJL920915123";
        assertTrue(clienteRepository.existsClienteByPersonaFisica_Rfc(rfc));
        assertNull(clienteRepository.getClienteByPersonaMoral_Rfc(rfc));
        assertNotNull(clienteRepository.getClienteByPersonaFisica_Rfc(rfc));

    }

    @Test
    void updateCliente() {
    }

    @Test
    void updateClienteByTipoPersonaRfc() {
    }

    @Test
    void deleteCliente() {
    }

    @Test
    void deleteClienteByTipoPersonaRfc() {
    }

    @Test
    void convertToDto() {
    }

    @Test
    void convertToEntity() {
    }

    @Test
    void existsClienteByTipoPersonaRfc() {
    }
}