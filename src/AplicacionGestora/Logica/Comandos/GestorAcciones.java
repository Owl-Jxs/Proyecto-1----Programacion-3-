package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Models.Interfaces.IComando;

import java.util.ArrayDeque;
import java.util.Deque;

// Administra los comandos ejecutados mediante pilas de deshacer y rehacer.
public class GestorAcciones<T extends IComando> {

    private final Deque<T> pilaDeshacer;
    private final Deque<T> pilaRehacer;

    public GestorAcciones() {
        pilaDeshacer = new ArrayDeque<>();
        pilaRehacer = new ArrayDeque<>();
    }

    public void ejecutarComando(T comando) {
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

        T comando = pilaDeshacer.pop();

        try {
            comando.deshacer();
            pilaRehacer.push(comando);
            return true;
        } catch (RuntimeException excepcion) {
            pilaDeshacer.push(comando);
            throw excepcion;
        }
    }

    public boolean rehacer() {
        if (pilaRehacer.isEmpty()) {
            return false;
        }

        T comando = pilaRehacer.pop();

        try {
            comando.ejecutar();
            pilaDeshacer.push(comando);
            return true;
        } catch (RuntimeException excepcion) {
            pilaRehacer.push(comando);
            throw excepcion;
        }
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
