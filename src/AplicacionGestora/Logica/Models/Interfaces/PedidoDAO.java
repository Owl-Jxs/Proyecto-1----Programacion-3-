package AplicacionGestora.Logica.Models.Interfaces;
import AplicacionGestora.Logica.Models.Pedido;
import java.util.List;

public interface PedidoDAO {
    void guardarPedido(Pedido pedido);

    List<Pedido> obtenerHistorialPedidos();

    Pedido obtenerPedidoPorId(int id);
}