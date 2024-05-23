package org.example.proyectofinal.service;

import org.example.proyectofinal.models.maquinas.Maquina;
import org.example.proyectofinal.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaServiceImp implements MaquinaService{
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Override
    public Maquina addMaquina(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    @Override
    public List<Maquina> getMaquinas() {
        return List.of();
    }

    @Override
    public List<Maquina> getMaquinasUsuario(Integer idCliente) {
        return maquinaRepository.getAllMaquinasCliente(idCliente);
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
        maquinaRepository.delete(maquina);
    }

    @Override
    public void deleteMaquinaById(Long id) {
        maquinaRepository.deleteById(id);
    }

    @Override
    public boolean clienteHasMaquina(int idCliente) {
        return maquinaRepository.clienteHasMaquina(idCliente);
    }
}
