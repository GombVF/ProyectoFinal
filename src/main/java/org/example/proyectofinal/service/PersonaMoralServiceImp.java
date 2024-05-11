package org.example.proyectofinal.service;

import org.example.proyectofinal.models.personasMorales.PersonaMoral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaMoralServiceImp implements PersonaMoralService{
    @Override
    public PersonaMoral addPersonaMoral(PersonaMoral personaMoral) {
        return null;
    }

    @Override
    public List<PersonaMoral> getPersonaMorals() {
        return List.of();
    }

    @Override
    public PersonaMoral getPersonaMoral(int id) {
        return null;
    }

    @Override
    public PersonaMoral getPersonaMoralByRfc(String rfc) {
        return null;
    }

    @Override
    public void updatePersonaMoral(PersonaMoral personaMoral) {

    }

    @Override
    public void updatePersonaMoralById(int id, PersonaMoral personaMoral) {

    }

    @Override
    public void updatePersonaMoralByRfc(String rfc, PersonaMoral personaMoral) {

    }

    @Override
    public void deletePersonaMoral(int id) {

    }

    @Override
    public void deletePersonaMoralByRfc(String rfc) {

    }
}
