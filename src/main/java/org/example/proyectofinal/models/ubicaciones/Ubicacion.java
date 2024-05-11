package org.example.proyectofinal.models.ubicaciones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "ubicaciones",  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigosPostales","calle", "exterior", "interior"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Length(min = 1, max = 50)
    @NotNull
    private String calle;
    @Length(min = 1, max = 50)
    @NotNull
    private String exterior;
    @Column(columnDefinition = "DEFAULT 'N/A'")
    @Length(min = 1, max = 50)
    private String interior;
    @Length(min = 1, max = 300)
    @NotNull
    private String referencias;
    @ManyToOne
    @JoinColumn(name = "id_codigos_postales")
    private CodigoPostal codigoPostal;

}
