package main;

public class DepartamentoEstandar extends Departamento {

    public DepartamentoEstandar(int numero, double metrosCuadrados, double precioBase) {
        super(numero, metrosCuadrados, precioBase);
    }

    // --- Sobreescritura 1 ---
    @Override
    public double calcularPrecioFinal(int nivelDemanda) {
        double aumento = nivelDemanda * 50.0;
        if (aumento > 2000.0) { 
        	aumento = 2000.0; 
        }
        return getPrecioBase() + aumento;
    }
}