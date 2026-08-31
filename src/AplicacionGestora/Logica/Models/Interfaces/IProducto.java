package AplicacionGestora.Logica.Models.Interfaces;
import AplicacionGestora.Logica.Models.CategoriaProducto;

public interface IProducto {
    void setPrecio (double precio);

    int getId ();
    String getNombre ();
    double getPrecio ();
    CategoriaProducto getCategoria();
}
