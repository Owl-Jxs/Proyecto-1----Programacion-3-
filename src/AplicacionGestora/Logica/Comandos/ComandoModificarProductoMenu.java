package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

// Representa la acción reversible de modificar un producto del menú.
public class ComandoModificarProductoMenu implements IComando {

    private final ControladorCatalogo controladorCatalogo;
    private final Producto productoAnterior;
    private final Producto productoModificado;
    private boolean ejecutado;

    public ComandoModificarProductoMenu(
            ControladorCatalogo controladorCatalogo,
            Producto productoModificado
    ) {
        if (controladorCatalogo == null) {
            throw new IllegalArgumentException(
                    "El controlador del catálogo no puede ser nulo"
            );
        }

        if (productoModificado == null) {
            throw new IllegalArgumentException(
                    "El producto modificado no puede ser nulo"
            );
        }

        Producto productoRegistrado =
                controladorCatalogo.obtenerProducto(
                        productoModificado.getId()
                );

        this.controladorCatalogo = controladorCatalogo;
        this.productoAnterior = copiarProducto(productoRegistrado);
        this.productoModificado = copiarProducto(productoModificado);
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

        controladorCatalogo.modificarProducto(productoModificado);
        ejecutado = true;
    }

    @Override
    public void deshacer() {
        if (!ejecutado) {
            throw new IllegalStateException(
                    "El comando todavía no ha sido ejecutado"
            );
        }

        controladorCatalogo.modificarProducto(productoAnterior);
        ejecutado = false;
    }
}
