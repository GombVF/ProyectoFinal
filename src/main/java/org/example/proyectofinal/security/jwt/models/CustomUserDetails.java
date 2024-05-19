package org.example.proyectofinal.security.jwt.models;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.roles.Rol;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.ClienteServiceImp;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.example.proyectofinal.service.RolesEmpleadoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomUserDetails extends ClienteLoginDto implements UserDetails {

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(ClienteLoginDto byUsername, RolesEmpleadoService rolesEmpleadoService,
                             ClienteService clienteService) {
        super();
        this.username = byUsername.getRfc();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        if (rolesEmpleadoService.existsRolEmpleadoByEmpleadoPersonaFisicaRfc(username)) {
            Empleado empleado = rolesEmpleadoService.getEmpleadoByPersonaFisicaRfc(username);
            for(String role : rolesEmpleadoService.getRolesEmpleado(empleado)){
                auths.add(new SimpleGrantedAuthority(role.toUpperCase()));
            }
            this.authorities = auths;
        }
        else if (clienteService.existsClienteByTipoPersonaRfc(username)) {
            Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
            auths.add(new SimpleGrantedAuthority(cliente.getRol().getRol().toUpperCase()));
            this.authorities = auths;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}