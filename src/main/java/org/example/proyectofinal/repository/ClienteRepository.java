package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.clientes.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
