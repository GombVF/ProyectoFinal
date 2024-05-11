package org.example.proyectofinal.service;

import org.example.proyectofinal.models.maquinas.Maquina;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaServiceImp implements MaquinaService{
    @Override
    public Maquina addMaquina(Maquina maquina) {
        return null;
    }

    @Override
    public List<Maquina> getMaquinas() {
        return List.of();
    }

    @Override
    public Maquina getMaquina(int id) {
        return null;
    }

    @Override
    public void updateMaquina(Maquina maquina) {

    }

    @Override
    public void updateMaquinaById(int id, Maquina maquina) {

    }

    @Override
    public void deleteMaquina(Maquina maquina) {

    }

    @Override
    public void deleteMaquinaById(int id) {

    }
}
