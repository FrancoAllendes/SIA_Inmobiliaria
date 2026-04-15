package main;

/**
 * Representa un departamento de tipo lujo
 * Se caracteriza por tener mucha demanda
 * precio mas elevedao a un departamento comun
 * * @author Franco Allendes
 */

public class Penthouse extends Departamento {
    
	/**
	 * Constructor para un Penthouse
	 * utiliza la logica de la clase padre Departamento para la inicializacion de sus atributos 
	 * @param numero Numero del Penthouse
	 * @param metrosCuadrados Area total
	 * @param precioBase Precio inicial de lujo
	 * @param habitaciones Cantidad de habitaciones
	 * @param banos Cantidad de baños
	 */
	
	public Penthouse(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos) {
        super(numero, metrosCuadrados, precioBase, habitaciones, banos);
    }

    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        return getPrecioBase() + (nivelDemanda * 300.0);
    }
}