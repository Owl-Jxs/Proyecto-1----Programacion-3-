package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

// Representa la acción reversible de registrar un producto en el menú.
public class ComandoCrearProductoMenu implements IComando {

    private final ControladorCatalogo controladorCatalogo;
    private final Producto producto;
    private boolean ejecutado;

    public ComandoCrearProductoMenu(
            ControladorCatalogo controladorCatalogo,
            Producto producto
    ) {
        if (controladorCatalogo == null) {
            throw new IllegalArgumentException(
                    "El controlador del catálogo no puede ser nulo"
            );
        }

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo"
            );
        }

        this.controladorCatalogo = controladorCatalogo;
        this.producto = producto;
        this.ejecutado = false;
    }

    @Override
    public void ejecutar() {
        if (ejecutado) {
            throw new IllegalStateException(
                    "El comando ya fue ejecutado"
            );
        }

        controladorCatalogo.agregarProducto(producto);
        ejecutado = true;
    }

    @Override
    public void deshacer() {
        if (!ejecutado) {
            throw new IllegalStateException(
                    "El comando todavía no ha sido ejecutado"
            );
        }

        controladorCatalogo.eliminarProducto(producto.getId());
        ejecutado = false;
    }
}
