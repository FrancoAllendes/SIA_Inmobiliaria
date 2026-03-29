package main;

public class Penthouse extends Departamento {

    public Penthouse(int numero, double metrosCuadrados, double precioBase) {
        super(numero, metrosCuadrados, precioBase);
    }

    // --- Sobreescritura 2 ---
    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        return getPrecioBase() + (nivelDemanda * 300.0);
    }
}