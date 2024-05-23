package org.example.proyectofinal.service;

import org.example.proyectofinal.models.areas.Area;
import org.example.proyectofinal.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImp implements AreaService{

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Area addArea(Area area) {
        return null;
    }

    @Override
    public List<Area> getAreas() {
        return List.of();
    }

    @Override
    public Area getAreaByNombreArea(String nombreArea) {
        return areaRepository.findByNombreArea(nombreArea);
    }

    @Override
    public void updateArea(Area area) {

    }

    @Override
    public void updateAreaById(int id, String nombreArea) {

    }

    @Override
    public void deleteArea(Area area) {

    }

    @Override
    public void deleteAreaById(int id) {

    }
}
