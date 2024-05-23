package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.maquinas.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
    @Query("Select m " +
        "from Maquina m " +
        "inner join fetch m.cliente " +
        "where m.cliente.id = :idCliente")
    List<Maquina> getAllMaquinasCliente(@Param("idCliente") int idCliente);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END " +
        "FROM Maquina m " +
        "WHERE m.cliente.id = :idCliente")
    boolean clienteHasMaquina(@Param("idCliente") int idCliente);
}
