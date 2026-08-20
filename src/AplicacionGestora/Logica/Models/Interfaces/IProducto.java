package AplicacionGestora.Logica.Models.Interfaces;

public abstract class IProducto {

    public enum categoriaProducto {
        BEBIDA,
        DESAYUNO,
        ALMUERZO,
        POSTRE,
        ACOMPANAMIENTO
    }
    public abstract void setId (int id);
    public abstract void setNombre (String nombre);
    public abstract void setPrecio (double precio);
    public abstract void  setDireccionImg (String direccionImg);

    public abstract int getId ();
    public abstract String getNombre ();
    public abstract double getPrecio ();
    public abstract String getDireccionImg ();
    public abstract String toString ();

}
