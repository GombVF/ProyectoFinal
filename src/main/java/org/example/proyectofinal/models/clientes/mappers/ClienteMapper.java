package org.example.proyectofinal.models.clientes.mappers;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.models.personasMorales.PersonaMoral;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Cliente convertToCliente(ClienteRegisterDto clienteRegisterDto) {
        Cliente cliente = new Cliente();
        if (clienteRegisterDto.getTipoPersona() == "Persona Física") {
            PersonaFisica clienteFisica = new PersonaFisica();
            clienteFisica.builder()
                .nombre(clienteRegisterDto.getNombre()).paterno(clienteRegisterDto.getPaterno()).materno(clienteRegisterDto.getMaterno())
                .rfc(clienteRegisterDto.getRfc()).build();
        }
        else if (clienteRegisterDto.getTipoPersona() == "Persona Moral"){
            PersonaMoral clienteMoral = new PersonaMoral();
            clienteMoral.builder().razonSocial(clienteRegisterDto.getRazonSocial()).rfc(clienteRegisterDto.getRfc());

        }



        return cliente;


    }


}
