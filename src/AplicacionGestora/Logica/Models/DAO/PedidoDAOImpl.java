package AplicacionGestora.Logica.Models.DAO;

import AplicacionGestora.Logica.Models.Interfaces.PedidoDAO;
import AplicacionGestora.Logica.Models.Pedido;

import java.util.ArrayList;
import java.util.List;

// Implementación en memoria del historial de pedidos.
public class PedidoDAOImpl implements PedidoDAO {

    private final List<Pedido> pedidos;

    public PedidoDAOImpl() {
        pedidos = new ArrayList<>();
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> obtenerHistorialPedidos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public Pedido obtenerPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }

        return null;
    }
  
}
