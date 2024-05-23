package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.empleados.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Empleado getEmpleadoByPersonaFisica_Rfc(String rfc);
}
