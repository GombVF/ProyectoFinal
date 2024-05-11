package org.example.proyectofinal.service;

import org.example.proyectofinal.models.areas.Area;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImp implements AreaService{
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
        return null;
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
