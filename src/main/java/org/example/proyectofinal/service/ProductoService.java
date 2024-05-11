package org.example.proyectofinal.service;

import org.example.proyectofinal.models.productos.Producto;

import java.util.List;

public interface ProductoService {
    Producto addProducto(Producto producto); //create
    List<Producto> getProductos(); //read
    Producto getProducto(int id);
    void updateProducto(Producto producto); //update
    void updateProductoById(Producto producto, int id);
    void deleteProducto(int id); //delete



}
