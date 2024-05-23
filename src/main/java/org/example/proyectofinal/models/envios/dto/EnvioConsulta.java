package org.example.proyectofinal.models.envios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.util.enums.EstatusEnvio;

import java.util.Date;

@Data
@AllArgsConstructor
public class EnvioConsulta {
    private String claveProducto;
    private Ubicacion ubicacion;
    private Empleado empleado;
    private Date fechaSolicitud;
    private Date fechaEnvio;
    private Date fechaEntrega;
    private EstatusEnvio status;
}
