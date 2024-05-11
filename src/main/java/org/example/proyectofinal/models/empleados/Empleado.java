package org.example.proyectofinal.models.empleados;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.areas.Area;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.util.enums.TipoEmpleado;

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
    private String password;
    @Column(name = "estatus", nullable = false)
    private boolean status;
    @Column(name = "tipo_empleado", nullable = false)
    private TipoEmpleado tipoEmpleado;
    @NotNull
    @ManyToOne
    private Empleado Jefe;
    @NotNull
    @OneToOne
    private Area area;
    @NotNull
    @OneToOne
    private PersonaFisica personaFisica;
}
