package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.servicios.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    @Query("select s " +
        "from Servicio s " +
        "inner join fetch s.maquina sm " +
        "inner join fetch sm.cliente smc " +
        "where smc.id = :id and s.fechaFin is null " +
        "order by s.fechaInicio")
    List<Servicio> getServiciosPendisntes(int id);

    @Query("select s " +
        "from Servicio s " +
        "inner join fetch s.maquina sm " +
        "inner join fetch sm.cliente smc " +
        "where smc.id = :id and s.fechaFin is not null " +
        "order by s.fechaInicio")
    List<Servicio> getServiciosCompletados(int id);

    List<Servicio> getServiciosByEmpleadoIsNull();
    List<Servicio> getServiciosByEmpleado_IdAndAndFechaFinIsNull(int id);
    List<Servicio> getServiciosByEmpleado_IdAndAndFechaFinIsNotNull(int id);

}
