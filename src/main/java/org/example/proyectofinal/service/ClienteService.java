package org.example.proyectofinal.service;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente addCliente(Cliente cliente); //Create
    List<Cliente> getClientes();  //Read
    Optional<Cliente> getClienteById(Integer id);
    Cliente getClienteByTipoPersonaRfc(String rfc);
    void updateCliente(Cliente cliente); //Update
    void updateClienteByTipoPersonaRfc(String rfc);
    void deleteCliente(Cliente cliente); //Delete
    void deleteClienteByTipoPersonaRfc(String rfc);
    void deleteClienteById(Integer id);
    ClienteRegisterDto convertToDto(Cliente cliente);
    Cliente convertToEntity(ClienteRegisterDto clienteRegisterDto);

    boolean existsClienteByTipoPersonaRfc(String rfc);
    boolean hasRegisteredAddress(String rfc);

}
