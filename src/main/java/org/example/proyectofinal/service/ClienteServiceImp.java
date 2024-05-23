package org.example.proyectofinal.service;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;
import org.example.proyectofinal.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente getClienteByTipoPersonaRfc(String rfc) {
        return clienteRepository.existsClienteByPersonaFisica_Rfc(rfc) ?
            clienteRepository.getClienteByPersonaFisica_Rfc(rfc) : clienteRepository.getClienteByPersonaMoral_Rfc(rfc);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        clienteRepository.save(cliente);

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

    @Override
    public void deleteClienteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    public ClienteRegisterDto convertToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteRegisterDto.class);
    }

    public Cliente convertToEntity(ClienteRegisterDto clienteRegisterDto) {
        return modelMapper.map(clienteRegisterDto, Cliente.class);
    }

    @Override
    public boolean existsClienteByTipoPersonaRfc(String rfc) {
        return (clienteRepository.existsClienteByPersonaFisica_Rfc(rfc)
            || clienteRepository.existsClienteByPersonaFisica_Rfc(rfc));
    }

    @Override
    public boolean hasRegisteredAddress(String rfc) {
        Cliente cliente = this.getClienteByTipoPersonaRfc(rfc);
        return cliente.getDomicilio() != null;
    }


}
