package AplicacionGestora.Logica.Models;
import AplicacionGestora.Logica.Models.Interfaces.IProducto;

public class Producto extends IProducto {
    private int id;
    private String nombre;
    private double precio;
    private categoriaProducto categoria;

    public Producto(int id, String nombre, double precio, categoriaProducto categoria){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override public void setId(int id) { this.id = id; }
    @Override public void setNombre(String nombre) { this.nombre = nombre; }
    @Override public void setPrecio(double precio) { this.precio = precio; }
    @Override public void setCategoria(categoriaProducto categoria) { this.categoria = categoria; }

    @Override public int getId() { return id; }
    @Override public String getNombre() { return nombre; }
    @Override public double getPrecio() { return precio; }
    @Override public categoriaProducto getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + ", categoria=" + categoria.name() + "}";
    }
}

