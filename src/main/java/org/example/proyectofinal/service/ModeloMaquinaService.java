package org.example.proyectofinal.service;

import org.example.proyectofinal.models.modelosMaquinas.ModeloMaquina;

import java.util.List;

public interface ModeloMaquinaService {
    ModeloMaquina addModeloMaquina(ModeloMaquina m); //create
    List<ModeloMaquina> getModelosMaquinas(); //read
    ModeloMaquina getModeloMaquina(int id);
    ModeloMaquina getModeloMaquinaByModelo(String modelo);
    void updateModeloMaquina(ModeloMaquina m); //update
    void updateModeloMaquinaById(int id, ModeloMaquina m);
    void deleteModeloMaquina(int id); //delete
    void deleteModeloMaquinaById(int id);
}
