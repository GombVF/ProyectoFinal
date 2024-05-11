package org.example.proyectofinal.service;

import org.example.proyectofinal.models.envios.Envio;

import java.util.List;

public interface EnvioService {
    Envio addEnvio(Envio envio); //create
    List<Envio> getEnvios(); //read
    List<Envio> getEnvioByClaveEnvio(String clave_producto);
    void updateEnvio(Envio envio); //update
    void updateEnvioByClaveEnvio(String claveEnvio, Envio envio);
    void deleteEnvio(Envio envio); //delete
    void deleteEnvioByClaveEnvio(String claveEnvio);



}
