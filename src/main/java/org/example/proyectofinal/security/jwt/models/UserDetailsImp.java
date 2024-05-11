package org.example.proyectofinal.security.jwt.models;

//import edu.unam.springsecurity.auth.model.UserInfo; * Modelo de usuario, en mi caso Cliente y Empleado
//import edu.unam.springsecurity.auth.model.UserInfoRole; * Modelo de roles, en mi caso Rol

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.service.RolesEmpleadoServiceImp;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    //private UserInfo userInfo;
    private Cliente cliente; // no puede ser empleado m to m
    private Empleado empleado; // no puede ser cliente 1 to 1 ¿va?
    private TipoPersona tipoPersona;



    public UserDetailsImp(Cliente userInfo) {
        this.cliente = userInfo;
    }
    public UserDetailsImp(Empleado userInfo) {
        this.empleado = userInfo;
    }
    public UserDetailsImp(Long id, String name, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
        this.tipoPersona = null;
    }
    public UserDetailsImp(Long id, String name, String email, Collection<? extends GrantedAuthority> authorities,
                          String tipoPersona) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
        this.tipoPersona = TipoPersona.valueOf(tipoPersona);
    }

    public static UserDetailsImp build(Empleado user) {
        RolesEmpleadoServiceImp rolesEmpleadosService = new RolesEmpleadoServiceImp();
        List<GrantedAuthority> authorities = rolesEmpleadosService.getRolEmpleado(user).stream().map(roleEmpleado ->
            new SimpleGrantedAuthority(roleEmpleado.getRol().getRol())
        ).collect(Collectors.toList());
        String name =
            user.getPersonaFisica().getNombre() + ' ' + user.getPersonaFisica().getPaterno() + ' '
                + user.getPersonaFisica().getMaterno();
        return new UserDetailsImp(
            (long) user.getId(),
            name,
            user.getPersonaFisica().getRfc(),
            authorities
        );
    }

    public static UserDetailsImp build(Cliente user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getRol());
        authoritiesList.add(authority);
        String name = "";
        String rfc = "";
        String tipoPersona = "";
        if (user.getPersonaFisica() != null) {
            name = user.getPersonaFisica().getNombre() + ' ' + user.getPersonaFisica().getPaterno() + ' '
                    + user.getPersonaFisica().getMaterno();
            rfc = user.getPersonaFisica().getRfc();
            tipoPersona = "Física";
        } else if (user.getPersonaMoral() != null) {
            name = user.getPersonaMoral().getRazonSocial();
            rfc = user.getPersonaMoral().getRfc();
            tipoPersona = "Moral";
        }
        return new UserDetailsImp(
            (long) user.getId(),
            name,
            rfc,
            authoritiesList,
            tipoPersona
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        RolesEmpleadoServiceImp rolesEmpleadosService = new RolesEmpleadoServiceImp();
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        if (this.tipoPersona == null) {
            if (rolesEmpleadosService.getRolEmpleado(this.empleado) == null) {
                return Collections.emptySet();
            }
            for (RolesEmpleado role : rolesEmpleadosService.getRolEmpleado(this.empleado)) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRol().getRol()));
            }
        }
        else if (this.tipoPersona != null){
            if (cliente.getRol() == null) {
                return Collections.emptySet();
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(cliente.getRol().getRol()));
        }
        return grantedAuthorities;
    }

    /**
     * getUsername
     * @return username
     */
    @Override
    public String getUsername() {
        return this.name;
    }

    /**
     * getPassword (OTP)
     * @return password
     */
    @Override
    public String getPassword() {
        if (this.tipoPersona == null) {
            return empleado.getPassword();
        }
        else if (this.tipoPersona != null){
            return cliente.getPassword();
        }
        return "";
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getEmail
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * isEnabled
     * @return if user is enabled
     */
    @Override
    public boolean isEnabled() {
        // yo no tengo la parte de habilitado o no
        return true;
    }

    /**
     * isAccountNonLocked
     * @return if user is locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isAccountNonExpired
     * @return if account is not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     * @return if credential is not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
