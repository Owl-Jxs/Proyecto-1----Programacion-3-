package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Models.Pedido;
import java.util.ArrayList;

public interface PedidoDAO {

    ArrayList<Pedido> pedidos = new ArrayList<>();

    /*Guarda un pedido en el historial*/
    default void guardarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /*Obtiene todos los pedidos registrados en el historial*/
    default ArrayList<Pedido> obtenerHistorialPedidos() {
        return pedidos;
    }

    /*Busca un pedido utilizando su id*/
    default Pedido obtenerPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}