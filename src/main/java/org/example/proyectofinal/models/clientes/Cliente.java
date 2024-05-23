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
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.util.conveters.TipoPersonaConverter;
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
    @Column(name = "contraseña")
    private String password;
    @Convert(converter = TipoPersonaConverter.class)
    @Column(name = "tipo_persona")
    private TipoPersona tipoPersona;
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personas_fisicas")
    private PersonaFisica personaFisica;
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personas_morales")
    private PersonaMoral personaMoral;
    @OneToOne
    @JoinColumn(name = "id_roles")
    @NotNull
    private Rol rol;
    @OneToOne
    @JoinColumn(name = "id_domicilio")
    Ubicacion domicilio;

    public Cliente(String tipoPersona, PersonaFisica personaFisica, String password, Rol rol) {
        this.tipoPersona = TipoPersona.fromString(tipoPersona);
        this.personaFisica = personaFisica;
        this.password = password;
        this.rol = rol;
    }

    public Cliente(String tipoPersona, PersonaMoral personaMoral,String password, Rol rol) {
        this.tipoPersona = TipoPersona.fromString(tipoPersona);
        this.personaMoral = personaMoral;
        this.password = password;
        this.rol = rol;
    }

}
