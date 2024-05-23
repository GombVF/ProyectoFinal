package org.example.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.proyectofinal.models.areas.Area;

public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area findByNombreArea(String nombreArea);
}
