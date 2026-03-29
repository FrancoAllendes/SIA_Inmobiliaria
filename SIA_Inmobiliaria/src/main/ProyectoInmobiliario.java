package main;

import java.util.ArrayList;

public class ProyectoInmobiliario {
    private String codigo;
    private String nombre;
    private int nivelDemanda;
    private ArrayList<Departamento> listaDepartamentos; 

    public ProyectoInmobiliario(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivelDemanda = 0; 
        this.listaDepartamentos = new ArrayList<>(); 
    }

    // --- Sobrecarga en ProyectoInmobiliario ---
    public void agregarDepartamento(Departamento depto) {
        this.listaDepartamentos.add(depto);
    }

    public void agregarDepartamento(int numero, double metros, double precio) {
        Departamento nuevo = new Departamento(numero, metros, precio);
        this.listaDepartamentos.add(nuevo);
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
    public ArrayList<Departamento> getListaDepartamentos() { 
    	return listaDepartamentos; 
    }
    public void setListaDepartamentos(ArrayList<Departamento> listaDepartamentos) { 
    	this.listaDepartamentos = listaDepartamentos; 
    }
}