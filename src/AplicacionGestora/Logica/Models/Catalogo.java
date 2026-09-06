package AplicacionGestora.Logica.Models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Colección de productos en memoria, indexada por identificador mediante un
 * {@link LinkedHashMap}. Garantiza unicidad de id y preserva el orden de inserción.
 */
public class Catalogo {

    private final LinkedHashMap<Integer, Producto> productos;

    public Catalogo() {
        productos = new LinkedHashMap<>();
    }

    private static void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
    }

    private static void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
    }

    /**
     * Registra un producto nuevo en el catálogo.
     *
     * @param producto el producto a registrar
     * @throws IllegalArgumentException si el producto es nulo o ya existe un id igual
     */
    public void agregarProducto(Producto producto) {
        validarProducto(producto);

        if (productos.containsKey(producto.getId())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con el ID " + producto.getId()
            );
        }

        productos.put(producto.getId(), producto);
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id el identificador del producto
     * @return el producto si existe, {@code null} en caso contrario
     */
    public Producto obtenerProducto(int id) {
        validarId(id);
        return productos.get(id);
    }

    /**
     * Elimina un producto por su identificador.
     *
     * @param id el identificador del producto a eliminar
     * @return {@code true} si existía y fue eliminado; {@code false} en caso contrario
     */
    public boolean eliminarProducto(int id) {
        validarId(id);
        return productos.remove(id) != null;
    }

    /**
     * Reemplaza un producto existente conservando su identificador.
     *
     * @param producto el producto con los datos actualizados
     * @return {@code true} si el producto existía y fue actualizado; {@code false} en caso contrario
     */
    public boolean actualizarProducto(Producto producto) {
        validarProducto(producto);

        if (!productos.containsKey(producto.getId())) {
            return false;
        }

        productos.put(producto.getId(), producto);
        return true;
    }

    /**
     * @return una lista con todos los productos en orden de inserción
     */
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    /**
     * Indica si existe un producto con el identificador dado.
     *
     * @param id el identificador a buscar
     * @return {@code true} si existe un producto con ese id
     */
    public boolean contiene(int id) {
        validarId(id);
        return productos.containsKey(id);
    }

    /**
     * @return la cantidad de productos registrados
     */
    public int tamanio() {
        return productos.size();
    }

    /**
     * Calcula el siguiente identificador disponible, como máximo id + 1.
     *
     * @return 1 si el catálogo está vacío; en caso contrario, el máximo id existente + 1
     */
    public int siguienteId() {
        int nuevoId = 1;

        for (int id : productos.keySet()) {
            if (id >= nuevoId) {
                nuevoId = id + 1;
            }
        }

        return nuevoId;
    }

}