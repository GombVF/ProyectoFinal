package org.example.proyectofinal.models.envios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.util.enums.EstatusEnvio;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class EnvioClaveDto {
    private String username;
    private Ubicacion ubicacion;
    private List<Producto> productos;
    private List<Integer> cantidades;
    private EstatusEnvio estatusEnvio;
    private Date fechaSolicitud;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvioClaveDto that = (EnvioClaveDto) o;
        return Objects.equals(username, that.username) && Objects.equals(ubicacion, that.ubicacion) && Objects.equals(productos, that.productos) && Objects.equals(cantidades, that.cantidades) && estatusEnvio == that.estatusEnvio && Objects.equals(fechaSolicitud, that.fechaSolicitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, ubicacion, productos, cantidades, estatusEnvio, fechaSolicitud);
    }
}
