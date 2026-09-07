package AplicacionGestora.Logica.Controllers;

import AplicacionGestora.Logica.Models.Interfaces.IProducto;
import AplicacionGestora.Logica.Models.Interfaces.PedidoDAO;
import AplicacionGestora.Logica.Structures.LineaPedido;
import AplicacionGestora.Logica.Structures.Pedido;

import java.util.Date;

/**
 * Administra el pedido actual y su almacenamiento en el historial.
 */
public class ControladorPedido {

    private PedidoDAO pedidoDAO;
    private Pedido pedidoActual;

    /**
     * @param pedidoDAO el DAO del historial de pedidos (no nulo)
     * @throws IllegalArgumentException si el DAO es nulo
     */
    public ControladorPedido(PedidoDAO pedidoDAO) {
        if (pedidoDAO == null) {
            throw new IllegalArgumentException(
                    "El pedidoDAO no puede ser nulo"
            );
        }

        this.pedidoDAO = pedidoDAO;
        iniciarNuevoPedido();
    }

    private int generarNuevoID() {
        int nuevoID = 1;

        for (Pedido pedido : pedidoDAO.obtenerHistorialPedidos()) {
            if (pedido.getId() >= nuevoID) {
                nuevoID = pedido.getId() + 1;
            }
        }

        return nuevoID;
    }

    private void iniciarNuevoPedido() {
        pedidoActual = new Pedido(
                generarNuevoID(),
                new Date()
        );
    }

    private void validarPedidoConLineas() {
        if (pedidoActual.getLineas().isEmpty()) {
            throw new IllegalStateException(
                    "El pedido no contiene productos"
            );
        }
    }

    /**
     * Agrega una línea de producto al pedido actual.
     *
     * @param producto el producto a agregar
     * @param cantidad la cantidad del producto
     */
    public void agregarLineaAlPedido(
            IProducto producto,
            int cantidad
    ) {
        LineaPedido linea = new LineaPedido(
                producto,
                cantidad
        );

        pedidoActual.agregarLinea(linea);
    }

    /**
     * Procesa el pedido actual: lo marca como "Procesado", lo guarda en el
     * historial y comienza un pedido nuevo.
     *
     * @throws IllegalStateException si el pedido no contiene productos
     */
    public void procesarPedidoTotal() {
        validarPedidoConLineas();

        String estadoAnterior = pedidoActual.getEstado();
        pedidoActual.setEstado("Procesado");

        try {
            pedidoDAO.guardarPedido(pedidoActual);
        } catch (RuntimeException excepcion) {
            pedidoActual.setEstado(estadoAnterior);
            throw excepcion;
        }

        iniciarNuevoPedido();
    }

    /**
     * Cancela el pedido actual: lo marca como "Cancelado", lo guarda en el
     * historial y comienza un pedido nuevo.
     *
     * @throws IllegalStateException si el pedido no contiene productos
     */
    public void cancelarPedido() {
        validarPedidoConLineas();

        String estadoAnterior = pedidoActual.getEstado();
        pedidoActual.setEstado("Cancelado");

        try {
            pedidoDAO.guardarPedido(pedidoActual);
        } catch (RuntimeException excepcion) {
            pedidoActual.setEstado(estadoAnterior);
            throw excepcion;
        }

        iniciarNuevoPedido();
    }

    /**
     * @return el pedido que se está armando actualmente
     */
    public Pedido getPedidoActual() {
        return pedidoActual;
    }

}