package org.example.proyectofinal.service;

import org.example.proyectofinal.models.maquinas.Maquina;

import java.util.List;
import java.util.Optional;

public interface MaquinaService {
    Maquina addMaquina(Maquina maquina); //create
    List<Maquina> getMaquinas(); //read
    List<Maquina> getMaquinasUsuario(Integer idCliente);
    Optional<Maquina> getMaquina(int id);
    void updateMaquina(Maquina maquina); //update
    void updateMaquinaById(int id, Maquina maquina);
    void deleteMaquina(Maquina maquina); //delete
    void deleteMaquinaById(Long id);

    boolean clienteHasMaquina(int idCliente);
}
