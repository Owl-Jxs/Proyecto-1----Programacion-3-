package AplicacionGestora.Persistencia;

import AplicacionGestora.Logica.Models.Catalogo;
import AplicacionGestora.Logica.Models.Producto;
import AplicacionGestora.Logica.Models.CategoriaProducto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Persiste el catálogo de productos en un archivo CSV.
 */
public class PersistenciaCatalogo {

    private static final Path RUTA_ARCHIVO = Paths.get("datos", "catalogo", "productos.csv");

    /**
     * Carga los productos almacenados en el archivo CSV.
     * Si el archivo no existe, devuelve un catálogo vacío.
     *
     * @return el catálogo con los productos leídos del archivo
     */
    public Catalogo cargar() {
        Catalogo catalogo = new Catalogo();

        if (!Files.exists(RUTA_ARCHIVO)) {
            return catalogo;
        }

        try {
            for (String linea : Files.readAllLines(RUTA_ARCHIVO, StandardCharsets.UTF_8)) {

                // Ignora la cabecera del archivo
                if (linea.equals("id,nombre,precio,categoria")) {
                    continue;
                }

                // Ignora líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);

                CategoriaProducto categoria = CategoriaProducto.valueOf(datos[3]);

                Producto producto = new Producto(id, nombre, precio, categoria);

                catalogo.agregarProducto(producto);
            }
        } catch (IOException | SecurityException e) {
            System.err.println("Error al cargar el catálogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al interpretar los datos del catálogo: " + e.getMessage());
        }
        return catalogo;
    }

    /**
     * Guarda todos los productos del catálogo en un archivo CSV.
     * El archivo anterior se reemplaza utilizando un archivo temporal para
     * evitar dejar un archivo incompleto.
     *
     * @param catalogo el catálogo a guardar
     * @throws IllegalStateException si no se pudo escribir el archivo
     */
    public void guardar(Catalogo catalogo) {
        Path archivoTemporal = null;

        try {
            Path directorio = RUTA_ARCHIVO.getParent();

            if (directorio != null) {
                Files.createDirectories(directorio);
            }

            archivoTemporal = Files.createTempFile(directorio, "productos", ".tmp");

            StringBuilder contenido = new StringBuilder();

            // Cabecera del CSV
            contenido.append("id,nombre,precio,categoria\r\n");

            // Agrega cada producto al contenido
            for (Producto producto : catalogo.listarProductos()) {

                contenido.append(producto.getId())
                        .append(",")
                        .append(producto.getNombre())
                        .append(",")
                        .append(producto.getPrecio())
                        .append(",")
                        .append(producto.getCategoria().name())
                        .append("\r\n");
            }

            try (BufferedWriter escritor = Files.newBufferedWriter(archivoTemporal, StandardCharsets.UTF_8)) {
                escritor.write(contenido.toString());
            }

            // Reemplaza el archivo original
            Files.move(archivoTemporal, RUTA_ARCHIVO, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            archivoTemporal = null;

        } catch (IOException | SecurityException e) {
            throw new IllegalStateException("No se pudo guardar el catálogo en CSV", e);
        } finally {
            // Si ocurrió un error, elimina el archivo temporal
            if (archivoTemporal != null) {
                try {
                    Files.deleteIfExists(archivoTemporal);
                } catch (IOException | SecurityException e) {
                    System.err.println("No se pudo eliminar el archivo temporal: " + e.getMessage());
                }
            }
        }
    }
}