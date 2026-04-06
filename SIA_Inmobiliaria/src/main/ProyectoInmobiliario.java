package main;
import java.util.ArrayList;

public class ProyectoInmobiliario {
    private String codigo;
    private String nombre;
    private int nivelDemanda;
    private ArrayList<Propiedad> listaPropiedades; // Ahora acepta Casas y Deptos

    public ProyectoInmobiliario(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivelDemanda = 0; 
        this.listaPropiedades = new ArrayList<>(); 
    }

    public void agregarPropiedad(Propiedad p) {
        this.listaPropiedades.add(p);
    }

    public void agregarPropiedad(int num, double mts, double precio, int hab, int banos) {
        Propiedad nueva = new Departamento(num, mts, precio, hab, banos);
        this.listaPropiedades.add(nueva);
    }

    // Getters y Setters
    public String getCodigo() { 
    	return codigo; 
    }
    public void setCodigo(String codigo) { 
    	this.codigo = codigo; 
    }
    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }
    public int getNivelDemanda() { 
    	return nivelDemanda; 
    }
    public void setNivelDemanda(int nivelDemanda) {
    	this.nivelDemanda = nivelDemanda; 
    }
    public ArrayList<Propiedad> getListaPropiedades() { 
    	return listaPropiedades; 
    }
    public void setListaPropiedades(ArrayList<Propiedad> listaPropiedades) { 
    	this.listaPropiedades = listaPropiedades; 
    }
}