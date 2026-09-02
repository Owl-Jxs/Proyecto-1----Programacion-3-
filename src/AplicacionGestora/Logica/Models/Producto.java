package AplicacionGestora.Logica.Models;
import AplicacionGestora.Logica.Models.Interfaces.IProducto;

// Clase que representa un producto en el sistema de gestión. 
// Implementa la interfaz IProducto.
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

    // Constructor de la clase Producto, recibe nombre, precio y categoría del producto.
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

    // Para settear el precio del producto.
    @Override public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }
    
    // Obtener el id.
    @Override public int getId() { return id; }
    // Obtener el nombre.
    @Override public String getNombre() { return nombre; }
    // Obtener el precio.
    @Override public double getPrecio() { return precio; }
    // Obtener la categoría del producto.
    @Override public CategoriaProducto getCategoria() { return categoria; }

    // Metodo toString para representar el producto como una cadena de texto.
    @Override
    public String toString() {
        return "Producto Descripcion -> ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio  + " | Categoria: " + categoria;
    }
}

