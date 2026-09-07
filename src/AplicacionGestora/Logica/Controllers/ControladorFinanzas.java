package AplicacionGestora.Logica.Controllers;

import AplicacionGestora.Logica.Models.CategoriaProducto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControladorFinanzas {

    private static final String CARPETA_PEDIDOS = "datos/pedidos";
    private static final String ESTADO_PROCESADO = "Procesado";
    private File carpetaPedidos;

    public ControladorFinanzas() {
        this(new File(CARPETA_PEDIDOS));
    }

    public ControladorFinanzas(File carpetaPedidos) {
        this.carpetaPedidos = carpetaPedidos;
    }

    public double[] calcularIngresos(String periodo, Date fechaConsulta) {
        double[] ingresos = new double[CategoriaProducto.values().length];

        if (!carpetaPedidos.exists()) {
            return ingresos;
        }

        File[] archivos = carpetaPedidos.listFiles();
        if (archivos == null) {
            return ingresos;
        }

        for (File archivo : archivos) {
            if (archivo.getName().endsWith(".csv")) {
                leerArchivo(archivo, periodo, fechaConsulta, ingresos);
            }
        }

        return ingresos;
    }

    private void leerArchivo(
            File archivo,
            String periodo,
            Date fechaConsulta,
            double[] ingresos
    ) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            lector.readLine();
            String linea;

            while ((linea = lector.readLine()) != null) {
                sumarLinea(linea, periodo, fechaConsulta, ingresos);
            }
        } catch (IOException excepcion) {
            throw new IllegalStateException(
                    "No se pudo leer el archivo " + archivo.getName()
            );
        }
    }

    private void sumarLinea(
            String linea,
            String periodo,
            Date fechaConsulta,
            double[] ingresos
    ) {
        try {
            String[] datos = separarDatos(linea);

            if (datos.length < 10) {
                return;
            }

            Date fechaPedido = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
            ).parse(quitarComillas(datos[1]));
            String estado = quitarComillas(datos[2]);

            if (!ESTADO_PROCESADO.equals(estado)
                    || !estaEnPeriodo(fechaPedido, periodo, fechaConsulta)) {
                return;
            }

            CategoriaProducto categoria = CategoriaProducto.valueOf(
                    quitarComillas(datos[6])
            );
            double subtotal = Double.parseDouble(datos[9]);
            ingresos[categoria.ordinal()] += subtotal;
        } catch (ParseException | IllegalArgumentException excepcion) {
            // Una linea incompleta no debe impedir mostrar los otros pedidos.
        }
    }

    private boolean estaEnPeriodo(
            Date fechaPedido,
            String periodo,
            Date fechaConsulta
    ) {
        if ("Ultimo mes".equals(periodo)) {
            SimpleDateFormat formatoMes = new SimpleDateFormat("MMyyyy");
            return formatoMes.format(fechaPedido).equals(
                    formatoMes.format(fechaConsulta)
            );
        }

        SimpleDateFormat formatoDia = new SimpleDateFormat("ddMMyyyy");
        return formatoDia.format(fechaPedido).equals(
                formatoDia.format(fechaConsulta)
        );
    }

    private String[] separarDatos(String linea) {
        ArrayList<String> datos = new ArrayList<>();
        String datoActual = "";
        boolean dentroDeComillas = false;

        for (int indice = 0; indice < linea.length(); indice++) {
            char caracter = linea.charAt(indice);

            if (caracter == '"') {
                dentroDeComillas = !dentroDeComillas;
            } else if (caracter == ',' && !dentroDeComillas) {
                datos.add(datoActual);
                datoActual = "";
            } else {
                datoActual += caracter;
            }
        }

        datos.add(datoActual);
        return datos.toArray(new String[datos.size()]);
    }

    private String quitarComillas(String texto) {
        return texto.replace("\"", "");
    }
}
