package AplicacionGestora.Logica.Structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Representa un pedido y las líneas de productos que lo componen.
public class Pedido {

    private final int id;
    private final Date fecha;
    private final List<LineaPedido> lineas;
    private String estado;

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

    public void agregarLinea(LineaPedido linea) {
        if (linea == null) {
            throw new IllegalArgumentException(
                    "La línea del pedido no puede ser nula"
            );
        }

        lineas.add(linea);
    }

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

    public Date getFecha() {
        return new Date(fecha.getTime());
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El estado no puede ser nulo ni estar vacío"
            );
        }

        this.estado = estado.trim();
    }

    public final List<LineaPedido> getLineas() {
        return lineas;
    }

}
