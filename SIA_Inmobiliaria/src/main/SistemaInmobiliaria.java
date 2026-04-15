package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Clase principal donde se inicializa el Sistema Inmobiliario
 * Contiene el menu interactivo para la consola y la interfaz grafica de usario (GUI)
 * * @author Franco Allendes 
 */

public class SistemaInmobiliaria {

    static Map<String, ProyectoInmobiliario> mapaProyectos = new HashMap<>();

    public static void main(String[] args) {
        
    	// --- Carga Batch al inicio ---
        GestorArchivos.cargarDatos(mapaProyectos);
        
        // Opcional: Si el mapa está vacío, cargamos los datos de prueba
        if (mapaProyectos.isEmpty()) {
            cargarDatosIniciales();
        }

        String[] opciones = {"Usar Consola", "Usar Ventanas (GUI)"};
        
        int eleccion = JOptionPane.showOptionDialog(null, 
                "¿Cómo desea ejecutar el Sistema Inmobiliario?", 
                "Selección de Interfaz", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

        if (eleccion == 0) {
            iniciarConsola();
        } else if (eleccion == 1) {
            iniciarVentanas();
        } else {
            System.out.println("Carga cancelada por el usuario.");
        }
    }

    public static void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales del sistema...");

        ProyectoInmobiliario proyecto1 = new ProyectoInmobiliario("PROY-001", "Edificio Valle Hermoso");
        ProyectoInmobiliario proyecto2 = new ProyectoInmobiliario("PROY-002", "Condominio Brisas del Mar");

        // Agregamos propiedades con los nuevos atributos: num, mts, precio, habitaciones, baños
        
        Propiedad depto1 = new DepartamentoEstandar(101, 45.5, 2500.0, 2, 1);
        Propiedad depto2 = new DepartamentoEstandar(102, 60.0, 3200.0, 3, 2);
        Propiedad depto3 = new Penthouse(201, 80.5, 4500.0, 4, 3);
        
        // Agregamos una casa de prueba (al final lleva el boolean del patio)
        
        Propiedad casa1 = new Casa(1, 120.0, 5000.0, 4, 3, true);

        proyecto1.agregarPropiedad(depto1);
        proyecto1.agregarPropiedad(depto2);
        proyecto2.agregarPropiedad(depto3);
        proyecto2.agregarPropiedad(casa1);

        proyecto1.setNivelDemanda(2);
        proyecto2.setNivelDemanda(5);

        mapaProyectos.put(proyecto1.getCodigo(), proyecto1);
        mapaProyectos.put(proyecto2.getCodigo(), proyecto2);

        System.out.println("¡Datos cargados con éxito!");
    }

    public static void iniciarConsola() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========== MENÚ GESTIÓN INMOBILIARIA ==========");
            System.out.println("--- GESTIÓN DE PROYECTOS (Colección 1) ---");
            System.out.println("1. Agregar Proyecto");
            System.out.println("2. Mostrar todos los Proyectos");
            System.out.println("3. Buscar Proyecto");
            System.out.println("4. Editar nombre de Proyecto");
            System.out.println("5. Eliminar Proyecto");
            System.out.println("--- GESTIÓN DE PROPIEDADES (Colección 2) ---");
            System.out.println("6. Agregar Propiedad (Casa/Depto)");
            System.out.println("7. Mostrar Propiedades de un Proyecto");
            System.out.println("8. Buscar Propiedad");
            System.out.println("9. Editar precio de Propiedad");
            System.out.println("10. Eliminar Propiedad");
            System.out.println("--- FUNCIONALIDADES DEL NEGOCIO ---");
            System.out.println("11. Simulador de Inversión");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                	System.out.print("Ingrese código del nuevo proyecto (Ej: PROY-003): ");
                    String codNuevo = scanner.nextLine();
                    
