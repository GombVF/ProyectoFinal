package org.example.proyectofinal.service;

import org.example.proyectofinal.models.servicios.Servicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImp implements ServicioService{
    @Override
    public Servicio addServicio(Servicio servicio) {
        return null;
    }

    @Override
    public List<Servicio> getServicios() {
        return List.of();
    }

    @Override
    public Servicio getServicioById(int id) {
        return null;
    }

    @Override
    public void updateServicio(Servicio servicio) {

    }

    @Override
    public void updateServicioById(Servicio servicio, int id) {

    }

    @Override
    public void deleteServicio(int id) {

    }
}
