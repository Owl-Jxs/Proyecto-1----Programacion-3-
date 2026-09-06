package AplicacionGestora.Logica.DAO;

import AplicacionGestora.Logica.Models.Interfaces.CatalogoDAO;
import AplicacionGestora.Logica.Models.Producto;

import java.util.ArrayList;
import java.util.List;

// Implementación en memoria del catálogo mediante un ArrayList.
public class CatalogoDAOImpl implements CatalogoDAO {

    private final List<Producto> productos;

    public CatalogoDAOImpl() {
        productos = new ArrayList<>();
    }

    @Override
    public void crearProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto leerProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    @Override
    public void actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            Producto productoRegistrado = productos.get(i);

            if (productoRegistrado.getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                return;
            }
        }
    }

    @Override
    public void borrarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }
}
