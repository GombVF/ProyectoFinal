package org.example.proyectofinal.models.envios;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.util.conveters.EstatusEnvioConverter;
import org.example.proyectofinal.util.conveters.TipoPersonaConverter;
import org.example.proyectofinal.util.enums.EstatusEnvio;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "clave_producto", nullable = false, length = 100)
    private String claveProducto;
    @NotNull
    private int cantidad;
    @NotNull
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;
    @Column(name = "fecha_envio")
    private Date fechaEnvio;
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
    @Convert(converter = EstatusEnvioConverter.class)
    @Column(name = "estatus")
    private EstatusEnvio status;
    @ManyToOne
    @JoinColumn(name = "id_empleados")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "id_productos")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "id_ubicaciones")
    private Ubicacion ubicacion;

    public Envio(String clave, Integer cantidad, Date fechaSolicitud, EstatusEnvio estatusEnvio, Producto producto,
                 Ubicacion ubicacion){
        this.claveProducto = clave;
        this.cantidad = cantidad;
        this.fechaSolicitud = fechaSolicitud;
        this.status = estatusEnvio;
        this.producto = producto;
        this.ubicacion = ubicacion;
    }

    // TODO: Validar que esta validacion funcione
    @AssertTrue(message = "La cantidad del producto enviado debe de ser mayor a 0")
    public boolean isCantidadValid() {
        return (this.cantidad > 0);
    }
}
