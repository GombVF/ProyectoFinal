package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.productos.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
