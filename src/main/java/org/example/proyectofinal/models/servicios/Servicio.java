package org.example.proyectofinal.models.servicios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.maquinas.Maquina;
import org.example.proyectofinal.models.tiposServicios.TipoServicio;

import java.util.Date;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_maquinas")
    private Maquina maquina;
    @ManyToOne
    @JoinColumn(name = "id_empleados")
    private Empleado empleado;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipos_servicios")
    private TipoServicio tipoServicio;

}
