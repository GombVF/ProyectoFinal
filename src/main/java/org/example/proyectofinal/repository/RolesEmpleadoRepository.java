package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.roles.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesEmpleadoRepository extends CrudRepository<RolesEmpleado, Long> {
    boolean existsRolesEmpleadoByEmpleado_PersonaFisica_Rfc(String rfc);
    List<RolesEmpleado> findRolesEmpleadoByEmpleado(Empleado empleado);

    @Query("Select e " +
        "from RolesEmpleado re " +
        "inner join re.empleado e " +
        "inner join e.personaFisica pf " +
        "where pf.rfc = :rfc")
    Empleado getEmpleadoByRfc(String rfc);


    @Query("Select re " +
        "from RolesEmpleado re " +
        "left join fetch re.empleado e " +
        "where re.empleado = :empleado")
    List<RolesEmpleado> getAllByEmpleado(Empleado empleado);


}
