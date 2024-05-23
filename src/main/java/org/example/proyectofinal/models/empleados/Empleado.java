package org.example.proyectofinal.models.empleados;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.areas.Area;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    // @Length todo realizar el length
    @Column(name = "contraseña")
    private String password;
    @Column(name = "estatus", nullable = false)
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "id_jefe")
    private Empleado Jefe;
    @NotNull
    @OneToOne
    @JoinColumn(name = "id_areas")
    private Area area;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personas_fisicas")
    private PersonaFisica personaFisica;

    public Empleado(PersonaFisica personaFisica, Area area, Boolean status, String password){
        this.personaFisica = personaFisica;
        this.area = area;
        this.status = status;
        this.password = password;
    }
}
