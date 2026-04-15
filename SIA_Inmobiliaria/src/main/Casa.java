package main;

/**
 * Representa una propiedad tipo Casa
 * En esta clase si se gestionan sus atributos especificos como si posee patio y su propia logica de precio
 * A diferencia de la clase departamento
 * * @author Franco Allendes
 */

public class Casa extends Propiedad {
    private boolean tienePatio;
    
    /**
     * Constructor para crear una nueva instancia de casa
     * @param numero Identificador numerico de la casa
     * @param metrosCuadrados Superficia total construida
     * @param precioBase Valor inicial antes de aplicar demanda o extras
     * @param habitaciones Cantidad de dormitorios
     * @param banos Cantidad de baños
     * @param tienePatio Indica si la propiedad tiene patio
     */
    public Casa(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos, boolean tienePatio) {
        super(numero, metrosCuadrados, precioBase, habitaciones, banos);
        this.tienePatio = tienePatio;
    }

    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        // Las casas valorizan distinto. El patio suma un plus.
        double aumento = nivelDemanda * 150.0;
        if (tienePatio) aumento += 500.0; 
        return getPrecioBase() + aumento;
    }

    public boolean isTienePatio() { 
    	return tienePatio; 
    }
    public void setTienePatio(boolean tienePatio) { 
    	this.tienePatio = tienePatio; 
    }
}