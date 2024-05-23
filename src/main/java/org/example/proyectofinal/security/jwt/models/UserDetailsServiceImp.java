package org.example.proyectofinal.security.jwt.models;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsServiceImp implements UserDetailsService {

    private ClienteService clienteService;
    private RolesEmpleadoService rolesEmpleadoService;

    @Autowired
    public UserDetailsServiceImp(ClienteService clienteService, RolesEmpleadoService rolesEmpleadoService) {
        this.clienteService = clienteService;
        this.rolesEmpleadoService = rolesEmpleadoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Entering in loadUserByUsername Method...");
        Optional<RolesEmpleado> rolesEmpleado = rolesEmpleadoService.getEmpleadoByPersonaFisicaRfc(username);

        if(rolesEmpleado.isPresent()){
            Empleado empleado = rolesEmpleado.get().getEmpleado();
            logger.info("User Authenticated Successfully..!!!");
            ClienteLoginDto user = new ClienteLoginDto(empleado.getPersonaFisica().getRfc(),empleado.getPassword());
            return new CustomUserDetails(user, rolesEmpleadoService, clienteService);

        }
        else if (clienteService.existsClienteByTipoPersonaRfc(username)) {
            Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
            logger.info("User Authenticated Successfully..!!!");
            ClienteLoginDto user = null;
            if(cliente.getPersonaFisica() != null){
                user = new ClienteLoginDto(cliente.getPersonaFisica().getRfc(),cliente.getPassword());
            }
            else if(cliente.getPersonaMoral() != null){
                user = new ClienteLoginDto(cliente.getPersonaMoral().getRfc(),cliente.getPassword());
            }
            else{
                logger.error("Username not found: " + username);
                throw new UsernameNotFoundException("could not found user..!!");
            }
            return new CustomUserDetails(user, rolesEmpleadoService, clienteService);
        }
        logger.error("Username not found: " + username);
        throw new UsernameNotFoundException("could not found user..!!");

    }
}
