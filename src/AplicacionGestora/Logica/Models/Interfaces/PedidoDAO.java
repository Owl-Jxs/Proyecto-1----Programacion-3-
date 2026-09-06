package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Structures.Pedido;

import java.util.List;

/**
 * Contrato de acceso a datos para el historial de pedidos.
 */
public interface PedidoDAO {

    /**
     * Guarda un pedido en el historial.
     *
     * @param pedido el pedido a guardar
     */
    void guardarPedido(Pedido pedido);

    /**
     * @return todos los pedidos registrados en el historial
     */
    List<Pedido> obtenerHistorialPedidos();

    /**
     * Busca un pedido por su identificador.
     *
     * @param id el identificador del pedido
     * @return el pedido si existe, {@code null} en caso contrario
     */
    Pedido obtenerPedidoPorId(int id);
}