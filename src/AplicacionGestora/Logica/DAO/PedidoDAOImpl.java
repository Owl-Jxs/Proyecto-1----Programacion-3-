package AplicacionGestora.Logica.DAO;

import AplicacionGestora.Logica.Models.Interfaces.PedidoDAO;
import AplicacionGestora.Logica.Structures.Pedido;
import AplicacionGestora.Persistencia.PersistenciaPedido;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Historial de pedidos en memoria ({@link LinkedHashMap} indexado por id)
 * con guardado de recibos en CSV.
 */
public class PedidoDAOImpl implements PedidoDAO {

    private final LinkedHashMap<Integer, Pedido> pedidos;
    private final PersistenciaPedido persistenciaPedido;

    public PedidoDAOImpl() {
        pedidos = new LinkedHashMap<>();
        persistenciaPedido = new PersistenciaPedido();
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        persistenciaPedido.guardarPedido(pedido);
        pedidos.put(pedido.getId(), pedido);
    }

    @Override
    public List<Pedido> obtenerHistorialPedidos() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public Pedido obtenerPedidoPorId(int id) {
        return pedidos.get(id);
    }

}