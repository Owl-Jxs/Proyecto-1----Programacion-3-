package AplicacionGestora.Logica.Models.Interfaces;

public abstract class IComando {
    public abstract void ejecutar ();
    public abstract void deshacer ();
}
