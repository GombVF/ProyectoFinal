package org.example.proyectofinal.service;

import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.repository.PersonaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaFisicaServiceImp implements PersonaFisicaService {
    @Autowired
    private PersonaFisicaRepository personaFisicaRepository;

    @Override
    public PersonaFisica addPersonaFisica(PersonaFisica personaFisica) {
        return personaFisicaRepository.save(personaFisica);
    }

    @Override
    public List<PersonaFisica> getPersonaFisicas() {
        return List.of();
    }

    @Override
    public PersonaFisica getPersonaFisica(int id) {
        return null;
    }

    @Override
    public PersonaFisica getPersonaFisicaByRfc(String rfc) {
        return null;
    }

    @Override
    public void updatePersonaFisica(PersonaFisica personaFisica) {

    }

    @Override
    public void updatePersonaFisicaById(int id, PersonaFisica personaFisica) {

    }

    @Override
    public void updatePersonaFisicaByRfc(String rfc, PersonaFisica personaFisica) {

    }

    @Override
    public void deletePersonaFisica(int id) {

    }

    @Override
    public void deletePersonaFisicaByRfc(String rfc) {

    }
}
