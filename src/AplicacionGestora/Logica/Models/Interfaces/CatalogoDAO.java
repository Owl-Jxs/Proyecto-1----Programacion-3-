package AplicacionGestora.Logica.Models.Interfaces;
import AplicacionGestora.Logica.Models.Producto;
import java.util.List;
public abstract class CatalogoDAO {
    void crearProducto(Producto producto);

    Producto leerProducto(int id);

    void actualizarProducto(Producto producto);

    void borrarProducto(int id);

    List<Producto> listarProductos();
}