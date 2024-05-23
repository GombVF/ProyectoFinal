package org.example.proyectofinal.models.ubicaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionClienteDto {
    private String calle;
    private String exterior;
    private String interior;
    private String referencia;
    private String codigoPostal;
    private String colonia;
    private String municipio;
    private String estado;
}
