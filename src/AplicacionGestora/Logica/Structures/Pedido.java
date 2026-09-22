package AplicacionGestora.Logica.Structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa un pedido y las líneas de productos que lo componen.
 */
public class Pedido {

    private final int id;
    private final Date fecha;
    private final List<LineaPedido> lineas;
    private String estado;

    /**
     * Crea un pedido con estado inicial "Pendiente".
     *
     * @param id    el identificador del pedido (no negativo)
     * @param fecha la fecha del pedido (no nula)
     * @throws IllegalArgumentException si el id es negativo o la fecha es nula
     */
    public Pedido(int id, Date fecha) {
        if (id < 0) {
            throw new IllegalArgumentException(
                    "El ID no puede ser negativo"
            );
        }

        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser nula"
            );
        }

        this.id = id;
        this.fecha = new Date(fecha.getTime());
        this.lineas = new ArrayList<>();
        this.estado = "Pendiente";
    }

    /**
     * Agrega una línea de producto al pedido.
     *
     * @param linea la línea a agregar (no nula)
     */
    public void agregarLinea(LineaPedido linea) {
        if (linea == null) {
            throw new IllegalArgumentException(
                    "La línea del pedido no puede ser nula"
            );
        }

        lineas.add(linea);
    }

    /**
     * Elimina una línea del pedido.
     *
     * @param linea la línea a eliminar (no nula y perteneciente al pedido)
     * @throws IllegalArgumentException si la línea es nula o no pertenece al pedido
     */
    public void eliminarLinea(LineaPedido linea) {
        if (linea == null) {
            throw new IllegalArgumentException(
                    "La línea del pedido no puede ser nula"
            );
        }

        if (!lineas.remove(linea)) {
            throw new IllegalArgumentException(
                    "La línea no pertenece al pedido"
            );
        }
    }

    /**
     * Calcula el total del pedido sumando el subtotal de cada línea.
     *
     * @return el total del pedido
     */
    public double calcularTotal() {
        double total = 0;

        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }

        return total;
    }

    public int getId() {
        return id;
    }

    /**
     * @return una copia defensiva de la fecha del pedido
     */
    public Date getFecha() {
        return new Date(fecha.getTime());
    }

    public String getEstado() {
        return estado;
    }

    /**
     * Asigna un nuevo estado al pedido.
     *
     * @param estado el estado del pedido (no nulo ni vacío)
     */
    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El estado no puede ser nulo ni estar vacío"
            );
        }

        this.estado = estado.trim();
    }

    /**
     * @return la lista de líneas del pedido
     */
    public final List<LineaPedido> getLineas() {
        return lineas;
    }

}