package main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase de utilidad encargada de la pesistencia de los datos del sistema
 * Permite leer y escribir la informacion en un archivo CSV tipo sistema bach
 * * @author Franco Allendes
 */

public class GestorArchivos {

    private static final String NOMBRE_ARCHIVO = "datos_inmobiliaria.csv";

    /**
     * Lee el arcivo CSV local y reconstruye los proyectos y propiedades en la memoria
     * * @param mapa El mapa principal del sistema donde se guardan los datos cargados
     */
    public static void cargarDatos(Map<String, ProyectoInmobiliario> mapa) {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("No se encontró archivo de guardado previo. Se iniciará un sistema en blanco.");
            return;
        }

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";"); // Separamos los datos por punto y coma

                if (partes[0].equals("PROYECTO")) {
                    // Formato: PROYECTO;Codigo;Nombre;Demanda
                    ProyectoInmobiliario p = new ProyectoInmobiliario(partes[1], partes[2]);
                    p.setNivelDemanda(Integer.parseInt(partes[3]));
                    mapa.put(p.getCodigo(), p);

                } else if (partes[0].equals("PROPIEDAD")) {
                    // Formato: PROPIEDAD;CodProyecto;Tipo;Numero;Mts;Precio;Hab;Banos;TienePatio
                    String codProy = partes[1];
                    String tipo = partes[2];
                    int num = Integer.parseInt(partes[3]);
                    double mts = Double.parseDouble(partes[4]);
                    double precio = Double.parseDouble(partes[5]);
                    int hab = Integer.parseInt(partes[6]);
                    int banos = Integer.parseInt(partes[7]);
                    boolean patio = Boolean.parseBoolean(partes[8]);

                    Propiedad prop;
                    if (tipo.equals("Casa")) {
                        prop = new Casa(num, mts, precio, hab, banos, patio);
                    } else if (tipo.equals("Penthouse")) {
                        prop = new Penthouse(num, mts, precio, hab, banos);
                    } else {
                        prop = new DepartamentoEstandar(num, mts, precio, hab, banos);
                    }

                    // Si el proyecto existe en el mapa, le metemos la propiedad
                    if (mapa.containsKey(codProy)) {
                        mapa.get(codProy).agregarPropiedad(prop);
                    }
                }
            }
            System.out.println("¡Datos cargados exitosamente desde el archivo!");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // --- GUARDAR DATOS AL SALIR ---
    public static void guardarDatos(Map<String, ProyectoInmobiliario> mapa) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (ProyectoInmobiliario p : mapa.values()) {
                // Escribimos el proyecto
                escritor.println("PROYECTO;" + p.getCodigo() + ";" + p.getNombre() + ";" + p.getNivelDemanda());
                
                // Escribimos las propiedades de ese proyecto
                for (Propiedad prop : p.getListaPropiedades()) {
                    String tipo = "Estandar";
                    boolean patio = false;
                    
                    if (prop instanceof Casa) {
                        tipo = "Casa";
                        patio = ((Casa) prop).isTienePatio();
                    } else if (prop instanceof Penthouse) {
                        tipo = "Penthouse";
                    }

                    escritor.println("PROPIEDAD;" + p.getCodigo() + ";" + tipo + ";" + prop.getNumero() + ";"
                            + prop.getMetrosCuadrados() + ";" + prop.getPrecioBase() + ";"
                            + prop.getHabitaciones() + ";" + prop.getBanos() + ";" + patio);
                }
            }
            System.out.println("¡Datos guardados correctamente en " + NOMBRE_ARCHIVO + "!");
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}