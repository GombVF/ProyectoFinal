package org.example.proyectofinal.service;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cliente addCliente(Cliente cliente) {
        return null;
    }

    @Override
    public List<Cliente> getClientes() {
        return List.of();
    }

    @Override
    public Cliente getClienteByTipoPersonaRfc(String rfc) {
        return null;
    }

    @Override
    public void updateCliente(Cliente cliente) {

    }

    @Override
    public void updateClienteByTipoPersonaRfc(String rfc) {

    }

    @Override
    public void deleteCliente(Cliente cliente) {

    }

    @Override
    public void deleteClienteByTipoPersonaRfc(String rfc) {

    }

    public ClienteRegisterDto convertToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteRegisterDto.class);
    }

    public Cliente convertToEntity(ClienteRegisterDto clienteRegisterDto) {
        return modelMapper.map(clienteRegisterDto, Cliente.class);
    }


}
