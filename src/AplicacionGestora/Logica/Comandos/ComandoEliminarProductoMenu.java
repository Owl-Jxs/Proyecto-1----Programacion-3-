package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

/**
 * Representa la acción de eliminar un producto del menú.
 */
public class ComandoEliminarProductoMenu implements IComando {

    private ControladorCatalogo controladorCatalogo;
    private Producto productoEliminado;

    /**
     * Guarda una copia del producto a eliminar para poder restaurarlo al deshacer.
     *
     * @param controladorCatalogo el controlador del catálogo (no nulo)
     * @param idProducto          el identificador del producto a eliminar
     */
    public ComandoEliminarProductoMenu(
            ControladorCatalogo controladorCatalogo,
            int idProducto
    ) {
        if (controladorCatalogo == null) {
            throw new IllegalArgumentException(
                    "El controlador del catálogo no puede ser nulo"
            );
        }

        Producto productoRegistrado =
                controladorCatalogo.obtenerProducto(idProducto);

        this.controladorCatalogo = controladorCatalogo;
        this.productoEliminado = copiarProducto(productoRegistrado);
    }

    private Producto copiarProducto(Producto producto) {
        return new Producto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria()
        );
    }

    @Override
    public void ejecutar() {
        controladorCatalogo.eliminarProducto(
                productoEliminado.getId()
        );
    }

    @Override
    public void deshacer() {
        controladorCatalogo.agregarProducto(
                copiarProducto(productoEliminado)
        );
    }
}