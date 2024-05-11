package org.example.proyectofinal.models.personasFisicas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "personas_fisicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Length(min = 3, max = 30)
    @NotNull
    private String nombre;
    @Length(min = 3, max = 20)
    @NotNull
    private String paterno;
    @Length(min = 3, max = 20)
    @Column(columnDefinition = "DEFAULT ''")
    private String materno;
    @Length(min = 13, max = 13)
    @Column(nullable = false, unique = true)
    private String rfc;

    public PersonaFisica(String nombre, String paterno, String materno, String rfc) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.rfc = rfc;
    }
}
