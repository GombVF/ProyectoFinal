package org.example.proyectofinal.models.maquinas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaRegistroDto {
    private boolean tipoMaquina;
    private String modelo;
    private int capacidad;
}
