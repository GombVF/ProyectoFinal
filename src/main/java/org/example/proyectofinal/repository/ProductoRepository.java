package org.example.proyectofinal.repository;

import org.example.proyectofinal.models.productos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto getProductoById(Integer id);
}
