package org.example.proyectofinal.models.personasMorales;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "personas_morales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaMoral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "razon_social", nullable = false)
    @NotNull
    @Length(min = 2, max = 100)
    private String razonSocial;
    @Column(nullable = false, unique = true)
    @Length(min = 12, max = 12)
    private String rfc;

    public PersonaMoral(String razonSocial, String rfc) {
        this.razonSocial = razonSocial;
        this.rfc = rfc;
    }

}
