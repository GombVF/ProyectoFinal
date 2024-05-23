package org.example.proyectofinal.service;

import org.example.proyectofinal.models.ubicaciones.Ubicacion;

import java.util.List;

public interface UbicacionService {
    Ubicacion addUbicacion(Ubicacion ubicacion);
    List<Ubicacion> getUbicacion();
    List<Ubicacion> getUbicacionByCodigoPostal(String codigoPostal);
    void updateUbicacion(Ubicacion ubicacion);
    void updateUbicacionByCodigoPostal(String codigoPostal, Ubicacion ubicacion);
    void deleteUbicacionByCodigoPostal(String codigoPostal);
    void deleteUbicacionById(Integer id);
}
