package main;

public class PresupuestoInvalidoException extends Exception {
    public PresupuestoInvalidoException(String mensaje) {
        super(mensaje);
    }
}