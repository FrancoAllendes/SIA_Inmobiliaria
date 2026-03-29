package main;

public class Departamento {
    private int numero;
    private double metrosCuadrados;
    private double precioBase;
    private boolean vendido;

    // Constructor
    public Departamento(int numero, double metrosCuadrados, double precioBase) {
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.precioBase = precioBase;
        this.vendido = false;
    }

    // --- GETTERS Y SETTERS  ---
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
}