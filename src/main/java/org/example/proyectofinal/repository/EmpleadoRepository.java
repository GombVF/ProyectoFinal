package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.empleados.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
}
