package AplicacionGestora.Persistencia;

import AplicacionGestora.Logica.Models.Interfaces.IProducto;
import AplicacionGestora.Logica.Structures.LineaPedido;
import AplicacionGestora.Logica.Structures.Pedido;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Guarda pedidos como archivos CSV de recibo, uno por pedido,
 * en la carpeta {@code datos/pedidos}.
 */
public class PersistenciaPedido {

    private final Path carpetaPedidos = Paths.get("datos", "pedidos");

    /**
     * Genera y guarda el recibo CSV de un pedido.
     *
     * @param pedido el pedido a guardar (no nulo y con al menos una línea)
     * @throws IllegalArgumentException si el pedido es nulo o no tiene productos
     * @throws IllegalStateException    si no se pudo escribir el archivo
     */
    public void guardarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException(
                    "El pedido no puede ser nulo"
            );
        }

        List<LineaPedido> lineas = pedido.getLineas();

        if (lineas.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se puede guardar un pedido sin productos"
            );
        }

        String fecha = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        ).format(pedido.getFecha());

        String datosPedido = pedido.getId() + ","
                + escaparTexto(fecha) + ","
                + escaparTexto(pedido.getEstado()) + ","
                + pedido.calcularTotal() + ",";

        StringBuilder contenido = new StringBuilder();
        contenido.append(
                "pedido_id,fecha,estado,total,producto_id,nombre,"
                        + "categoria,precio_unitario,cantidad,subtotal\r\n"
        );

        for (LineaPedido linea : lineas) {
            IProducto producto = linea.getProducto();

            contenido.append(datosPedido)
                    .append(producto.getId()).append(",")
                    .append(escaparTexto(producto.getNombre())).append(",")
                    .append(escaparTexto(producto.getCategoria().name())).append(",")
                    .append(producto.getPrecio()).append(",")
                    .append(linea.getCantidad()).append(",")
                    .append(linea.calcularSubtotal()).append("\r\n");
        }

        Path archivoTemporal = null;

        try {
            Files.createDirectories(carpetaPedidos);
            archivoTemporal = Files.createTempFile(
                    carpetaPedidos,
                    "pedido_" + pedido.getId() + "_",
                    ".tmp"
            );

            try (BufferedWriter escritor = Files.newBufferedWriter(
                    archivoTemporal,
                    StandardCharsets.UTF_8
            )) {
                escritor.write(contenido.toString());
            }

            String nombreArchivo = archivoTemporal.getFileName()
                    .toString().replace(".tmp", ".csv");

            Files.move(
                    archivoTemporal,
                    archivoTemporal.resolveSibling(nombreArchivo)
            );
        } catch (IOException | SecurityException excepcion) {
            if (archivoTemporal != null) {
                try {
                    Files.deleteIfExists(archivoTemporal);
                } catch (IOException | SecurityException errorLimpieza) {
                    excepcion.addSuppressed(errorLimpieza);
                }
            }

            throw new IllegalStateException(
                    "No se pudo guardar el pedido en CSV",
                    excepcion
            );
        }
    }

    // Protege textos que contienen comas, comillas o saltos de línea en el CSV.
    private String escaparTexto(String texto) {
        return "\"" + texto.replace("\"", "\"\"") + "\"";
    }

}