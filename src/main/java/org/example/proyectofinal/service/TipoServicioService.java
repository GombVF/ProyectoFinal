package org.example.proyectofinal.service;

import org.example.proyectofinal.models.tiposServicios.TipoServicio;

import java.util.List;

public interface TipoServicioService {
    TipoServicio addTipoServicio(TipoServicio tipoServicio); //create
    List<TipoServicio> getTiposServicios(); //read
    TipoServicio getTipoServicioById(int id);
    void updateTipoServicio(TipoServicio tipoServicio); //update
    void updateTipoServicioById(TipoServicio tipoServicio, int id);
    void deleteTipoServicioById(int id); //delete
}
