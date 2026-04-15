package main;

/**
 * Excepcion que se activa cunado se ingresa un codigo de un proyecto y este ya existe
 * * @author Franco Allendes
 */

public class ProyectoDuplicadoException extends Exception {
    public ProyectoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}