package org.example.proyectofinal.service;

import org.example.proyectofinal.models.municipios.Municipio;

import java.util.List;

public interface MunicipioService {
    Municipio addMunicipio(Municipio municipio); //create
    List<Municipio> getMunicipios(); //read
    Municipio getMunicipio(int id);
    Municipio getMunicipioByMunicipio(String municipio);
    void updateMunicipio(Municipio municipio); //update
    void updateMunicipioById(int id, Municipio municipio);
    void updateMunicipioByMunicipio(String municipio, Municipio municipioObj);
    void deleteMunicipio(int id); //delete
    void deleteMunicipio(String municipio);

}
