package org.example.proyectofinal.models.productos;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "productos", uniqueConstraints = {@UniqueConstraint(columnNames = {"producto", "cantidad", "unidad"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Length(min = 1, max = 50)
    private String producto;
    @NotNull
    private float cantidad;
    @NotNull
    @Length(min = 1, max = 20)
    private String unidad;
    @NotNull
    private boolean sellos;
    @NotNull
    @Min(0)
    @Column(name = "cantidad_sellos")
    private int cantidadSellos;
    @NotNull
    private Double precio;

    // TODO: Validar que esta validacion funcione
    @AssertTrue(message = "La cantidad debe de ser diferente de 0.0")
    public boolean isCantidadValid() {
        return (cantidad > 0);
    }

    // TODO: Validar que esta validacion funcione
    @AssertTrue(message = "Si el producto no tiene sellos entonces la cantidad de sellos debe de ser 0")
    public boolean isSellosAndCantidadSellosValid() {
        return (this.sellos && this.cantidadSellos > 0) || (!this.sellos && this.cantidadSellos == 0);
    }

}
