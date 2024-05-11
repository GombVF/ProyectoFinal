package org.example.proyectofinal.service;

import org.example.proyectofinal.models.servicios.Servicio;

import java.util.List;

public interface ServicioService {
    Servicio addServicio(Servicio servicio); //create
    List<Servicio> getServicios(); //read
    Servicio getServicioById(int id);
    void updateServicio(Servicio servicio); //update
    void updateServicioById(Servicio servicio, int id);
    void deleteServicio(int id); //delete
}
