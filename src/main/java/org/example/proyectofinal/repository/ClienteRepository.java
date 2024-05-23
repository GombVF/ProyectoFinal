package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.clientes.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsClienteByPersonaFisica_Rfc(String personaFisica);
    boolean existsClienteByPersonaMoral_Rfc(String personaMoral);

    @Query("Select c " +
        "from Cliente c " +
        "left join fetch c.personaFisica cpf " +
        "where cpf.rfc = :personaFisica")
    Cliente getClienteByPersonaFisica_Rfc(String personaFisica);
    Cliente getClienteByPersonaMoral_Rfc(String personaMoral);
}
