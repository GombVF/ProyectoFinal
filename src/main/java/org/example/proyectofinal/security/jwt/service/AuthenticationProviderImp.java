package org.example.proyectofinal.security.jwt.service;

import lombok.AllArgsConstructor;

import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.EmpleadoService;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationProviderImp implements AuthenticationProvider {
    //private final UserInfoRepository userInfoRepository;
    //private final RolesEmpleadoRepository rolesEmpleadoRepository;
    private final RolesEmpleadoService rolesEmpleadoService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Empleado empleado = rolesEmpleadoService.getEmpleadoByPersonaFisicaRfc(username);
        if (empleado == null) {
            Cliente userAdmin = Optional.ofNullable(clienteService.getClienteByTipoPersonaRfc(username))
                .orElseThrow(() -> new BadCredentialsException("User not found in database"));
            if (passwordEncoder.matches(pwd, userAdmin.getPassword())) {
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userAdmin.getRol().getRol()));
                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
        else{
            Empleado userAdmin = Optional.ofNullable(empleado)
                .orElseThrow(() -> new BadCredentialsException("User not found in database"));
            if (passwordEncoder.matches(pwd, userAdmin.getPassword())) {
                List<GrantedAuthority> authorities = rolesEmpleadoService.getRolEmpleado(userAdmin).stream().map(roleEmpleado ->
                    new SimpleGrantedAuthority(roleEmpleado.getRol().getRol())
                ).collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
