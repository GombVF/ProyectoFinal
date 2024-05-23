package org.example.proyectofinal.models.envios.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.util.conveters.EstatusEnvioConverter;
import org.example.proyectofinal.util.enums.EstatusEnvio;

import java.util.Date;

@Data
@AllArgsConstructor
public class EnvioCarritoDto {
    private int cantidad;
    private Producto producto;
}
