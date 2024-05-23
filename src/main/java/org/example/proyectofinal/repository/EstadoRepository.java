package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.estados.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
