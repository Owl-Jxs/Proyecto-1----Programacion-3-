package AplicacionGestora.Logica.Controllers;

import AplicacionGestora.Logica.Models.Interfaces.CatalogoDAO;
import AplicacionGestora.Logica.Models.Producto;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aplica las reglas de negocio sobre el catálogo: valida las operaciones
 * (identificadores, productos existentes) antes de delegar en el DAO.
 */
public class ControladorCatalogo {

    private CatalogoDAO catalogoDAO;

    private void validarID(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("No hay productos con ID negativo");
        }
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("No existe el producto");
        }
    }

    private Producto validarProductoRegistrado(int id) {
        Producto producto = catalogoDAO.leerProducto(id);

        if (producto == null) {
            throw new NoSuchElementException(
                    "No existe un producto con el ID " + id
            );
        }

        return producto;
    }

    /**
     * @param catalogoDAO el DAO del catálogo (no nulo)
     * @throws IllegalArgumentException si el DAO es nulo
     */
    public ControladorCatalogo(CatalogoDAO catalogoDAO) {
        if (catalogoDAO == null) {
            throw new IllegalArgumentException("El catalogoDAO no puede ser nulo");
        }
        this.catalogoDAO = catalogoDAO;
    }

    /**
     * Registra un producto nuevo, validando que no exista otro con el mismo id.
     *
     * @param producto el producto a registrar
     * @throws IllegalArgumentException si el producto es nulo o el id ya existe
     */
    public void agregarProducto(Producto producto) {
        validarProducto(producto);

        if (catalogoDAO.leerProducto(producto.getId()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con el ID " + producto.getId()
            );
        }

        catalogoDAO.crearProducto(producto);
    }

    /**
     * Elimina un producto del catálogo.
     *
     * @param id el identificador del producto a eliminar
     * @throws NoSuchElementException si no existe un producto con ese id
     */
    public void eliminarProducto(int id) {
        validarID(id);
        validarProductoRegistrado(id);
        catalogoDAO.borrarProducto(id);
    }

    /**
     * Modifica un producto existente.
     *
     * @param producto el producto con los datos actualizados
     * @throws NoSuchElementException si no existe un producto con ese id
     */
    public void modificarProducto(Producto producto) {
        validarProducto(producto);
        validarProductoRegistrado(producto.getId());
        catalogoDAO.actualizarProducto(producto);
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id el identificador del producto
     * @return el producto registrado
     * @throws NoSuchElementException si no existe un producto con ese id
     */
    public Producto obtenerProducto(int id) {
        validarID(id);
        return validarProductoRegistrado(id);
    }

    /**
     * @return todos los productos registrados en el catálogo
     */
    public List<Producto> obtenerCatalogo() {
        return catalogoDAO.listarProductos();
    }
}