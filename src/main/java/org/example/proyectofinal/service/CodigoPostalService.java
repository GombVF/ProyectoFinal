package org.example.proyectofinal.service;

import org.example.proyectofinal.models.codigosPostales.CodigoPostal;

import java.util.List;

public interface CodigoPostalService {
    CodigoPostal addCodigoPostal(CodigoPostal codigoPostal); //Create
    List<CodigoPostal> getCodigoPostal(); //Reade
    CodigoPostal getCodigoPostalByCodigoPostal(String codigoPostal);
    void updateCodigoPostal(CodigoPostal codigoPostal); //Update
    void updateCodigoPostalByCodigoPostal(CodigoPostal codigoPostal);
    void deleteCodigoPostal(CodigoPostal codigoPostal); //Delete
    void deleteCodigoPostalByCodigoPostal(CodigoPostal codigoPostal);
}
