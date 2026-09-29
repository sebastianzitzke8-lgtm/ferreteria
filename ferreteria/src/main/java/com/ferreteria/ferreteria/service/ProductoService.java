package com.ferreteria.ferreteria.service;

import com.ferreteria.ferreteria.model.Producto;
import com.ferreteria.ferreteria.repository.IProductoRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    //Inyección de dependencias del repositorio de productos.
    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }



    @Override
    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoPorId(long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {

        //Validación de que el producto no sea nulo antes de guardarlo en la base de datos.
        if(producto == null){
            return null;
        }
        //Id se genera automaticamente en la DB.
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(long id, Producto producto) {

        //Buscar el producto por id.
        Producto productoExistente = buscarProductoPorId(id);

        //Validación de que el producto exista antes de editarlo.
        if(productoExistente == null){
            return null;
        }

        //Actualizar los campos del producto existente con los valores del producto recibido.
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setStock(producto.getStock());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setMarca(producto.getMarca());
        productoExistente.setDescripcion(producto.getDescripcion());

        //Guardar el producto actualizado en la base de datos.
        return productoRepository.save(productoExistente);
    }

    private static @Nullable Producto getProducto() {
        return null;
    }

    @Override
    public boolean eliminarProducto(long id) {

        //Buscar el producto por id.
        Producto productoExistente = buscarProductoPorId(id);

        //Validación de que el producto exista antes de eliminarlo.
        if(productoExistente == null){
            return false;
        }

        //Eliminar el producto de la base de datos.
        productoRepository.deleteById(id);
        return true;
    }
}
