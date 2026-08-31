package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Models.Producto;
import java.util.ArrayList;

public interface PedidoDAO {

    ArrayList<Producto> pedidos = new ArrayList<>();

    /*Guarda un pedido en el historial*/
    default void guardarPedido(Producto pedido) {
        pedidos.add(pedido);
    }

    /*Obtiene todos los pedidos registrados en el historial*/
    default ArrayList<Producto> obtenerHistorialPedidos() {
        return pedidos;
    }

    /*Busca un pedido utilizando su identificador*/
    default Producto obtenerPedidoPorId(int id) {
        for (Producto pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}