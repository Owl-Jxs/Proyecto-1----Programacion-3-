package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Models.Producto;

import java.util.ArrayList;

public interface CatalogoDAO {

    ArrayList<Producto> productos = new ArrayList<>();

    /*Agrega un producto al catálogo*/
    default void crearProducto(Producto producto) {
        productos.add(producto);
    }

    /*Busca un producto por medio de su identificador*/
    default Producto leerProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    /*Actualiza la información de un producto existente*/
    default void actualizarProducto(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                return;
            }
        }
    }

    /*Elimina un producto del catálogo utilizando su identificador*/
    default void borrarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return;
            }
        }
    }

    /*Obtiene todos los productos registrados en el catálogo*/
    default ArrayList<Producto> listarProductos() {
        return productos;
    }
}