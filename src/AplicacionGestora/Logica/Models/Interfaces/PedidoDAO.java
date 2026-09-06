package AplicacionGestora.Logica.Models.Interfaces;

import AplicacionGestora.Logica.Structures.Pedido;
import java.util.List;

public interface PedidoDAO {

    /*Guarda un pedido en el historial*/
    void guardarPedido(Pedido pedido);

    /*Obtiene todos los pedidos registrados en el historial*/
    List<Pedido> obtenerHistorialPedidos();

    /*Busca un pedido utilizando su id*/
    Pedido obtenerPedidoPorId(int id);
}