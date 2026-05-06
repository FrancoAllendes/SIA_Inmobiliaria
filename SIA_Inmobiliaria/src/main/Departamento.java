package main;

/**
 * Clase base para los distintos tipos de departamento
 * Hereda la clase propiedad y define el comportamiento general de una
 * * @author Franco Allendes
 */

/**
 * CAMBIOS CORRECION
 * Se define como clase abstracta para fortalecer el diseño de la jerarquía.
 * Esto evita la instanciación de un "Departamento" genérico, asegurando que solo 
 * se creen objetos de tipos específicos como DepartamentoEstandar o Penthouse, 
 * lo que garantiza la integridad del modelo de datos.
 */

public abstract class Departamento extends Propiedad {
	
	/**
	 * Constructor para la clase departamento
	 * @param numero Numero de Departamento
	 * @param metrosCuadrados Tamaño de la unidad
	 * @param precioBase Precio base de la lista
	 * @param habitaciones Numero de Habitaciones
	 * @param banos Numero de baños
	 */
	
    public Departamento(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos) {
        super(numero, metrosCuadrados, precioBase, habitaciones, banos);
    }

    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        return getPrecioBase() + (nivelDemanda * 50.0); 
    }
}