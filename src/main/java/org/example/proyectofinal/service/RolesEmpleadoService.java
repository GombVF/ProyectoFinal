package org.example.proyectofinal.service;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.roles.Rol;

import java.util.List;

public interface RolesEmpleadoService {
    RolesEmpleado addRolEmpleado(Empleado empleado, Rol rol); //create
    List<RolesEmpleado> getRolesEmpleados(); //read
    List<RolesEmpleado> getRolEmpleado(Empleado empleado);
    List<RolesEmpleado> getRolEmpleadoByEmpleadoId(int id);
    Empleado getEmpleadoByPersonaFisicaRfc(String fisicaRfc);
    void updateRolEmpleado(RolesEmpleado rolEmpleado); //update
    void updateRolEmpleadoById(RolesEmpleado producto, int id);
    void updateRolEmpleadoByEmpleadoId(RolesEmpleado producto, int id);
    void deleteRolEmpleadoById(int id); //delete

    boolean existsRolEmpleadoByEmpleadoPersonaFisicaRfc(String fisicaRfc);
}
