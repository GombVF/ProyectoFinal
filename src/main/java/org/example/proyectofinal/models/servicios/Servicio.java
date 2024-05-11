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
    @NotNull
    private Date fecha;
    @NotNull
    @ManyToOne
    private Maquina maquina;
    @NotNull
    @ManyToOne
    private Empleado empleado;
    @NotNull
    @ManyToOne
    private TipoServicio tipoServicio;

}
