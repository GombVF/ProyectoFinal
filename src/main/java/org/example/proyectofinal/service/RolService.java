package org.example.proyectofinal.service;

import org.example.proyectofinal.models.areas.Area;
import org.example.proyectofinal.models.roles.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolService{
    Rol addRol(Rol rol); //Create
    List<Rol> getRoles(); //Read
    Rol geRolByRol(String rol);
    void updateRol(Rol rol); //Update
    void updateRolById(int id, String rol);
    void deleteRol(Rol rol); //Delete
    void deleteRolById(int id);
}
