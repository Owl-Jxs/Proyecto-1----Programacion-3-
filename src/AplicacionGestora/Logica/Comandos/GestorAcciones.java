package AplicacionGestora.Logica.Comandos;

import AplicacionGestora.Logica.Models.Interfaces.IComando;

import java.util.Stack;

/**
 * Administra las acciones ejecutadas que pueden deshacerse y rehacerse
 * mediante dos pilas (patrón Command).
 */
public class GestorAcciones {

    private Stack<IComando> pilaDeshacer;
    private Stack<IComando> pilaRehacer;

    public GestorAcciones() {
        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
    }

    /**
     * Ejecuta un comando y lo registra para poder deshacerlo.
     *
     * @param comando el comando a ejecutar (no nulo)
     * @throws IllegalArgumentException si el comando es nulo
     */
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

    /**
     * Deshace la última acción ejecutada.
     *
     * @return {@code true} si había una acción para deshacer; {@code false} en caso contrario
     */
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

    /**
     * Rehace la última acción deshecha.
     *
     * @return {@code true} si había una acción para rehacer; {@code false} en caso contrario
     */
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

    /**
     * @return {@code true} si hay acciones que se pueden deshacer
     */
    public boolean puedeDeshacer() {
        return !pilaDeshacer.isEmpty();
    }

    /**
     * @return {@code true} si hay acciones que se pueden rehacer
     */
    public boolean puedeRehacer() {
        return !pilaRehacer.isEmpty();
    }

    /**
     * Vacía el historial de acciones deshechas y rehechas.
     */
    public void limpiarHistorial() {
        pilaDeshacer.clear();
        pilaRehacer.clear();
    }

}