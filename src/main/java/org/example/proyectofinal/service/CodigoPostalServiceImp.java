package org.example.proyectofinal.service;

import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.example.proyectofinal.repository.CodigoPostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoPostalServiceImp implements CodigoPostalService{
    @Autowired
    private CodigoPostalRepository codigoPostalRepository;

    @Override
    public CodigoPostal addCodigoPostal(CodigoPostal codigoPostal) {
        return null;
    }

    @Override
    public List<CodigoPostal> getCodigoPostal() {
        return List.of();
    }

    @Override
    public List<CodigoPostal> findAll() {
        return codigoPostalRepository.findAll();
    }

    @Override
    public CodigoPostal getCodigoPostalByCodigoPostal(String codigoPostal) {
        return null;
    }

    @Override
    public void updateCodigoPostal(CodigoPostal codigoPostal) {

    }

    @Override
    public void updateCodigoPostalByCodigoPostal(CodigoPostal codigoPostal) {

    }

    @Override
    public void deleteCodigoPostal(CodigoPostal codigoPostal) {

    }

    @Override
    public void deleteCodigoPostalByCodigoPostal(CodigoPostal codigoPostal) {

    }
}
