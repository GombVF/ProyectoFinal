package org.example.proyectofinal.models.envios;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Date fecha;

    // TODO: Validar que esta validacion funcione
    @AssertTrue(message = "La cantidad del producto enviado debe de ser mayor a 0")
    public boolean isCantidadValid() {
        return (this.cantidad > 0);
    }
}
