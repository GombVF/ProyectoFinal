package org.example.proyectofinal.service;

import org.example.proyectofinal.models.roles.Rol;
import org.example.proyectofinal.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImp implements RolService{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol addRol(Rol rol) {
        return null;
    }

    @Override
    public List<Rol> getRoles() {
        return List.of();
    }

    @Override
    public Rol geRolByRol(String rol) {
        return rolRepository.getRolByRol(rol);
    }

    @Override
    public void updateRol(Rol rol) {

    }

    @Override
    public void updateRolById(int id, String rol) {

    }

    @Override
    public void deleteRol(Rol rol) {

    }

    @Override
    public void deleteRolById(int id) {

    }
}
