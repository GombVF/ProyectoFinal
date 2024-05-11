package org.example.proyectofinal.models.tiposServicios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tipos_servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 50)
    private String servicio;
    @NotNull
    @Length(min = 1, max = 300)
    private String descripcion;
}
