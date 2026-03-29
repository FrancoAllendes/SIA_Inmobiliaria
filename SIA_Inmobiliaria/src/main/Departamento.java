package main;

public class Departamento {
    private int numero;
    private double metrosCuadrados;
    private double precioBase;
    private boolean vendido;

    public Departamento(int numero, double metrosCuadrados, double precioBase) {
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.precioBase = precioBase;
        this.vendido = false;
    }

    // --- Sobrecarga en Departamento ---
    public void modificarPrecio(double nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }

    public void modificarPrecio(double porcentajeAumento, boolean esPorcentaje) {
        if (esPorcentaje) {
            this.precioBase = this.precioBase + (this.precioBase * (porcentajeAumento / 100));
        }
    }

    // --- Método a sobreescribir ---
    public double calcularPrecioFinal(int nivelDemanda) {
        return this.precioBase + (nivelDemanda * 50.0); 
    }

    // Getters y Setters
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