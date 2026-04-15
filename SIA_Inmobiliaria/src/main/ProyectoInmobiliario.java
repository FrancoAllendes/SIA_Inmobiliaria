package main;
import java.util.ArrayList;

/**
 * Representa un proyecto inmobiliario que agrupa y gestiona una lista de propiedades
 * Cuenta con la logica para almacenar Casas y Departamentos de forma dinamica
 * * @author Franco Allendes
 */

public class ProyectoInmobiliario {
    private String codigo;
    private String nombre;
    private int nivelDemanda;
    private ArrayList<Propiedad> listaPropiedades;

    public ProyectoInmobiliario(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivelDemanda = 0; 
        this.listaPropiedades = new ArrayList<>(); 
    }
    
    /**
     * Agrega una propiedad ya creada a la lista de proyecto
     * @param p El objeto de tipo propiedad a insertar en la lista
     */
    public void agregarPropiedad(Propiedad p) {
        this.listaPropiedades.add(p);
    }
    
    /**
     * Sobrecarga del metodo agregarPropiedad estandar ingresando los atributos manualmente
     * * @param num Numero del departamento / casa
     * @param mts Metros cuadrados
     * @param precio Precio base inicial
     * @param hab Cantidad de habitaciones
     * @param banos cantidad de baños
     */
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