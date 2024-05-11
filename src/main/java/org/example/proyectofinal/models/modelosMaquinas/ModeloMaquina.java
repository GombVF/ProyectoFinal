package org.example.proyectofinal.models.modelosMaquinas;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "modelos_maquinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModeloMaquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    @Length(min = 5, max = 30)
    private String modelo;
    @NotNull
    @Min(value = 1)
    private int capacidad;
}
