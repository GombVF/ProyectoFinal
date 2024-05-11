package org.example.proyectofinal.service;

import org.example.proyectofinal.models.productos.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService{
    @Override
    public Producto addProducto(Producto producto) {
        return null;
    }

    @Override
    public List<Producto> getProductos() {
        return List.of();
    }

    @Override
    public Producto getProducto(int id) {
        return null;
    }

    @Override
    public void updateProducto(Producto producto) {

    }

    @Override
    public void updateProductoById(Producto producto, int id) {

    }

    @Override
    public void deleteProducto(int id) {

    }
}
