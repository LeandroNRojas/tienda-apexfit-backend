package com.tiendapexfit.productos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String marca;

    //En lugar de enviar el objeto Categoría completo
    //la respuesta enviara solo los datos necesarios.
    private Long categoriaId;
    private String categoriaNombre;

}
