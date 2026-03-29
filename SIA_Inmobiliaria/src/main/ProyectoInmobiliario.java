package main;

import java.util.ArrayList;

public class ProyectoInmobiliario {
    private String codigo;
    private String nombre;
    private int nivelDemanda;
    // 2da Colección anidada
    private ArrayList<Departamento> listaDepartamentos; 

    // Constructor
    public ProyectoInmobiliario(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivelDemanda = 0; // Inicia con demanda cero
        this.listaDepartamentos = new ArrayList<>(); // Inicializamos la colección
    }

    // --- GETTERS Y SETTERS ---
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