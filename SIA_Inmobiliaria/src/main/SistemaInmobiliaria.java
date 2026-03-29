package main;

import java.util.HashMap;
import java.util.Map;


public class SistemaInmobiliaria {

	public static void main(String[] args) {
		// --- 1ra Colección Mapa ---
        // Aquí guardamos los Proyectos Inmobiliarios 
        // La llave será el código del proyecto.
        Map<String, ProyectoInmobiliario> mapaProyectos = new HashMap<>();

        // --- Datos iniciales en el código ---
        System.out.println("Cargando datos iniciales del sistema...");

        // 1. Creamos un par de proyectos 
        ProyectoInmobiliario proyecto1 = new ProyectoInmobiliario("PROY-001", "Edificio Valle Hermoso");
        ProyectoInmobiliario proyecto2 = new ProyectoInmobiliario("PROY-002", "Condominio Brisas del Mar");

        // 2. Creamos algunos departamentos
        Departamento depto1 = new Departamento(101, 45.5, 2500.0);
        Departamento depto2 = new Departamento(102, 60.0, 3200.0);
        Departamento depto3 = new Departamento(201, 80.5, 4500.0);

        // 3. Agregamos los departamentos a sus respectivos proyectos 
        // (Aquí usamos la 2da colección anidada)
        proyecto1.getListaDepartamentos().add(depto1);
        proyecto1.getListaDepartamentos().add(depto2);
        proyecto2.getListaDepartamentos().add(depto3);

        // 4. Finalmente, guardamos los proyectos en nuestra colección principal (El Mapa)
        mapaProyectos.put(proyecto1.getCodigo(), proyecto1);
        mapaProyectos.put(proyecto2.getCodigo(), proyecto2);

        System.out.println("¡Datos cargados con éxito!");
        System.out.println("Proyectos registrados actualmente: " + mapaProyectos.size());
        
        // ...
    }
}

	}

}
