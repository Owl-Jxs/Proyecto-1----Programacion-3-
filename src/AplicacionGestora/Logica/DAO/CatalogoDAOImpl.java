package AplicacionGestora.Logica.DAO;

import AplicacionGestora.Logica.Models.Catalogo;
import AplicacionGestora.Logica.Models.Interfaces.CatalogoDAO;
import AplicacionGestora.Logica.Models.Producto;
import AplicacionGestora.Persistencia.PersistenciaCatalogo;

import java.util.List;

public class CatalogoDAOImpl implements CatalogoDAO {
    private final Catalogo catalogo;
    private final PersistenciaCatalogo persistencia;

    /*Crea el DAO del catálogo e inicializa el catálogo cargando los productos almacenados en el archivo.*/
    public CatalogoDAOImpl() {
        persistencia = new PersistenciaCatalogo();
        catalogo = persistencia.cargar();
    }

    /*Agrega un producto al catálogo y guarda los cambios en el archivo de persistencia.*/
    @Override
    public void crearProducto(Producto producto) {
        catalogo.agregarProducto(producto);
        persistencia.guardar(catalogo);
    }

    /*Busca un producto en el catálogo utilizando su identificador.*/
    @Override
    public Producto leerProducto(int id) {
        return catalogo.obtenerProducto(id);
    }

    /*Actualiza un producto existente en el catálogo y guarda*/
    @Override
    public void actualizarProducto(Producto producto) {
        catalogo.actualizarProducto(producto);
        persistencia.guardar(catalogo);
    }

    /*Elimina un producto del catálogo utilizando su identificador
    y guarda los cambios en el archivo de persistencia.*/
    @Override
    public void borrarProducto(int id) {
        catalogo.eliminarProducto(id);
        persistencia.guardar(catalogo);
    }

    /*Obtiene la lista de productos registrados en el catálogo.*/
    @Override
    public List<Producto> listarProductos() {
        return catalogo.listarProductos();
    }
}
