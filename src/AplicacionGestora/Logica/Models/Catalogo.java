package AplicacionGestora.Logica.Models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

// Colección de productos en memoria mediante un LinkedHashMap clave = id.
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

    public void agregarProducto(Producto producto) {
        validarProducto(producto);

        if (productos.containsKey(producto.getId())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con el ID " + producto.getId()
            );
        }

        productos.put(producto.getId(), producto);
    }

    public Producto obtenerProducto(int id) {
        validarId(id);
        return productos.get(id);
    }

    public boolean eliminarProducto(int id) {
        validarId(id);
        return productos.remove(id) != null;
    }

    public boolean actualizarProducto(Producto producto) {
        validarProducto(producto);

        if (!productos.containsKey(producto.getId())) {
            return false;
        }

        productos.put(producto.getId(), producto);
        return true;
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    public boolean contiene(int id) {
        validarId(id);
        return productos.containsKey(id);
    }

    public int tamanio() {
        return productos.size();
    }

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