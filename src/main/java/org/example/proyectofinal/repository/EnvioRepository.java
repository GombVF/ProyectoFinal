package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.envios.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    @Query("select e " +
        "from Envio e " +
        "inner join fetch e.ubicacion eu " +
        "where eu.id =:id " +
        "")
    List<Envio> getEnviosUsuario(@Param("id") int id);

    List<Envio> getEnviosByEmpleadoNull();

    List<Envio> getEnviosByEmpleado_IdAndAndFechaEntregaNullOrderByFechaSolicitud(int id);
    List<Envio> getEnviosByEmpleado_IdAndAndFechaEntregaNotNullOrderByFechaSolicitud(int id);

    List<Envio> getEnviosByClaveProducto(String claveProducto);
}
