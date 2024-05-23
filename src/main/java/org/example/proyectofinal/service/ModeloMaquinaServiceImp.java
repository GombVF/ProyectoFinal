package org.example.proyectofinal.service;

import org.example.proyectofinal.models.modelosMaquinas.ModeloMaquina;
import org.example.proyectofinal.repository.ModeloMaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloMaquinaServiceImp implements ModeloMaquinaService{
    @Autowired
    private ModeloMaquinaRepository modeloMaquinaRepository;

    @Override
    public ModeloMaquina addModeloMaquina(ModeloMaquina m) {
        return null;
    }

    @Override
    public List<ModeloMaquina> getModelosMaquinas() {
        return modeloMaquinaRepository.findAll();
    }

    @Override
    public ModeloMaquina getModeloMaquina(int id) {
        return null;
    }

    @Override
    public ModeloMaquina getModeloMaquinaByModelo(String modelo) {
        return modeloMaquinaRepository.getModeloMaquinaByModelo(modelo);
    }

    @Override
    public void updateModeloMaquina(ModeloMaquina m) {

    }

    @Override
    public void updateModeloMaquinaById(int id, ModeloMaquina m) {

    }

    @Override
    public void deleteModeloMaquina(int id) {

    }

    @Override
    public void deleteModeloMaquinaById(int id) {

    }
}
