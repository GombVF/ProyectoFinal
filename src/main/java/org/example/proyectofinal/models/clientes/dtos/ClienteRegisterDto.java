package org.example.proyectofinal.models.clientes.dtos;

import lombok.Data;

@Data
public class ClienteRegisterDto {
    private String nombre;
    private String paterno;
    private String materno;
    private String razonSocial;
    private String rfc;
    private String tipoPersona;
}
