package org.example.proyectofinal.models.maquinas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.modelosMaquinas.ModeloMaquina;

@Entity
@Table(name = "maquinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo_maquina",nullable = false)
    private boolean tipoMaquina;
    @ManyToOne
    @JoinColumn(name = "id_clientes", nullable = false)
    private Cliente cliente;
    @OneToOne
    @NotNull
    @JoinColumn(name = "id_modelo_maquinas")
    private ModeloMaquina modeloMaquina;

    public Maquina(boolean tipoMaquina,ModeloMaquina mm){
        this.tipoMaquina = tipoMaquina;
        this.modeloMaquina = mm;
    }
}
