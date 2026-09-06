package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Producto;

/**
 * Representa la acción de modificar un producto del menú.
 */
public class ComandoModificarProductoMenu implements IComando {

    private ControladorCatalogo controladorCatalogo;
    private Producto productoAnterior;
    private Producto productoModificado;

    /**
     * Guarda una copia del producto antes de modificarlo para poder restaurarlo al deshacer.
     *
     * @param controladorCatalogo el controlador del catálogo (no nulo)
     * @param productoModificado  el producto con los datos actualizados (no nulo)
     */
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
        controladorCatalogo.modificarProducto(productoModificado);
    }

    @Override
    public void deshacer() {
        controladorCatalogo.modificarProducto(productoAnterior);
    }

}