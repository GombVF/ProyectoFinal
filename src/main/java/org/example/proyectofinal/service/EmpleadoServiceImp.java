package org.example.proyectofinal.service;

import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImp implements EmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado addEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(int id) {
        return null;
    }

    @Override
    public Empleado getEmpleadoByTipoPersonaRfc(String rfc) {
        return empleadoRepository.getEmpleadoByPersonaFisica_Rfc(rfc);
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
        empleadoRepository.deleteById((long)id);
    }

    @Override
    public void deleteEmpleadoByTipoPersonaRfc(String rfc) {

    }
}
