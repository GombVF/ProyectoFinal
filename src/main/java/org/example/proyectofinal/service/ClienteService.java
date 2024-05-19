package org.example.proyectofinal.service;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;

import java.util.List;

public interface ClienteService {
    Cliente addCliente(Cliente cliente); //Create
    List<Cliente> getClientes();  //Read
    Cliente getClienteByTipoPersonaRfc(String rfc);
    void updateCliente(Cliente cliente); //Update
    void updateClienteByTipoPersonaRfc(String rfc);
    void deleteCliente(Cliente cliente); //Delete
    void deleteClienteByTipoPersonaRfc(String rfc);
    public ClienteRegisterDto convertToDto(Cliente cliente);
    public Cliente convertToEntity(ClienteRegisterDto clienteRegisterDto);

    public boolean existsClienteByTipoPersonaRfc(String rfc);
}
