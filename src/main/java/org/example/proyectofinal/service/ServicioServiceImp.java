package org.example.proyectofinal.service;

import org.example.proyectofinal.models.servicios.Servicio;
import org.example.proyectofinal.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImp implements ServicioService{

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio addServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public List<Servicio> getServicios() {
        return List.of();
    }

    @Override
    public List<Servicio> getServiciosPendientesCliente(int id) {
        return servicioRepository.getServiciosPendisntes(id);
    }

    @Override
    public List<Servicio> getServiciosCompletadosCliente(int id) {
        return servicioRepository.getServiciosCompletados(id);
    }

    @Override
    public List<Servicio> getServiciosSinAtender() {
        return servicioRepository.getServiciosByEmpleadoIsNull();
    }

    @Override
    public List<Servicio> getServiciosPendientesEmpleado(int id) {
        return servicioRepository.getServiciosByEmpleado_IdAndAndFechaFinIsNull(id);
    }

    @Override
    public List<Servicio> getServiciosCompletadosEmpleado(int id) {
        return servicioRepository.getServiciosByEmpleado_IdAndAndFechaFinIsNotNull(id);
    }

    @Override
    public Optional<Servicio> getServicioById(int id) {
        return servicioRepository.findById((long)id);
    }

    @Override
    public void updateServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public void updateServicioById(Servicio servicio, int id) {

    }

    @Override
    public void deleteServicio(int id) {

    }
}
