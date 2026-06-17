package com.tiendapexfit.productos.services;

import com.tiendapexfit.productos.entities.Producto;
import com.tiendapexfit.productos.repositories.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    //Instanciar Logger para trazabilidad exigida
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);
    
    private final ProductoRepository productoRepository;

    //Inyeccion de dependencia por const.
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    //1. Obtener todos los productos
    public List<Producto> obtenerTodo(){
        logger.info("Solicitando listado completo de suplementos");
        return productoRepository.findAll();
    }

    //2. Obtener producto por ID
    public Optional<Producto>obtenerPorId(Long id){
        logger.info("Buscando suplemento con ID: {}", id);
        return productoRepository.findById(id);
    }

    //3. Crear un nuevo producto
    public Producto guardar(Producto producto){
        try{
            logger.info("Registrando nuevo producto: {} de la marca {}", producto.getNombre(), producto.getMarca());
            Producto productoGuardado = productoRepository.save(producto);
            logger.info("Producto registrado con exito con ID: {}", productoGuardado.getId());
            return productoGuardado;
        } catch (Exception e) {
            logger.error("Error al guardar datos del producto: {}", e.getMessage());
            throw e;
        }
    }

    //4. Eliminar producto
    public void eliminar(Long id) {
        try {
            logger.warn("Elimando el suplemento ID: {}", id);
            if(productoRepository.existsById(id)){
                productoRepository.deleteById(id);
                logger.info("Suplemento ID: {} eliminado correctamente", id);
            } else { 
                logger.error("No se pudo eliminar el suplemento con ID: {} no existe", id);
            }
        } catch (Exception e) {
            logger.error("Error al intentar eliminar el producto: {}", e.getMessage());
            throw e;
        }
    }

    //5. Buscar por marca
    public List<Producto> buscarPorMarca(String marca){
        logger.info("Buscando suplementos bajo la marca: {}", marca);
        return productoRepository.findByMarca(marca);
    }

}
