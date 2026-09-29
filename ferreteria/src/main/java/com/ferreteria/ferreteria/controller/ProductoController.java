package com.ferreteria.ferreteria.controller;

import com.ferreteria.ferreteria.model.Producto;
import com.ferreteria.ferreteria.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    //Inyección de dependencias del servicio de productos.
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;

    }

    //Read.
    @GetMapping
    public List<Producto> traerProductos() {
        return productoService.traerProductos();
    }

    //Read de producto por id.
    @GetMapping("/{id}")
public ResponseEntity<?> buscarProducto(@PathVariable long id){
        Producto producto = productoService.buscarProductoPorId(id);
        if(producto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra un producto con ese codigo.");

        }
        return ResponseEntity.ok(producto);
    }

    //Create.
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        Producto productoCreado = productoService.crearProducto(producto);
        if(productoCreado == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el producto.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    //Update.
    @PutMapping("/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody Producto productoModificado){

        Producto productoEditado = productoService.editarProducto(id, productoModificado);
        if(productoEditado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra un producto con ese codigo.");
        }
        return ResponseEntity.ok(productoEditado);
    }

    //Delete.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        boolean eliminado = productoService.eliminarProducto(id);
        if(!eliminado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra un producto con ese codigo.");
        }
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }


}




