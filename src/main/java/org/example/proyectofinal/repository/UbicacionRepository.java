package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
}
