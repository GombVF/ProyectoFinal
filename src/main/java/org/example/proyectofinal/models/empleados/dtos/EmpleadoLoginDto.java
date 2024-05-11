package org.example.proyectofinal.models.empleados.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpleadoLoginDto {
    private String rfc;
    private String password;
}
