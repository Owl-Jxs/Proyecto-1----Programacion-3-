package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

// Representa la la acción de registrar un producto en el menú.
public class ComandoCrearProductoMenu implements IComando {

    private ControladorCatalogo controladorCatalogo;
    private Producto producto;

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
    }

    @Override
    public void ejecutar() {
        controladorCatalogo.agregarProducto(producto);
    }

    @Override
    public void deshacer() {
        controladorCatalogo.eliminarProducto(producto.getId());
    }
  
}
