package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.productos.Producto;
import org.springframework.data.repository.CrudRepository;

public interface RolesEmpleadoRepository extends CrudRepository<RolesEmpleado, Long> {
    boolean existsByEmpleado_PersonaFisica_Rfc(String rfc);
}
