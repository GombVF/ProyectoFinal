package org.example.proyectofinal.service;

import org.example.proyectofinal.models.envios.Envio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioServiceImp implements EnvioService{
    @Override
    public Envio addEnvio(Envio envio) {
        return null;
    }

    @Override
    public List<Envio> getEnvios() {
        return List.of();
    }

    @Override
    public List<Envio> getEnvioByClaveEnvio(String clave_producto) {
        return List.of();
    }

    @Override
    public void updateEnvio(Envio envio) {

    }

    @Override
    public void updateEnvioByClaveEnvio(String claveEnvio, Envio envio) {

    }

    @Override
    public void deleteEnvio(Envio envio) {

    }

    @Override
    public void deleteEnvioByClaveEnvio(String claveEnvio) {

    }
}
