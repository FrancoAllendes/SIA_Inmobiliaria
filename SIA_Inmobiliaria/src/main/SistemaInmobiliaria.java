package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaInmobiliaria {

    public static void main(String[] args) {

        Map<String, ProyectoInmobiliario> mapaProyectos = new HashMap<>();

        System.out.println("Cargando datos iniciales del sistema...");

        ProyectoInmobiliario proyecto1 = new ProyectoInmobiliario("PROY-001", "Edificio Valle Hermoso");
        ProyectoInmobiliario proyecto2 = new ProyectoInmobiliario("PROY-002", "Condominio Brisas del Mar");

        Departamento depto1 = new DepartamentoEstandar(101, 45.5, 2500.0);
        Departamento depto2 = new DepartamentoEstandar(102, 60.0, 3200.0);
        Departamento depto3 = new Penthouse(201, 80.5, 4500.0);

        proyecto1.agregarDepartamento(depto1);
        proyecto1.agregarDepartamento(depto2);
        proyecto2.agregarDepartamento(depto3);

        proyecto1.setNivelDemanda(2);
        proyecto2.setNivelDemanda(5);

        mapaProyectos.put(proyecto1.getCodigo(), proyecto1);
        mapaProyectos.put(proyecto2.getCodigo(), proyecto2);

        System.out.println("¡Datos cargados con éxito!");

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========== MENÚ GESTIÓN INMOBILIARIA ==========");
            System.out.println("--- GESTIÓN DE PROYECTOS ---");
            System.out.println("1. Agregar Proyecto");
            System.out.println("2. Mostrar todos los Proyectos");
            System.out.println("3. Buscar Proyecto por código");
            System.out.println("4. Editar nombre de Proyecto");
            System.out.println("5. Eliminar Proyecto");
            System.out.println("--- GESTIÓN DE DEPARTAMENTOS ---");
            System.out.println("6. Agregar Departamento");
            System.out.println("7. Mostrar Departamentos de un Proyecto");
            System.out.println("8. Buscar Departamento");
            System.out.println("9. Editar precio de Departamento");
            System.out.println("10. Eliminar Departamento");
            System.out.println("--- FUNCIONALIDADES DEL NEGOCIO ---");
            System.out.println("11. Simulador de Inversión");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                // ==========================================
                // CASOS PARA PROYECTOS (COLECCIÓN 1 - MAPA)
                // ==========================================
                case 1:
                    System.out.print("Ingrese código del nuevo proyecto (Ej: PROY-003): ");
                    String codNuevo = scanner.nextLine();
                    if(mapaProyectos.containsKey(codNuevo)){
                        System.out.println("Error: Ya existe un proyecto con ese código.");
                    } else {
                        System.out.print("Ingrese el nombre del proyecto: ");
                        String nomNuevo = scanner.nextLine();
                        mapaProyectos.put(codNuevo, new ProyectoInmobiliario(codNuevo, nomNuevo));
                        System.out.println("¡Proyecto agregado con éxito!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTADO DE PROYECTOS ---");
                    if(mapaProyectos.isEmpty()){
                        System.out.println("No hay proyectos registrados.");
                    } else {
                        for (ProyectoInmobiliario p : mapaProyectos.values()) {
                            System.out.println("Código: " + p.getCodigo() + " | Nombre: " + p.getNombre() + " | Deptos: " + p.getListaDepartamentos().size());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el código del proyecto a buscar: ");
                    String codBuscar = scanner.nextLine();
                    if (mapaProyectos.containsKey(codBuscar)) {
                        ProyectoInmobiliario p = mapaProyectos.get(codBuscar);
                        System.out.println("Proyecto Encontrado -> Nombre: " + p.getNombre() + " | Demanda actual: " + p.getNivelDemanda());
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el código del proyecto a editar: ");
                    String codEditar = scanner.nextLine();
                    if (mapaProyectos.containsKey(codEditar)) {
                        System.out.print("Ingrese el nuevo nombre para el proyecto: ");
                        String nuevoNombre = scanner.nextLine();
                        mapaProyectos.get(codEditar).setNombre(nuevoNombre);
                        System.out.println("¡Nombre actualizado exitosamente!");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el código del proyecto a eliminar: ");
                    String codEliminar = scanner.nextLine();
                    if (mapaProyectos.remove(codEliminar) != null) {
                        System.out.println("¡Proyecto eliminado del sistema!");
                    } else {
                        System.out.println("Error: No se encontró el proyecto para eliminar.");
                    }
                    break;

                // ==========================================
                // CASOS PARA DEPARTAMENTOS (COLECCIÓN 2 - LISTA)
                // ==========================================
                case 6:
                    System.out.print("Ingrese el código del proyecto: ");
                    String codProyAdd = scanner.nextLine();
                    if (mapaProyectos.containsKey(codProyAdd)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codProyAdd);
                        System.out.print("Ingrese número del departamento: ");
                        int num = scanner.nextInt();
                        System.out.print("Ingrese metros cuadrados: ");
                        double mts = scanner.nextDouble();
                        System.out.print("Ingrese precio base: ");
                        double precio = scanner.nextDouble();

                        System.out.println("¿Qué tipo es? (1. Estándar | 2. Penthouse): ");
                        int tipoDepto = scanner.nextInt();
                        scanner.nextLine();

                        Departamento nuevoDepto = (tipoDepto == 2) ? new Penthouse(num, mts, precio) : new DepartamentoEstandar(num, mts, precio);
                        proy.agregarDepartamento(nuevoDepto);
                        proy.setNivelDemanda(proy.getNivelDemanda() + 1);
                        System.out.println("¡Departamento agregado exitosamente!");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese código del proyecto para ver departamentos: ");
                    String codVer = scanner.nextLine();
                    if (mapaProyectos.containsKey(codVer)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codVer);
                        if (proy.getListaDepartamentos().isEmpty()) {
                            System.out.println("No hay departamentos en este proyecto.");
                        } else {
                            System.out.println("\n--- DEPARTAMENTOS DE " + proy.getNombre() + " ---");
                            for (Departamento d : proy.getListaDepartamentos()) {
                                double pFinal = d.calcularPrecioFinal(proy.getNivelDemanda());
                                System.out.println("N°" + d.getNumero() + " | Área: " + d.getMetrosCuadrados() + " | Precio Base: $" + d.getPrecioBase() + " | Venta: $" + pFinal);
                            }
                        }
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 8:
                    System.out.print("Ingrese código del proyecto: ");
                    String codBuscarDepto = scanner.nextLine();
                    if (mapaProyectos.containsKey(codBuscarDepto)) {
                        System.out.print("Ingrese número del departamento a buscar: ");
                        int numBuscado = scanner.nextInt();
                        scanner.nextLine();
                        boolean encontrado = false;
                        
                        for (Departamento d : mapaProyectos.get(codBuscarDepto).getListaDepartamentos()) {
                            if (d.getNumero() == numBuscado) {
                                System.out.println("Departamento Encontrado -> Área: " + d.getMetrosCuadrados() + " m2 | Precio Base: $" + d.getPrecioBase());
                                encontrado = true;
                                break;
                            }
                        }
                        if(!encontrado) System.out.println("Error: Departamento no existe en este proyecto.");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 9:
                    System.out.print("Ingrese código del proyecto: ");
                    String codEditarDepto = scanner.nextLine();
                    if (mapaProyectos.containsKey(codEditarDepto)) {
                        System.out.print("Ingrese número del departamento a editar: ");
                        int numEditar = scanner.nextInt();
                        System.out.print("Ingrese el nuevo precio base: ");
                        double nuevoPrecio = scanner.nextDouble();
                        scanner.nextLine();
                        boolean editado = false;
                        
                        for (Departamento d : mapaProyectos.get(codEditarDepto).getListaDepartamentos()) {
                            if (d.getNumero() == numEditar) {
                                d.modificarPrecio(nuevoPrecio); 
                                System.out.println("¡Precio del departamento actualizado exitosamente!");
                                editado = true;
                                break;
                            }
                        }
                        if(!editado) System.out.println("Error: Departamento no encontrado.");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 10:
                    System.out.print("Ingrese código del proyecto: ");
                    String codElimDepto = scanner.nextLine();
                    if (mapaProyectos.containsKey(codElimDepto)) {
                        System.out.print("Ingrese número del departamento a eliminar: ");
                        int numEliminar = scanner.nextInt();
                        scanner.nextLine();
                        boolean borrado = mapaProyectos.get(codElimDepto).getListaDepartamentos().removeIf(d -> d.getNumero() == numEliminar);
                        
                        if (borrado) {
                            System.out.println("¡Departamento eliminado del proyecto!");
                        } else {
                            System.out.println("Error: No se encontró el departamento.");
                        }
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;

                case 11:
                    System.out.println("\n--- SIMULADOR DE INVERSIÓN INMOBILIARIA ---");
                    System.out.print("Ingrese su presupuesto máximo para invertir: $");
                    double presupuesto = scanner.nextDouble();
                    scanner.nextLine();

                    boolean hayOpciones = false;
                    System.out.println("Buscando oportunidades de inversión rentables...\n");

                    for (ProyectoInmobiliario p : mapaProyectos.values()) {
                        for (Departamento d : p.getListaDepartamentos()) {
                            double precioVenta = d.calcularPrecioFinal(p.getNivelDemanda());

                            if (precioVenta <= presupuesto) { 
                                hayOpciones = true;
                                
                                double arriendoEstimado = precioVenta * 0.005; 
                                double rentabilidadAnual = (arriendoEstimado * 12) / precioVenta * 100;

                                System.out.println("Proyecto: " + p.getNombre() + " | Depto N°" + d.getNumero());
                                System.out.println(" - Precio de Compra: $" + precioVenta);
                                System.out.println(" - Arriendo mensual sugerido: $" + String.format("%.2f", arriendoEstimado));
                                System.out.println(" - Rentabilidad Anual estimada: " + String.format("%.1f", rentabilidadAnual) + "%");
                                System.out.println("--------------------------------------------------");
                            }
                        }
                    }

                    if (!hayOpciones) {
                        System.out.println("No se encontraron departamentos dentro de ese presupuesto.");
                    }
                    break;
                
                case 12:
                    System.out.println("Saliendo del menú de consola...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 12);

        scanner.close();
    }
}