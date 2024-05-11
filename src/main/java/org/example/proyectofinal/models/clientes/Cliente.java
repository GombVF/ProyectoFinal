package org.example.proyectofinal.models.clientes;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.models.personasMorales.PersonaMoral;
import org.example.proyectofinal.models.roles.Rol;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "clientes")
//@Check(constraints = "((tipo_cliente = 'Moral' AND id_personas_morales IS NOT NULL AND id_personas_fisicas IS NULL) OR " +
//    "(tipo_cliente = 'Física' AND id_personas_fisicas IS NOT NULL AND id_personas_morales IS NULL))")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    // @Length todo realizar el length
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_persona")
    private TipoPersona tipoPersona;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id_personas_fisicas")
    private PersonaFisica personaFisica;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id_personas_morales")
    private PersonaMoral personaMoral;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_roles")
    @NotNull
    private Rol rol;

    public Cliente(String tipoPersona, PersonaFisica personaFisica) {
        this.tipoPersona = TipoPersona.valueOf(tipoPersona);
        this.personaFisica = personaFisica;
    }

    public Cliente(String tipoPersona, PersonaMoral personaMoral) {
        this.tipoPersona = TipoPersona.valueOf(tipoPersona);
        this.personaMoral = personaMoral;
    }

    // TODO: Validar que esta validacion funcione
    @AssertTrue(message = "El tipo de persona y la persona relacionada deben de coincidir")
    public boolean isTipoClienteValid() {
        return (personaMoral == null && tipoPersona == TipoPersona.MORAL) ||
            (personaFisica == null && tipoPersona == TipoPersona.FISICA);
    }
}
