package org.example.proyectofinal.service;

import org.example.proyectofinal.models.estados.Estado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImp implements EstadoService{
    @Override
    public Estado addEstado(Estado estado) {
        return null;
    }

    @Override
    public List<Estado> getEstados() {
        return List.of();
    }

    @Override
    public Estado getEstado(int id) {
        return null;
    }

    @Override
    public Estado getEstadoByEstado(String estado) {
        return null;
    }

    @Override
    public void updateEstado(Estado estado) {

    }

    @Override
    public void updateEstadoByEstado(String estado, Estado estadoObj) {

    }

    @Override
    public void deleteEstado(int id) {

    }

    @Override
    public void deleteEstadoByEstado(String estado) {

    }
}