                    try {
                        if(mapaProyectos.containsKey(codNuevo)){
                            
                            throw new ProyectoDuplicadoException("El código " + codNuevo + " ya está registrado en el sistema.");
                        } 
                        
                        System.out.print("Ingrese el nombre del proyecto: ");
                        String nomNuevo = scanner.nextLine();
                        mapaProyectos.put(codNuevo, new ProyectoInmobiliario(codNuevo, nomNuevo));
                        System.out.println("¡Proyecto agregado con éxito!");
                        
                    } catch (ProyectoDuplicadoException e) {
                        System.out.println("ERROR DE NEGOCIO: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\n--- LISTADO DE PROYECTOS ---");
                    if(mapaProyectos.isEmpty()){
                        System.out.println("No hay proyectos registrados.");
                    } else {
                        for (ProyectoInmobiliario p : mapaProyectos.values()) {
                            System.out.println("Código: " + p.getCodigo() + " | Nombre: " + p.getNombre() + " | Propiedades: " + p.getListaPropiedades().size());
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
                case 6:
                    System.out.print("Ingrese el código del proyecto: ");
                    String codProyAdd = scanner.nextLine();
                    if (mapaProyectos.containsKey(codProyAdd)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codProyAdd);
                        System.out.print("Ingrese número de la propiedad: ");
                        int num = scanner.nextInt();
                        System.out.print("Ingrese metros cuadrados: ");
                        double mts = scanner.nextDouble();
                        System.out.print("Ingrese precio base: ");
                        double precio = scanner.nextDouble();
                        System.out.print("Ingrese cantidad de habitaciones: ");
                        int hab = scanner.nextInt();
                        System.out.print("Ingrese cantidad de baños: ");
                        int banos = scanner.nextInt();

                        System.out.println("¿Qué tipo de propiedad es? (1. Depto Estándar | 2. Penthouse | 3. Casa): ");
                        int tipoProp = scanner.nextInt();
                        scanner.nextLine();

                        Propiedad nuevaPropiedad;
                        if (tipoProp == 3) {
                            System.out.print("¿Tiene patio? (1 para Sí, 2 para No): ");
                            int opcPatio = scanner.nextInt();
                            scanner.nextLine();
                            boolean tienePatio = (opcPatio == 1);
                            nuevaPropiedad = new Casa(num, mts, precio, hab, banos, tienePatio);
                        } else if (tipoProp == 2) {
                            nuevaPropiedad = new Penthouse(num, mts, precio, hab, banos);
                        } else {
                            nuevaPropiedad = new DepartamentoEstandar(num, mts, precio, hab, banos);
                        }

                        proy.agregarPropiedad(nuevaPropiedad);
                        proy.setNivelDemanda(proy.getNivelDemanda() + 1);
                        System.out.println("¡Propiedad agregada exitosamente!");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;
                case 7:
                    System.out.print("Ingrese código del proyecto para ver sus propiedades: ");
                    String codVer = scanner.nextLine();
                    if (mapaProyectos.containsKey(codVer)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codVer);
                        if (proy.getListaPropiedades().isEmpty()) {
                            System.out.println("No hay propiedades en este proyecto.");
                        } else {
                            System.out.println("\n--- PROPIEDADES DE " + proy.getNombre() + " ---");
                            for (Propiedad p : proy.getListaPropiedades()) {
                                double pFinal = p.calcularPrecioFinal(proy.getNivelDemanda());
                                System.out.println("N°" + p.getNumero() + " | " + p.getHabitaciones() + " Hab / " + p.getBanos() + " Baños | Área: " + p.getMetrosCuadrados() + " | Precio Venta: $" + pFinal);
                            }
                        }
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;
                case 8: 
                    System.out.print("Ingrese código del proyecto: ");
                    String codBuscarProp = scanner.nextLine();
                    if (mapaProyectos.containsKey(codBuscarProp)) {
                        System.out.print("Ingrese número de la propiedad a buscar: ");
                        int numBuscado = scanner.nextInt();
                        scanner.nextLine();
                        boolean encontrado = false;
                        
                        for (Propiedad p : mapaProyectos.get(codBuscarProp).getListaPropiedades()) {
                            if (p.getNumero() == numBuscado) {
                                System.out.println("Propiedad Encontrada -> Área: " + p.getMetrosCuadrados() + " m2 | Habitaciones: " + p.getHabitaciones() + " | Precio Base: $" + p.getPrecioBase());
                                encontrado = true;
                                break;
                            }
                        }
                        if(!encontrado) System.out.println("Error: Propiedad no existe en este proyecto.");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;
                case 9: 
                    System.out.print("Ingrese código del proyecto: ");
                    String codEditarProp = scanner.nextLine();
                    if (mapaProyectos.containsKey(codEditarProp)) {
                        System.out.print("Ingrese número de la propiedad a editar: ");
                        int numEditar = scanner.nextInt();
                        System.out.print("Ingrese el nuevo precio base: ");
                        double nuevoPrecio = scanner.nextDouble();
                        scanner.nextLine();
                        boolean editado = false;
                        
                        for (Propiedad p : mapaProyectos.get(codEditarProp).getListaPropiedades()) {
                            if (p.getNumero() == numEditar) {
                                p.modificarPrecio(nuevoPrecio); 
                                System.out.println("¡Precio de la propiedad actualizado exitosamente!");
                                editado = true;
                                break;
                            }
                        }
                        if(!editado) System.out.println("Error: Propiedad no encontrada.");
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    break;
                case 10: 
                    System.out.print("Ingrese código del proyecto: ");
                    String codElimProp = scanner.nextLine();
                    if (mapaProyectos.containsKey(codElimProp)) {
                        System.out.print("Ingrese número de la propiedad a eliminar: ");
                        int numEliminar = scanner.nextInt();
                        scanner.nextLine();
                        
                        boolean borrado = mapaProyectos.get(codElimProp).getListaPropiedades().removeIf(p -> p.getNumero() == numEliminar);
                        if (borrado) {
                            System.out.println("¡Propiedad eliminada del proyecto!");
                        } else {
                            System.out.println("Error: No se encontró la propiedad.");
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

                    try {
                        if (presupuesto <= 0) {
                            throw new PresupuestoInvalidoException("El presupuesto no puede ser cero o negativo.");
                        }

                        boolean hayOpciones = false;
                        System.out.println("Buscando oportunidades de inversión rentables...\n");

                        for (ProyectoInmobiliario proy : mapaProyectos.values()) {
                            for (Propiedad p : proy.getListaPropiedades()) {
                                double precioVenta = p.calcularPrecioFinal(proy.getNivelDemanda());
                                if (precioVenta <= presupuesto) { 
                                    hayOpciones = true;
                                    double arriendoEstimado = precioVenta * 0.005; 
                                    double rentabilidadAnual = (arriendoEstimado * 12) / precioVenta * 100;

                                    System.out.println("Proyecto: " + proy.getNombre() + " | Propiedad N°" + p.getNumero());
                                    System.out.println(" - Precio de Compra: $" + precioVenta);
                                    System.out.println(" - Arriendo mensual sugerido: $" + String.format("%.2f", arriendoEstimado));
                                    System.out.println(" - Rentabilidad Anual estimada: " + String.format("%.1f", rentabilidadAnual) + "%");
                                    System.out.println("--------------------------------------------------");
                                }
                            }
                        }
                        if (!hayOpciones) {
                            System.out.println("No se encontraron propiedades dentro de ese presupuesto.");
                        }
                        
                    } catch (PresupuestoInvalidoException e) {
                        System.out.println("ERROR FINANCIERO: " + e.getMessage());
                    }
                    break;
                case 12:
                	GestorArchivos.guardarDatos(mapaProyectos);
                	System.out.println("Saliendo del menú de consola...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 12);

        scanner.close();
    }

    public static void iniciarVentanas() {
        String[] opcionesMenu = {
        		"1. Agregar Proyecto", "2. Mostrar todos los Proyectos", "3. Buscar Proyecto",
                "4. Editar nombre de Proyecto", "5. Eliminar Proyecto", "6. Agregar Propiedad (Casa/Depto)",
                "7. Mostrar Propiedades de un Proyecto", "8. Buscar Propiedad", "9. Editar precio de Propiedad",
                "10. Eliminar Propiedad", "11. Simulador de Inversión", 
                "12. Exportar Reporte a Excel", "13. Salir"
        };

        boolean salir = false;

        while (!salir) {
            String seleccion = (String) JOptionPane.showInputDialog(null,
                    "Seleccione una opción:",
                    "Menú Gestión Inmobiliaria (GUI)",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcionesMenu,
                    opcionesMenu[0]);

            if (seleccion == null || seleccion.equals("13. Salir")) {
                salir = true;
                GestorArchivos.guardarDatos(mapaProyectos);
                JOptionPane.showMessageDialog(null, "Datos guardados. Saliendo del menú de ventanas...");
                break;
            }

            try {
                if (seleccion.startsWith("1.")) {
                    String codNuevo = JOptionPane.showInputDialog("Ingrese código del nuevo proyecto:");
                    if (codNuevo != null && !codNuevo.trim().isEmpty()) {
                        if(mapaProyectos.containsKey(codNuevo)){
                            JOptionPane.showMessageDialog(null, "Error: Ya existe un proyecto con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String nomNuevo = JOptionPane.showInputDialog("Ingrese el nombre del proyecto:");
                            mapaProyectos.put(codNuevo, new ProyectoInmobiliario(codNuevo, nomNuevo));
                            JOptionPane.showMessageDialog(null, "¡Proyecto agregado con éxito!");
                        }
                    }
                } else if (seleccion.startsWith("2.")) {
                    if(mapaProyectos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay proyectos registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("--- LISTADO DE PROYECTOS ---\n\n");
                        for (ProyectoInmobiliario p : mapaProyectos.values()) {
                            sb.append("Código: ").append(p.getCodigo()).append(" | Nombre: ").append(p.getNombre()).append(" | Propiedades: ").append(p.getListaPropiedades().size()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                } else if (seleccion.startsWith("3.")) {
                    String codBuscar = JOptionPane.showInputDialog("Ingrese el código del proyecto a buscar:");
                    if (codBuscar != null) {
                        if (mapaProyectos.containsKey(codBuscar)) {
                            ProyectoInmobiliario p = mapaProyectos.get(codBuscar);
                            JOptionPane.showMessageDialog(null, "Proyecto Encontrado -> Nombre: " + p.getNombre() + " | Demanda: " + p.getNivelDemanda());
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (seleccion.startsWith("4.")) {
                    String codEditar = JOptionPane.showInputDialog("Ingrese el código del proyecto a editar:");
                    if (codEditar != null && mapaProyectos.containsKey(codEditar)) {
                        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre para el proyecto:");
                        mapaProyectos.get(codEditar).setNombre(nuevoNombre);
                        JOptionPane.showMessageDialog(null, "¡Nombre actualizado exitosamente!");
                    } else if (codEditar != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("5.")) {
                    String codEliminar = JOptionPane.showInputDialog("Ingrese el código del proyecto a eliminar:");
                    if (codEliminar != null) {
                        if (mapaProyectos.remove(codEliminar) != null) {
                            JOptionPane.showMessageDialog(null, "¡Proyecto eliminado del sistema!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No se encontró el proyecto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (seleccion.startsWith("6.")) {
                    String codProyAdd = JOptionPane.showInputDialog("Ingrese el código del proyecto:");
                    if (codProyAdd != null && mapaProyectos.containsKey(codProyAdd)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codProyAdd);

                        int num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de la propiedad:"));
                        double mts = Double.parseDouble(JOptionPane.showInputDialog("Ingrese metros cuadrados:"));
                        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio base:"));
                        int hab = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de habitaciones:"));
                        int banos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de baños:"));

                        String[] tipos = {"1. Depto Estándar", "2. Penthouse", "3. Casa"};
                        String tipoElegido = (String) JOptionPane.showInputDialog(null, "¿Qué tipo de propiedad es?", "Tipo", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

                        if (tipoElegido != null) {
                            Propiedad nuevaPropiedad;
                            if (tipoElegido.startsWith("3")) {
                                int patio = JOptionPane.showConfirmDialog(null, "¿Tiene patio?", "Patio", JOptionPane.YES_NO_OPTION);
                                nuevaPropiedad = new Casa(num, mts, precio, hab, banos, patio == JOptionPane.YES_OPTION);
                            } else if (tipoElegido.startsWith("2")) {
                                nuevaPropiedad = new Penthouse(num, mts, precio, hab, banos);
                            } else {
                                nuevaPropiedad = new DepartamentoEstandar(num, mts, precio, hab, banos);
                            }

                            proy.agregarPropiedad(nuevaPropiedad);
                            proy.setNivelDemanda(proy.getNivelDemanda() + 1);
                            JOptionPane.showMessageDialog(null, "¡Propiedad agregada exitosamente!");
                        }
                    } else if (codProyAdd != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("7.")) {
                    String codVer = JOptionPane.showInputDialog("Ingrese código del proyecto para ver sus propiedades:");
                    if (codVer != null && mapaProyectos.containsKey(codVer)) {
                        ProyectoInmobiliario proy = mapaProyectos.get(codVer);
                        if (proy.getListaPropiedades().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay propiedades en este proyecto.");
                        } else {
                            StringBuilder sb = new StringBuilder("--- PROPIEDADES DE " + proy.getNombre() + " ---\n\n");
                            for (Propiedad p : proy.getListaPropiedades()) {
                                sb.append("N°").append(p.getNumero()).append(" | ").append(p.getHabitaciones()).append(" Hab / ").append(p.getBanos()).append(" Baños | Venta: $").append(p.calcularPrecioFinal(proy.getNivelDemanda())).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    } else if (codVer != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("8.")) {
                    String codBuscarProp = JOptionPane.showInputDialog("Ingrese código del proyecto:");
                    if (codBuscarProp != null && mapaProyectos.containsKey(codBuscarProp)) {
                        int numBuscado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de la propiedad a buscar:"));
                        boolean encontrado = false;
                        for (Propiedad p : mapaProyectos.get(codBuscarProp).getListaPropiedades()) {
                            if (p.getNumero() == numBuscado) {
                                JOptionPane.showMessageDialog(null, "Propiedad Encontrada -> Área: " + p.getMetrosCuadrados() + " m2 | Habitaciones: " + p.getHabitaciones() + " | Precio Base: $" + p.getPrecioBase());
                                encontrado = true;
                                break;
                            }
                        }
                        if(!encontrado) JOptionPane.showMessageDialog(null, "Error: Propiedad no existe en este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (codBuscarProp != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("9.")) {
                    String codEditarProp = JOptionPane.showInputDialog("Ingrese código del proyecto:");
                    if (codEditarProp != null && mapaProyectos.containsKey(codEditarProp)) {
                        int numEditar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de la propiedad a editar:"));
                        double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio base:"));
                        boolean editado = false;
                        for (Propiedad p : mapaProyectos.get(codEditarProp).getListaPropiedades()) {
                            if (p.getNumero() == numEditar) {
                                p.modificarPrecio(nuevoPrecio);
                                JOptionPane.showMessageDialog(null, "¡Precio de la propiedad actualizado exitosamente!");
                                editado = true;
                                break;
                            }
                        }
                        if(!editado) JOptionPane.showMessageDialog(null, "Error: Propiedad no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (codEditarProp != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("10.")) {
                    String codElimProp = JOptionPane.showInputDialog("Ingrese código del proyecto:");
                    if (codElimProp != null && mapaProyectos.containsKey(codElimProp)) {
                        int numEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de la propiedad a eliminar:"));
                        boolean borrado = mapaProyectos.get(codElimProp).getListaPropiedades().removeIf(p -> p.getNumero() == numEliminar);
                        if (borrado) {
                            JOptionPane.showMessageDialog(null, "¡Propiedad eliminada del proyecto!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No se encontró la propiedad.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (codElimProp != null) {
                        JOptionPane.showMessageDialog(null, "Error: Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (seleccion.startsWith("11.")) {
                    String inputPresupuesto = JOptionPane.showInputDialog("Ingrese su presupuesto máximo para invertir: $");
                    if (inputPresupuesto != null) {
                        double presupuesto = Double.parseDouble(inputPresupuesto);
                        boolean hayOpciones = false;
                        StringBuilder sb = new StringBuilder("--- OPORTUNIDADES DE INVERSIÓN ---\n\n");

                        for (ProyectoInmobiliario proy : mapaProyectos.values()) {
                            for (Propiedad p : proy.getListaPropiedades()) {
                                double precioVenta = p.calcularPrecioFinal(proy.getNivelDemanda());
                                if (precioVenta <= presupuesto) {
                                    hayOpciones = true;
                                    double arriendoEstimado = precioVenta * 0.005;
                                    double rentabilidadAnual = (arriendoEstimado * 12) / precioVenta * 100;
                                    sb.append("Proyecto: ").append(proy.getNombre()).append(" | Prop N°").append(p.getNumero()).append("\n")
                                      .append("Compra: $").append(precioVenta).append(" | Arriendo: $").append(String.format("%.2f", arriendoEstimado)).append("\n")
                                      .append("Rentabilidad: ").append(String.format("%.1f", rentabilidadAnual)).append("%\n--------------------------\n");
                                }
                            }
                        }
                        if (!hayOpciones) {
                            JOptionPane.showMessageDialog(null, "No se encontraron propiedades dentro de ese presupuesto.");
                        } else {
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    }
                
                } else if (seleccion.startsWith("12.")) {
                    GestorArchivos.exportarReporteExcel(mapaProyectos);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error de formato: Asegúrese de ingresar solo números donde corresponde.", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}