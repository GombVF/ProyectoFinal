package org.example.proyectofinal.service;

import org.example.proyectofinal.models.empleados.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImp implements EmpleadoService{
    @Override
    public Empleado addEmpleado(Empleado empleado) {
        return null;
    }

    @Override
    public List<Empleado> getEmpleados() {
        return List.of();
    }

    @Override
    public Empleado getEmpleadoById(int id) {
        return null;
    }

    @Override
    public Empleado getEmpleadoByTipoPersonaRfc(String rfc) {
        return null;
    }

    @Override
    public void updateEmpleado(Empleado empleado) {

    }

    @Override
    public void updateEmpleadoById(int id, Empleado empleado) {

    }

    @Override
    public void updateEmpleadoByTipoPersonaRfc(String rfc, Empleado empleado) {

    }

    @Override
    public void deleteEmpleado(int id) {

    }

    @Override
    public void deleteEmpleadoByTipoPersonaRfc(String rfc) {

    }
}
