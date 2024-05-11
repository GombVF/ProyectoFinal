package org.example.proyectofinal.service;

import org.example.proyectofinal.models.areas.Area;
import java.util.List;

public interface AreaService {
    //CRUD
    Area addArea(Area area); //Create
    List<Area> getAreas(); //Read
    Area getAreaByNombreArea(String nombreArea);
    void updateArea(Area area); //Update
    void updateAreaById(int id, String nombreArea);
    void deleteArea(Area area); //Delete
    void deleteAreaById(int id);
}
