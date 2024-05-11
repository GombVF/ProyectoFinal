package org.example.proyectofinal.service;

import org.example.proyectofinal.models.municipios.Municipio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServiceImp implements MunicipioService{
    @Override
    public Municipio addMunicipio(Municipio municipio) {
        return null;
    }

    @Override
    public List<Municipio> getMunicipios() {
        return List.of();
    }

    @Override
    public Municipio getMunicipio(int id) {
        return null;
    }

    @Override
    public Municipio getMunicipioByMunicipio(String municipio) {
        return null;
    }

    @Override
    public void updateMunicipio(Municipio municipio) {

    }

    @Override
    public void updateMunicipioById(int id, Municipio municipio) {

    }

    @Override
    public void updateMunicipioByMunicipio(String municipio, Municipio municipioObj) {

    }

    @Override
    public void deleteMunicipio(int id) {

    }

    @Override
    public void deleteMunicipio(String municipio) {

    }
}
