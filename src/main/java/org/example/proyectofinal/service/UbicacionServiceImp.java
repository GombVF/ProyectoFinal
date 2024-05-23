package org.example.proyectofinal.service;

import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionServiceImp implements UbicacionService {
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public Ubicacion addUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public List<Ubicacion> getUbicacion() {
        return List.of();
    }

    @Override
    public List<Ubicacion> getUbicacionByCodigoPostal(String codigoPostal) {
        return List.of();
    }

    @Override
    public void updateUbicacion(Ubicacion ubicacion) {

    }

    @Override
    public void updateUbicacionByCodigoPostal(String codigoPostal, Ubicacion ubicacion) {

    }

    @Override
    public void deleteUbicacionByCodigoPostal(String codigoPostal) {

    }

    @Override
    public void deleteUbicacionById(Integer id) {
        ubicacionRepository.deleteById(id);
    }
}
