package main;

/** 
 * Clase abstracta que es el ejemplo general de una propiedad dentro del sistema inmobiliario
 * Funciona como la base de los distintos tipos de propiedades como Casas y Departamentos 
 * * @author Franco Allendes
 */

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

    /**
     * Calcula el precio final de la venta de una propiedad basnadose en la demanda
     * Debe ser implementado obligatoriamente por las clases hijas
     * @param nivelDemanda El nivel de demanda actual del proyecto (int)
     * @return El valor total calculado para la venta de la propiedad
     */
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