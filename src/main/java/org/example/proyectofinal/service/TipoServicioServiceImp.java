package org.example.proyectofinal.service;

import org.example.proyectofinal.models.tiposServicios.TipoServicio;
import org.example.proyectofinal.repository.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoServicioServiceImp implements TipoServicioService{

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Override
    public TipoServicio addTipoServicio(TipoServicio tipoServicio) {
        return null;
    }

    @Override
    public List<TipoServicio> getTiposServicios() {
        return tipoServicioRepository.findAll();
    }

    @Override
    public TipoServicio getTipoServicioById(int id) {
        return null;
    }

    @Override
    public void updateTipoServicio(TipoServicio tipoServicio) {

    }

    @Override
    public void updateTipoServicioById(TipoServicio tipoServicio, int id) {

    }

    @Override
    public void deleteTipoServicioById(int id) {

    }
}
