package org.example.proyectofinal.models.RolesEmpleados;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.roles.Rol;

@Entity
@Table(name = "roles_empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empleados")
    @NotNull
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "id_roles")
    @NotNull
    private Rol rol;
}
