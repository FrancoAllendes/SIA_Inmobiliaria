package main;

public class DepartamentoEstandar extends Departamento {
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