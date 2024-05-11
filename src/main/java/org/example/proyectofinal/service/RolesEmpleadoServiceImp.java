package org.example.proyectofinal.service;

import org.example.proyectofinal.models.RolesEmpleados.RolesEmpleado;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.roles.Rol;
import org.example.proyectofinal.repository.RolesEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesEmpleadoServiceImp implements RolesEmpleadoService{
    @Autowired
    private RolesEmpleadoRepository rolesEmpleadoRepository;
    @Override
    public RolesEmpleado addRolEmpleado(Empleado empleado, Rol rol) {
        return null;
    }

    @Override
    public List<RolesEmpleado> getRolesEmpleados() {
        return List.of();
    }

    @Override
    public List<RolesEmpleado> getRolEmpleado(Empleado empleado) {
        return List.of();
    }

    @Override
    public List<RolesEmpleado> getRolEmpleadoByEmpleadoId(int id) {
        return List.of();
    }

    @Override
    public Empleado getEmpleadoByPersonaFisicaRfc(String fisicaRfc) {
        return null;
    }

    @Override
    public void updateRolEmpleado(RolesEmpleado rolEmpleado) {

    }

    @Override
    public void updateRolEmpleadoById(RolesEmpleado producto, int id) {

    }

    @Override
    public void updateRolEmpleadoByEmpleadoId(RolesEmpleado producto, int id) {

    }

    @Override
    public void deleteRolEmpleadoById(int id) {

    }

    @Override
    public boolean existsRolEmpleadoByEmpleadoPersonaFisicaRfc(String fisicaRfc) {
        return rolesEmpleadoRepository.existsByEmpleado_PersonaFisica_Rfc(fisicaRfc);
    }
}
