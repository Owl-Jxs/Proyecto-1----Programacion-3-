package AplicacionGestora.Logica.Models;
import AplicacionGestora.Logica.Models.Interfaces.IProducto;

public class Producto implements IProducto {
    private int id;
    private String nombre;
    private double precio;
    private CategoriaProducto categoria;

    private void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    public Producto(int id, String nombre, double precio, CategoriaProducto categoria) {
        if (id < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser vacío o nulo");
        }
        validarPrecio(precio);
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria no puede ser nulo");
        }

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    @Override public int getId() { return id; }
    @Override public String getNombre() { return nombre; }
    @Override public double getPrecio() { return precio; }
    @Override public CategoriaProducto getCategoria() { return categoria; }
    @Override
    public String toString() {
        return "Producto Descripcion -> ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio  + " | Categoria: " + categoria;
    }
}

