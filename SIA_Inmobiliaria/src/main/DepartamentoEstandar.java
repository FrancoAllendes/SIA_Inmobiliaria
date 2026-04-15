package main;

/**
 * Representa la unidad Habitacional mas comun
 * Tiene una restrccion logica donde el aumento por demanda no puede superar el tope maximo de $2000
 * * @author Franco Allendes
 */

public class DepartamentoEstandar extends Departamento {
    
	/**
	 * Constructor para un DepartamentoEstandar
	 * * @param numero Numero del Departamento
	 * @param metrosCuadrados Metros cuadrados de la unidad
	 * @param precioBase Precio base del mercado
	 * @param habitaciones Numero de dormitorios
	 * @param banos Numero de baños
	 */
	
	public DepartamentoEstandar(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos) {
        super(numero, metrosCuadrados, precioBase, habitaciones, banos);
    }

    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        double aumento = nivelDemanda * 50.0;
        if (aumento > 2000.0) { aumento = 2000.0; }
        return getPrecioBase() + aumento;
    }
}