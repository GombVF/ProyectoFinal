package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.modelosMaquinas.ModeloMaquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloMaquinaRepository extends JpaRepository<ModeloMaquina, Integer> {
    ModeloMaquina getModeloMaquinaByModelo(String modelo);
}
