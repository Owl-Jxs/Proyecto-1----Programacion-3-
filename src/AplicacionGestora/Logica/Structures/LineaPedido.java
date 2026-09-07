package AplicacionGestora.Logica.Structures;

import AplicacionGestora.Logica.Models.Interfaces.IProducto;

/**
 * Representa un producto y su cantidad dentro de un pedido.
 */
public class LineaPedido {

    private IProducto producto;
    private int cantidad;

    /**
     * Crea una línea de pedido.
     *
     * @param producto el producto (no nulo)
     * @param cantidad la cantidad (mayor que cero)
     * @throws IllegalArgumentException si el producto es nulo o la cantidad no es positiva
     */
    public LineaPedido(IProducto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo"
            );
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Calcula el subtotal multiplicando el precio unitario por la cantidad.
     *
     * @return el subtotal de la línea
     */
    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public IProducto getProducto() {
        return producto;
    }
}