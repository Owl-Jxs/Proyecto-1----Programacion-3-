package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Models.CategoriaProducto;

/**
 * Contrato que debe cumplir cualquier producto del sistema.
 */
public interface IProducto {

    /**
     * Asigna un nuevo precio al producto.
     *
     * @param precio el nuevo precio (no negativo y finito)
     */
    void setPrecio(double precio);

    /**
     * @return el identificador del producto
     */
    int getId();

    /**
     * @return el nombre del producto
     */
    String getNombre();

    /**
     * @return el precio actual del producto
     */
    double getPrecio();

    /**
     * @return la categoría a la que pertenece el producto
     */
    CategoriaProducto getCategoria();
}