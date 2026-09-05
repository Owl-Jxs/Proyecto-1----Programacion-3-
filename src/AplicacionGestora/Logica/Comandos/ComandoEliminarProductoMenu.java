package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

// Representa la acción reversible de eliminar un producto del menú.
public class ComandoEliminarProductoMenu implements IComando {

    private final ControladorCatalogo controladorCatalogo;
    private final Producto productoEliminado;
    private boolean ejecutado;

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
        this.ejecutado = false;
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
        if (ejecutado) {
            throw new IllegalStateException(
                    "El comando ya fue ejecutado"
            );
        }

        controladorCatalogo.eliminarProducto(
                productoEliminado.getId()
        );

        ejecutado = true;
    }

    @Override
    public void deshacer() {
        if (!ejecutado) {
            throw new IllegalStateException(
                    "El comando todavía no ha sido ejecutado"
            );
        }

        controladorCatalogo.agregarProducto(
                copiarProducto(productoEliminado)
        );

        ejecutado = false;
    }
}
