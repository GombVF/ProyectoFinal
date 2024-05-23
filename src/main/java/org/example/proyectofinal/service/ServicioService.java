package org.example.proyectofinal.service;

import org.example.proyectofinal.models.servicios.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    Servicio addServicio(Servicio servicio); //create
    List<Servicio> getServicios(); //read
    List<Servicio> getServiciosPendientesCliente(int id);
    List<Servicio> getServiciosCompletadosCliente(int id);
    List<Servicio> getServiciosSinAtender();
    List<Servicio> getServiciosPendientesEmpleado(int id);
    List<Servicio> getServiciosCompletadosEmpleado(int id);
    Optional<Servicio> getServicioById(int id);
    void updateServicio(Servicio servicio); //update
    void updateServicioById(Servicio servicio, int id);
    void deleteServicio(int id); //delete
}
