package AplicacionGestora.Logica.Models;

import AplicacionGestora.Logica.Models.Interfaces.IProducto;

/**
 * Representa un producto del menú: su identificador, nombre, precio y categoría.
 * El id, el nombre y la categoría son inmutables; el precio puede modificarse.
 */
public class Producto implements IProducto {

    private final int id;
    private final String nombre;
    private double precio;
    private final CategoriaProducto categoria;

    private static void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar vacío");
        }
    }

    private static void validarPrecio(double precio) {
        if (precio < 0 || Double.isNaN(precio) || Double.isInfinite(precio)) {
            throw new IllegalArgumentException("El precio debe ser un número finito y no negativo");
        }
    }

    private static void validarCategoria(CategoriaProducto categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
    }

    /**
     * Crea un producto validando todos sus atributos.
     *
     * @param id        el identificador del producto (no negativo)
     * @param nombre    el nombre del producto (no nulo ni vacío)
     * @param precio    el precio del producto (no negativo y finito)
     * @param categoria la categoría del producto (no nula)
     * @throws IllegalArgumentException si algún atributo no es válido
     */
    public Producto(int id, String nombre, double precio, CategoriaProducto categoria) {
        validarId(id);
        validarNombre(nombre);
        validarPrecio(precio);
        validarCategoria(categoria);

        this.id = id;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override
    public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public CategoriaProducto getCategoria() {
        return categoria;
    }

    /**
     * Crea una copia independiente del producto (snapshot).
     *
     * @return un nuevo producto con los mismos valores
     */
    public Producto copiar() {
        return new Producto(id, nombre, precio, categoria);
    }

    @Override
    public String toString() {
        return "Producto -> ID: " + id
                + " | Nombre: " + nombre
                + " | Precio: " + precio
                + " | Categoría: " + categoria;
    }
}