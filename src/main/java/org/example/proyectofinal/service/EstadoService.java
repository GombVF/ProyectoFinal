package org.example.proyectofinal.service;

import org.example.proyectofinal.models.estados.Estado;

import java.util.List;

public interface EstadoService {
    Estado addEstado(Estado estado); //create
    List<Estado> getEstados();
    Estado getEstado(int id);
    Estado getEstadoByEstado(String estado);
    void updateEstado(Estado estado); //update
    void updateEstadoByEstado(String estado, Estado estadoObj);
    void deleteEstado(int id); //delete
    void deleteEstadoByEstado(String estado);
}
