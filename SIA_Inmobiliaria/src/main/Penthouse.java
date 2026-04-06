package main;

public class Penthouse extends Departamento {
    public Penthouse(int numero, double metrosCuadrados, double precioBase, int habitaciones, int banos) {
        super(numero, metrosCuadrados, precioBase, habitaciones, banos);
    }

    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        return getPrecioBase() + (nivelDemanda * 300.0);
    }
}