package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Models.Producto;

import java.util.List;

/**
 * Contrato de acceso a datos para el catálogo de productos.
 */
public interface CatalogoDAO {

    /**
     * Registra un producto nuevo en el catálogo.
     *
     * @param producto el producto a registrar
     */
    void crearProducto(Producto producto);

    /**
     * Busca un producto por su identificador.
     *
     * @param id el identificador del producto
     * @return el producto si existe, {@code null} en caso contrario
     */
    Producto leerProducto(int id);

    /**
     * Reemplaza un producto existente.
     *
     * @param producto el producto con los datos actualizados
     */
    void actualizarProducto(Producto producto);

    /**
     * Elimina un producto por su identificador.
     *
     * @param id el identificador del producto a eliminar
     */
    void borrarProducto(int id);

    /**
     * @return una lista con todos los productos del catálogo
     */
    List<Producto> listarProductos();
}