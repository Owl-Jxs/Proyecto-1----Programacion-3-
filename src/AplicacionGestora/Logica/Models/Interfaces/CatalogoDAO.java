package AplicacionGestora.Logica.Models.Interfaces;
import AplicacionGestora.Logica.Models.Producto;
import java.util.List;

public abstract class CatalogoDAO {
    abstract void crearProducto(Producto producto);

    abstract Producto leerProducto(int id);

    abstract void actualizarProducto(Producto producto);

    abstract void borrarProducto(int id);

    abstract List<Producto> listarProductos();
}