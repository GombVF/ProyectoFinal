package org.example.proyectofinal.service;

import org.example.proyectofinal.models.empleados.Empleado;

import java.util.List;

public interface EmpleadoService {
    Empleado addEmpleado(Empleado empleado); //create
    List<Empleado> getEmpleados(); //read
    Empleado getEmpleadoById(int id);
    Empleado getEmpleadoByTipoPersonaRfc(String rfc);
    void updateEmpleado(Empleado empleado); //update
    void updateEmpleadoById(int id, Empleado empleado);
    void updateEmpleadoByTipoPersonaRfc(String rfc, Empleado empleado);
    void deleteEmpleado(int id); // delete
    void deleteEmpleadoByTipoPersonaRfc(String rfc);
}
