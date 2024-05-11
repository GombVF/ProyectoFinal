package org.example.proyectofinal.service;

import org.example.proyectofinal.models.personasFisicas.PersonaFisica;

import java.util.List;

public interface PersonaFisicaService {
    PersonaFisica addPersonaFisica(PersonaFisica personaFisica); //create
    List<PersonaFisica> getPersonaFisicas(); //read
    PersonaFisica getPersonaFisica(int id);
    PersonaFisica getPersonaFisicaByRfc(String rfc);
    void updatePersonaFisica(PersonaFisica personaFisica); //update
    void updatePersonaFisicaById(int id, PersonaFisica personaFisica);
    void updatePersonaFisicaByRfc(String rfc, PersonaFisica personaFisica);
    void deletePersonaFisica(int id); //delete
    void deletePersonaFisicaByRfc(String rfc);

}