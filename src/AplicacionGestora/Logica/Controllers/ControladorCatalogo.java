package AplicacionGestora.Logica.Controllers;

import AplicacionGestora.Logica.Models.Interfaces.CatalogoDAO;
import AplicacionGestora.Logica.Models.Producto;

import java.util.List;
import java.util.NoSuchElementException;

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

public ControladorCatalogo(CatalogoDAO catalogoDAO) {
    if (catalogoDAO == null) {
        throw new IllegalArgumentException("El catalogoDAO no puede ser nulo");
    }
    this.catalogoDAO = catalogoDAO;
}

public void agregarProducto(Producto producto) {
    validarProducto(producto);

    if (catalogoDAO.leerProducto(producto.getId()) != null) {
        throw new IllegalArgumentException(
                "Ya existe un producto con el ID " + producto.getId()
        );
    }

    catalogoDAO.crearProducto(producto);
}

public void eliminarProducto(int id) {
    validarID(id);
    validarProductoRegistrado(id);
    catalogoDAO.borrarProducto(id);
}

public void modificarProducto(Producto producto) {
    validarProducto(producto);
    validarProductoRegistrado(producto.getId());
    catalogoDAO.actualizarProducto(producto);
}

public Producto obtenerProducto(int id) {
    validarID(id);
    return validarProductoRegistrado(id);
}

public List<Producto> obtenerCatalogo() {
    return catalogoDAO.listarProductos();
}
}
