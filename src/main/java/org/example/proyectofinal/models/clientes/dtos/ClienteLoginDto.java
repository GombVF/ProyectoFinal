package org.example.proyectofinal.models.clientes.dtos;

import lombok.Builder;
import lombok.Data;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.ClienteServiceImp;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.example.proyectofinal.service.RolesEmpleadoServiceImp;

import java.util.Optional;

@Data
@Builder
public class ClienteLoginDto {
    private String rfc;
    private String password;

    public ClienteLoginDto() {
    }

    public ClienteLoginDto(String rfc, String password) {
        this.rfc = rfc;
        this.password = password;
    }
}
