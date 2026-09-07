package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorPedido;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Interfaces.IProducto;
import AplicacionGestora.Logica.Structures.LineaPedido;
import AplicacionGestora.Logica.Structures.Pedido;

/**
 * Representa la acción de agregar un producto al carrito (pedido actual).
 */
public class ComandoAgregarProductoCarrito implements IComando {

    private Pedido pedido;
    private LineaPedido lineaPedido;

    /**
     * @param controladorPedido el controlador del pedido (no nulo)
     * @param producto          el producto a agregar
     * @param cantidad          la cantidad del producto
     */
    public ComandoAgregarProductoCarrito(
            ControladorPedido controladorPedido,
            IProducto producto,
            int cantidad
    ) {
        if (controladorPedido == null) {
            throw new IllegalArgumentException(
                    "El controlador del pedido no puede ser nulo"
            );
        }

        this.pedido = controladorPedido.getPedidoActual();
        this.lineaPedido = new LineaPedido(
                producto,
                cantidad
        );
    }

    private void validarPedidoPendiente() {
        if (!"Pendiente".equals(pedido.getEstado())) {
            throw new IllegalStateException(
                    "Solo se puede modificar un pedido pendiente"
            );
        }
    }

    @Override
    public void ejecutar() {
        validarPedidoPendiente();
        pedido.agregarLinea(lineaPedido);
    }

    @Override
    public void deshacer() {
        validarPedidoPendiente();
        pedido.eliminarLinea(lineaPedido);
    }

}