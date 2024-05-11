package org.example.proyectofinal.service;

import org.example.proyectofinal.models.personasMorales.PersonaMoral;

import java.util.List;

public interface PersonaMoralService {
    PersonaMoral addPersonaMoral(PersonaMoral personaMoral); //create
    List<PersonaMoral> getPersonaMorals(); //read
    PersonaMoral getPersonaMoral(int id);
    PersonaMoral getPersonaMoralByRfc(String rfc);
    void updatePersonaMoral(PersonaMoral personaMoral); //update
    void updatePersonaMoralById(int id, PersonaMoral personaMoral);
    void updatePersonaMoralByRfc(String rfc, PersonaMoral personaMoral);
    void deletePersonaMoral(int id); //delete
    void deletePersonaMoralByRfc(String rfc);

}