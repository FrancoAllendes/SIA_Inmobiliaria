package main;

public class Casa extends Propiedad {
    private boolean tienePatio;

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