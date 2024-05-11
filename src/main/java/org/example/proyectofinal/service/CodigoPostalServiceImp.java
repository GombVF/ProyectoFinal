package org.example.proyectofinal.service;

import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoPostalServiceImp implements CodigoPostalService{
    @Override
    public CodigoPostal addCodigoPostal(CodigoPostal codigoPostal) {
        return null;
    }

    @Override
    public List<CodigoPostal> getCodigoPostal() {
        return List.of();
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
