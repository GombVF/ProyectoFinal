package org.example.proyectofinal.service;

import org.example.proyectofinal.models.envios.Envio;
import org.example.proyectofinal.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioServiceImp implements EnvioService{
    @Autowired
    private EnvioRepository envioRepository;


    @Override
    public Envio addEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public List<Envio> getEnvios() {
        return List.of();
    }

    @Override
    public List<Envio> getEnvioByClaveEnvio(String claveProducto) {
        return envioRepository.getEnviosByClaveProducto(claveProducto);
    }

    @Override
    public List<Envio> getEnviosUsuario(int id) {
        return envioRepository.getEnviosUsuario(id);
    }

    @Override
    public List<Envio> getEnviosSinAtender() {
        return envioRepository.getEnviosByEmpleadoNull();
    }

    @Override
    public List<Envio> getEnviosPendientesByEmpleadoId(int id) {
        return envioRepository.getEnviosByEmpleado_IdAndAndFechaEntregaNullOrderByFechaSolicitud(id);
    }

    @Override
    public List<Envio> getEnviosCompletadosByEmpleadoId(int id) {
        return envioRepository.getEnviosByEmpleado_IdAndAndFechaEntregaNotNullOrderByFechaSolicitud(id);
    }

    @Override
    public void updateEnvio(Envio envio) {
        envioRepository.save(envio);
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
