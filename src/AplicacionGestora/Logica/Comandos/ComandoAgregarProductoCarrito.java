package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Controllers.ControladorPedido;
import AplicacionGestora.Logica.Models.Interfaces.IComando;
import AplicacionGestora.Logica.Models.Interfaces.IProducto;
import AplicacionGestora.Logica.Models.LineaPedido;
import AplicacionGestora.Logica.Models.Pedido;

// Representa la acción de agregar un producto al carrito.
public class ComandoAgregarProductoCarrito implements IComando {

    private Pedido pedido;
    private LineaPedido lineaPedido;

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
