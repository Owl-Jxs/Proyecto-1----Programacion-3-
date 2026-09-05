package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Models.Interfaces.IComando;

import java.util.Stack;

// Administra las acciones que pueden deshacerse y rehacerse.
public class GestorAcciones {

    private Stack<IComando> pilaDeshacer;
    private Stack<IComando> pilaRehacer;

    public GestorAcciones() {
        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
    }

    public void ejecutarComando(IComando comando) {
        if (comando == null) {
            throw new IllegalArgumentException(
                    "El comando no puede ser nulo"
            );
        }

        comando.ejecutar();
        pilaDeshacer.push(comando);
        pilaRehacer.clear();
    }

    public boolean deshacer() {
        if (pilaDeshacer.isEmpty()) {
            return false;
        }

        IComando comando = pilaDeshacer.peek();
        comando.deshacer();

        pilaDeshacer.pop();
        pilaRehacer.push(comando);

        return true;
    }

    public boolean rehacer() {
        if (pilaRehacer.isEmpty()) {
            return false;
        }

        IComando comando = pilaRehacer.peek();
        comando.ejecutar();

        pilaRehacer.pop();
        pilaDeshacer.push(comando);

        return true;
    }

    public boolean puedeDeshacer() {
        return !pilaDeshacer.isEmpty();
    }

    public boolean puedeRehacer() {
        return !pilaRehacer.isEmpty();
    }

    public void limpiarHistorial() {
        pilaDeshacer.clear();
        pilaRehacer.clear();
    }
  
}
