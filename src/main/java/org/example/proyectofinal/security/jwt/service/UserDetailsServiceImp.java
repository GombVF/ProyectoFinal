package org.example.proyectofinal.security.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.repository.RolesEmpleadoRepository;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.ClienteServiceImp;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.example.proyectofinal.service.RolesEmpleadoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserDetailsServiceImp implements UserDetailsService {
    private final RolesEmpleadoRepository rolesEmpleadoRepository;


    @Autowired
    public UserDetailsServiceImp(RolesEmpleadoRepository rolesEmpleadoRepository) {
        this.rolesEmpleadoRepository = rolesEmpleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Security - UserDetailsServiceImpl.loadUserByUsername {}", username);
        RolesEmpleadoService rolesEmpleadoService = new RolesEmpleadoServiceImp();
        Empleado empleado = rolesEmpleadoService.getEmpleadoByPersonaFisicaRfc(username);
        String userName;
        String password;
        List<GrantedAuthority> authorities;
        if (empleado != null){
            Empleado userInfo = Optional.ofNullable(empleado)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
            userName = userInfo.getPersonaFisica().getRfc();
            password = userInfo.getPassword();
            authorities = rolesEmpleadoService.getRolEmpleado(userInfo).stream().map(roleEmpleado ->
                new SimpleGrantedAuthority(roleEmpleado.getRol().getRol())
            ).collect(Collectors.toList());
        }
        else {
            ClienteService clienteService = new ClienteServiceImp();
            Cliente userInfo = Optional.ofNullable(clienteService.getClienteByTipoPersonaRfc(username))
                .orElseThrow(() -> new BadCredentialsException("User not found in database"));
            if (userInfo.getPersonaFisica().getRfc() != null){
                userName = userInfo.getPersonaFisica().getRfc();
            }
            else {
                userName = userInfo.getPersonaMoral().getRfc();
            }
            password = userInfo.getPassword();
            authorities = List.of(new SimpleGrantedAuthority(userInfo.getRol().getRol()));
        }
        return new User(userName, password, authorities);
    }
}
