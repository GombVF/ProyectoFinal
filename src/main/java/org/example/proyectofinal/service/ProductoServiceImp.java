package org.example.proyectofinal.service;

import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.productos.dtos.ProductoDtoCarrito;
import org.example.proyectofinal.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ModelMapper modelMapper;

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
        return productoRepository.getProductoById(id);
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
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

    public ProductoDtoCarrito convertToDto(Producto producto) {
        return producto == null ? null : modelMapper.map(producto, ProductoDtoCarrito.class);
    }


    public Producto convertToEntity(ProductoDtoCarrito productoDtoCarrito) {
        return productoDtoCarrito == null ? null : modelMapper.map(productoDtoCarrito, Producto.class);
    }
}
