package main;

public abstract class Propiedad {
    private int numero;
    private double metrosCuadrados;
    private double precioBase;
    private int habitaciones;
    private int banos;

    public Propiedad(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos) {
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.precioBase = precioBase;
        this.habitaciones = habitaciones;
        this.banos = banos;
    }

    public void modificarPrecio(double nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }

    public void modificarPrecio(double porcentajeAumento, boolean esPorcentaje) {
        if (esPorcentaje) {
            this.precioBase = this.precioBase + (this.precioBase * (porcentajeAumento / 100));
        }
    }

    // Método que los hijos deberán personalizar
    public abstract double calcularPrecioFinal(int nivelDemanda);

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
    public int getHabitaciones() { 
    	return habitaciones; 
    }
    public void setHabitaciones(int habitaciones) { 
    	this.habitaciones = habitaciones; 
    }
    public int getBanos() { 
    	return banos; 
    }
    public void setBanos(int banos) { 
    	this.banos = banos; 
    }
}