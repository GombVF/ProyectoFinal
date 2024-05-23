package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.empleados.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesEmpleadoRepository extends JpaRepository<RolesEmpleado, Long> {
    boolean existsRolesEmpleadoByEmpleado_PersonaFisica_Rfc(String rfc);
    List<RolesEmpleado> findRolesEmpleadoByEmpleado(Empleado empleado);
    RolesEmpleado findRolesEmpleadoByEmpleado_PersonaFisica_Rfc(String rfc);

    //@Query("SELECT re " +
    //    "FROM RolesEmpleado re " +
    //    "INNER JOIN fetch re.empleado e " +
    //    "INNER JOIN fetch e.personaFisica pf " +
    //    "WHERE pf.rfc = :rfc")
    //RolesEmpleado getEmpleadoByRfc(@Param("rfc")String rfc);
    @Query(value = "select re.* " +
        "from roles_empleados re " +
        "inner join empleados e " +
        "on re.id_empleados = e.id " +
        "inner join personas_fisicas pf " +
        "on e.id_personas_fisicas = pf.id " +
        "where pf.rfc =:rfc", nativeQuery = true)
    Optional<RolesEmpleado> getRolesEmpleadoByEmpleadoPersonaFisicaRfc(@Param("rfc") String rfc);


    @Query(value = "select * " +
        "from roles_empleados re " +
        "where id_empleados =:id", nativeQuery = true)
    List<RolesEmpleado> getAllByEmpleado(@Param("id")int id);


}
