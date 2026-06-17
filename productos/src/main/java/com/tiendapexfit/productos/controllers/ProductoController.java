package com.tiendapexfit.productos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendapexfit.productos.entities.Producto;
import com.tiendapexfit.productos.services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    //Inyeccion servicio por constructor
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    //1. GET - Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.obtenerTodo();
        return ResponseEntity.ok(productos);
    }

    //2. GET - Obtener productos especifico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        return productoService.obtenerPorId(id)
        .map(producto -> ResponseEntity.ok(producto))
        .orElse(ResponseEntity.notFound().build());
    }

    //3. POST - Crear nuevo producto
    //@Valid activa las reglas como @NotBlank, @Positive, etc. usadas en la Entidad
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto){
        Producto nuevoProducto = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    //4. DELETE - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.obtenerPorId(id).isPresent()){
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //5. GET - Filtro personalizado por Marca
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> filtrarPorMarca(@RequestParam String marca) {
        List<Producto> productos = productoService.buscarPorMarca(marca);
        return ResponseEntity.ok(productos);
    }

}
