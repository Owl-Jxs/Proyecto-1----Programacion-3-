package AplicacionGestora.Logica.DAO;

import AplicacionGestora.Logica.Models.Interfaces.PedidoDAO;
import AplicacionGestora.Logica.Structures.Pedido;
import AplicacionGestora.Persistencia.PersistenciaPedido;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

// Historial de pedidos en memoria con guardado de recibos CSV.
public class PedidoDAOImpl implements PedidoDAO {
    // LinkedHashMap por consistencia con catalogo.
    // Aparte busqueda por id O(1). La clave es id y no existirian pedidos duplicados
    // Orden de insercion se preserva, al obtenerlos se devuelve en mismo orden en que se guardaron.
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