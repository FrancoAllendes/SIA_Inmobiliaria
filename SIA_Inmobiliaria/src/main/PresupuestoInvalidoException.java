package main;

/**
 * Excepcion personalizada que se muestra cuando un usuario ingresa un presupuesto menor o igual a cero en el simulador
 * * @author Franco Allendes 
 */

public class PresupuestoInvalidoException extends Exception {
    public PresupuestoInvalidoException(String mensaje) {
        super(mensaje);
    }
}