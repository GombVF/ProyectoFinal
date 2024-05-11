package org.example.proyectofinal.service;

import org.example.proyectofinal.models.maquinas.Maquina;

import java.util.List;

public interface MaquinaService {
    Maquina addMaquina(Maquina maquina); //create
    List<Maquina> getMaquinas(); //read
    Maquina getMaquina(int id);
    void updateMaquina(Maquina maquina); //update
    void updateMaquinaById(int id, Maquina maquina);
    void deleteMaquina(Maquina maquina); //delete
    void deleteMaquinaById(int id);
}
