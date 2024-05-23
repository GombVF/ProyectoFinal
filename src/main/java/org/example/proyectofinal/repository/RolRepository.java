package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.roles.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RolRepository extends JpaRepository<Rol,Integer> {
    Rol getRolByRol(String rol);
}
