package org.example.proyectofinal.service;

import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.productos.dtos.ProductoDtoCarrito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {
    Producto addProducto(Producto producto); //create
    List<Producto> getProductos(); //read
    Producto getProducto(int id);
    Page<Producto> findAll(Pageable pageable);
    List<Producto> findAll();
    void updateProducto(Producto producto); //update
    void updateProductoById(Producto producto, int id);
    void deleteProducto(int id); //delete



}
