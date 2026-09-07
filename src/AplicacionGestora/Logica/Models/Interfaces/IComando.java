package AplicacionGestora.Logica.Models.Interfaces;

/**
 * Contrato para una acción deshacible (patrón Command).
 */
public interface IComando {

    /**
     * Ejecuta la acción definida por el comando.
     */
    void ejecutar();

    /**
     * Revierte el resultado de {@link #ejecutar()}.
     */
    void deshacer();
}