package com.ferreteria.ferreteria.service;

import com.ferreteria.ferreteria.model.Producto;

import java.util.List;

public interface IProductoService {

    //Metodos para el crud.

    //Read.
    List<Producto> traerProductos();
    Producto buscarProductoPorId(long id);

    //Create.
    Producto crearProducto(Producto producto);

    //Update.
    Producto editarProducto(long id, Producto producto);

    //Delete.
    boolean eliminarProducto(long id);

}

