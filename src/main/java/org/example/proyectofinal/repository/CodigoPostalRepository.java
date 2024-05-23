package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Long> {
}
