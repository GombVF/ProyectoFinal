package org.example.proyectofinal.models.codigosPostales;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.estados.Estado;
import org.example.proyectofinal.models.municipios.Municipio;
import org.hibernate.validator.constraints.Length;

import java.util.Map;

@Entity
@Table(name = "codigos_postales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodigoPostal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    @Length(min = 5, max = 5)
    private String codigoPostal;
    @Length(min = 1, max = 100)
    @NotNull
    private String colonia;
    @ManyToOne
    @JoinColumn(name = "id_estados")
    @NotNull
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "id_municipios")
    @NotNull
    private Municipio municipio;

}
