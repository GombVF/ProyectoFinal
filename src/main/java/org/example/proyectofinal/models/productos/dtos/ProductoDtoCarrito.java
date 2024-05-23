package org.example.proyectofinal.models.productos.dtos;

import lombok.Data;

@Data
public class ProductoDtoCarrito {
    private String producto;
    private int cantidad;
    private String unidad;
    private int cantidadSellos;
    private double precio;
    private boolean addedToShoppingCart;

    // Getters and Setters
}
