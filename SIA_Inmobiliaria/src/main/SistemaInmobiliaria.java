package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaInmobiliaria {

    public static void main(String[] args) {

        // --- 1ra Colección (Mapa) ---
        Map<String, ProyectoInmobiliario> mapaProyectos = new HashMap<>();

        // --- Datos iniciales en el código ---
        System.out.println("Cargando datos iniciales del sistema...");

        ProyectoInmobiliario proyecto1 = new ProyectoInmobiliario("PROY-001", "Edificio Valle Hermoso");
        ProyectoInmobiliario proyecto2 = new ProyectoInmobiliario("PROY-002", "Condominio Brisas del Mar");

        Departamento depto1 = new Departamento(101, 45.5, 2500.0);
        Departamento depto2 = new Departamento(102, 60.0, 3200.0);
        Departamento depto3 = new Departamento(201, 80.5, 4500.0);

        proyecto1.getListaDepartamentos().add(depto1);
        proyecto1.getListaDepartamentos().add(depto2);
        proyecto2.getListaDepartamentos().add(depto3);
        
        proyecto1.setNivelDemanda(2);
        proyecto2.setNivelDemanda(5);

        mapaProyectos.put(proyecto1.getCodigo(), proyecto1);
        mapaProyectos.put(proyecto2.getCodigo(), proyecto2);

        System.out.println("Datos cargados con éxito");
        System.out.println("Proyectos registrados actualmente: " + mapaProyectos.size());

        // --- Menú del Sistema ---
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========== MENÚ GESTIÓN INMOBILIARIA ==========");
            System.out.println("1. Agregar nuevo Proyecto Inmobiliario (Colección 1)");
            System.out.println("2. Agregar nuevo Departamento a un Proyecto (Colección 2)");
            System.out.println("3. Mostrar listado de Proyectos (Colección 1)");
            System.out.println("4. Mostrar listado de Departamentos de un Proyecto (Colección 2)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            // Leemos la opción elegida
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el salto de línea residual

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código del nuevo proyecto (Ej: PROY-003): ");
                    String codNuevo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del proyecto: ");
                    String nomNuevo = scanner.nextLine();
                    
                    mapaProyectos.put(codNuevo, new ProyectoInmobiliario(codNuevo, nomNuevo));
                    System.out.println("¡Proyecto agregado con éxito!");
                    break;

                case 2:
                	System.out.print("Ingrese el código del proyecto al que pertenece (Ej: PROY-001): ");
                    String codProy = scanner.nextLine();
                    
                    if (mapaProyectos.containsKey(codProy)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codProy);

                        System.out.print("Ingrese número del departamento: ");
                        int num = scanner.nextInt();
                        System.out.print("Ingrese metros cuadrados: ");
                        double mts = scanner.nextDouble();
                        System.out.print("Ingrese precio base: ");
                        double precio = scanner.nextDouble();

                        System.out.println("¿Qué tipo de departamento es?");
                        System.out.println("1. Estándar");
                        System.out.println("2. Penthouse");
                        System.out.print("Seleccione una opción (1 o 2): ");
                        int tipoDepto = scanner.nextInt();
                        scanner.nextLine();

                        Departamento nuevoDepto;
                        if (tipoDepto == 2) {
                            nuevoDepto = new Penthouse(num, mts, precio);
                        } else {
                            nuevoDepto = new DepartamentoEstandar(num, mts, precio);
                        }

                        proy.agregarDepartamento(nuevoDepto);
                        
                        proy.setNivelDemanda(proy.getNivelDemanda() + 1);

                        System.out.println("¡Departamento agregado exitosamente al proyecto " + codProy + "!");
                    } else {
                        System.out.println("Error: No se encontró ningún proyecto con ese código.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- LISTADO DE PROYECTOS INMOBILIARIOS ---");
                    for (ProyectoInmobiliario p : mapaProyectos.values()) {
                        System.out.println("Código: " + p.getCodigo() + " | Nombre: " + p.getNombre() + " | Total Deptos: " + p.getListaDepartamentos().size());
                    }
                    break;

                case 4:
                	System.out.print("Ingrese el código del proyecto para ver sus departamentos: ");
                    String codBusqueda = scanner.nextLine();
                    
                    if (mapaProyectos.containsKey(codBusqueda)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codBusqueda);
                        System.out.println("\n--- DEPARTAMENTOS DEL PROYECTO: " + proy.getNombre() + " ---");
                        System.out.println("Nivel de Demanda actual del proyecto: " + proy.getNivelDemanda());
                        
                        if (proy.getListaDepartamentos().isEmpty()) {
                            System.out.println("Aún no hay departamentos registrados aquí.");
                        } else {
                            for (Departamento d : proy.getListaDepartamentos()) {
                                // USANDO SIA-6: El programa llama al método sobreescrito dinámicamente según si es Estándar o Penthouse
                                double precioFinal = d.calcularPrecioFinal(proy.getNivelDemanda());
                                System.out.println("Depto N°" + d.getNumero() + " | Área: " + d.getMetrosCuadrados() + " m2 | Precio Base: $" + d.getPrecioBase() + " | Precio Venta Ajustado: $" + precioFinal);
                            }
                        }
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del menú de consola...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}