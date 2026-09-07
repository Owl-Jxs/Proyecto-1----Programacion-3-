package AplicacionGestora.Logica.DAO;

import AplicacionGestora.Logica.Models.Catalogo;
import AplicacionGestora.Logica.Models.Interfaces.CatalogoDAO;
import AplicacionGestora.Logica.Models.Producto;
import AplicacionGestora.Persistencia.PersistenciaCatalogo;

import java.util.List;

/**
 * DAO del catálogo que opera sobre un {@link Catalogo} en memoria y persiste
 * los cambios en CSV.
 */
public class CatalogoDAOImpl implements CatalogoDAO {

    private final Catalogo catalogo;
    private final PersistenciaCatalogo persistencia;

    /**
     * Inicializa el catálogo cargando los productos almacenados en el archivo.
     */
    public CatalogoDAOImpl() {
        persistencia = new PersistenciaCatalogo();
        catalogo = persistencia.cargar();
    }

    @Override
    public void crearProducto(Producto producto) {
        catalogo.agregarProducto(producto);
        persistencia.guardar(catalogo);
    }

    @Override
    public Producto leerProducto(int id) {
        return catalogo.obtenerProducto(id);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        catalogo.actualizarProducto(producto);
        persistencia.guardar(catalogo);
    }

    @Override
    public void borrarProducto(int id) {
        catalogo.eliminarProducto(id);
        persistencia.guardar(catalogo);
    }

    @Override
    public List<Producto> listarProductos() {
        return catalogo.listarProductos();
    }

    @Override
    public int siguienteId() {
        return catalogo.siguienteId();
    }
}