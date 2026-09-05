package AplicacionGestora.Logica.Models.DAO;

import AplicacionGestora.Logica.Models.Interfaces.PedidoDAO;
import AplicacionGestora.Logica.Models.Pedido;
import AplicacionGestora.Persistencia.PersistenciaPedido;

import java.util.ArrayList;
import java.util.List;

// Historial de pedidos en memoria con guardado de recibos CSV.
public class PedidoDAOImpl implements PedidoDAO {

    private final List<Pedido> pedidos;
    private final PersistenciaPedido persistenciaPedido;

    public PedidoDAOImpl() {
        pedidos = new ArrayList<>();
        persistenciaPedido = new PersistenciaPedido();
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        persistenciaPedido.guardarPedido(pedido);
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
