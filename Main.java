import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private Pila pilaUrgentes;
    private Cola colaRecetas;
    private ListaTareas listaInventario;
    private ListaTareas listaAdministrativas;
    private int contadorId;
    private Scanner scanner;

    public Main() {
        pilaUrgentes = new Pila();
        colaRecetas = new Cola();
        listaInventario = new ListaTareas();
        listaAdministrativas = new ListaTareas();
        contadorId = 1;
        scanner = new Scanner(System.in);
    }

    // Métodos para PILAS (Tareas Urgentes)
    public void agregarTareaUrgente(String descripcion, String prioridad) {
        String fecha = obtenerFechaActual();
        TareaFarmacia tarea = new TareaFarmacia(contadorId++, descripcion, "URGENTE", prioridad, fecha);
        pilaUrgentes.apilar(tarea);
        System.out.println("Tarea urgente agregada: " + descripcion + " (Prioridad: " + prioridad + ")");
    }

    public void procesarTareaUrgente() {
        TareaFarmacia tarea = pilaUrgentes.procesarTarea();
        if (tarea != null) {
            System.out.println("Tarea urgente COMPLETADA: " + tarea.getDescripcion());
        } else {
            System.out.println("No hay tareas urgentes pendientes");
        }
    }

    // Métodos para COLAS (Recetas Médicas)
    public void agregarReceta(String descripcion, String prioridad) {
        String fecha = obtenerFechaActual();
        TareaFarmacia tarea = new TareaFarmacia(contadorId++, descripcion, "RECETA", prioridad, fecha);
        colaRecetas.encolar(tarea);
        System.out.println("Receta agregada: " + descripcion + " (Prioridad: " + prioridad + ")");
    }

    public void procesarReceta() {
        TareaFarmacia tarea = colaRecetas.procesarTarea();
        if (tarea != null) {
            System.out.println("Receta COMPLETADA: " + tarea.getDescripcion());
        } else {
            System.out.println("No hay recetas pendientes");
        }
    }

    // Métodos para LISTAS (Inventario)
    public void agregarTareaInventario(String descripcion, String prioridad) {
        String fecha = obtenerFechaActual();
        TareaFarmacia tarea = new TareaFarmacia(contadorId++, descripcion, "INVENTARIO", prioridad, fecha);
        listaInventario.insertar(tarea);
        System.out.println("Tarea de inventario agregada: " + descripcion + " (Prioridad: " + prioridad + ")");
    }

    public void completarTareaInventario(int id) {
        if (listaInventario.completarTarea(id)) {
            System.out.println("Tarea de inventario COMPLETADA: ID " + id);
        } else {
            System.out.println("Tarea no encontrada o ya completada: ID " + id);
        }
    }

    public void eliminarTareaInventario(int id) {
        if (listaInventario.eliminar(id)) {
            System.out.println("Tarea de inventario ELIMINADA: ID " + id);
        } else {
            System.out.println("Tarea no encontrada: ID " + id);
        }
    }

    // Métodos para LISTAS (Tareas Administrativas)
    public void agregarTareaAdministrativa(String descripcion, String prioridad) {
        String fecha = obtenerFechaActual();
        TareaFarmacia tarea = new TareaFarmacia(contadorId++, descripcion, "ADMINISTRATIVA", prioridad, fecha);
        listaAdministrativas.insertar(tarea);
        System.out.println("Tarea administrativa agregada: " + descripcion + " (Prioridad: " + prioridad + ")");
    }
public void completarTareaAdministrativa(int id) {
        if (listaAdministrativas.completarTarea(id)) {
            System.out.println("Tarea administrativa COMPLETADA: ID " + id);
        } else {
            System.out.println("Tarea no encontrada o ya completada: ID " + id);
        }
    }

    public void eliminarTareaAdministrativa(int id) {
        if (listaAdministrativas.eliminar(id)) {
            System.out.println("Tarea administrativa ELIMINADA: ID " + id);
        } else {
            System.out.println("Tarea no encontrada: ID " + id);
        }
    }

    // Método para mostrar todas las tareas (solo pendientes)
    public void mostrarTodasLasTareas() {
        System.out.println("\n=== TODAS LAS TAREAS PENDIENTES ===");
        
        System.out.println("\n--- TAREAS URGENTES ---");
        pilaUrgentes.mostrar();
        
        System.out.println("\n--- RECETAS ---");
        colaRecetas.mostrar();
        
        System.out.println("\n--- TAREAS DE INVENTARIO ---");
        listaInventario.mostrar();
        
        System.out.println("\n--- TAREAS ADMINISTRATIVAS ---");
        listaAdministrativas.mostrar();
    }

    // Método para mostrar todas las tareas (incluyendo completadas)
    public void mostrarTodasLasTareasCompletadas() {
        System.out.println("\n=== TODAS LAS TAREAS (INCLUYENDO COMPLETADAS) ===");
        
        System.out.println("\n--- TAREAS URGENTES ---");
        pilaUrgentes.mostrarTodas();
        
        System.out.println("\n--- RECETAS ---");
        colaRecetas.mostrarTodas();
        
        System.out.println("\n--- TAREAS DE INVENTARIO ---");
        listaInventario.mostrarTodas();
        
        System.out.println("\n--- TAREAS ADMINISTRATIVAS ---");
        listaAdministrativas.mostrarTodas();
    }

    // Método auxiliar para obtener fecha actual
    private String obtenerFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    // Método para seleccionar prioridad
    private String seleccionarPrioridad() {
        System.out.println("\nSeleccione la prioridad:");
        System.out.println("1. ALTA");
        System.out.println("2. MEDIA");
        System.out.println("3. BAJA");
        System.out.print("Opcion: ");
        
        int opcion = leerEntero();
        
        switch (opcion) {
            case 1: return "ALTA";
            case 2: return "MEDIA";
            case 3: return "BAJA";
            default:
                System.out.println("Opcion no valida, se asignara prioridad MEDIA");
                return "MEDIA";
        }
    }

    // Métodos de utilidad para estadísticas
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS DE LA FARMACIA ===");
        System.out.println("Tareas urgentes pendientes: " + pilaUrgentes.tareasPendientes());
        System.out.println("Recetas pendientes: " + colaRecetas.tareasPendientes());
        System.out.println("Tareas de inventario pendientes: " + listaInventario.tareasPendientes());
        System.out.println("Tareas administrativas pendientes: " + listaAdministrativas.tareasPendientes());
        System.out.println("Total de tareas creadas: " + (contadorId - 1));
    }

    // Interfaz de usuario
    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTION - FARMACIA ===");
            System.out.println("1. Agregar nueva tarea");
            System.out.println("2. Procesar tarea urgente");
            System.out.println("3. Procesar receta medica");
            System.out.println("4. Ver tareas");
            System.out.println("5. Completar tarea de inventario");
            System.out.println("6. Completar tarea administrativa");
            System.out.println("7. Eliminar tarea permanentemente");
            System.out.println("8. Mostrar estadisticas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    mostrarMenuAgregarTarea();
                    break;
                case 2:
                    procesarTareaUrgente();
                    break;
                case 3:
                    procesarReceta();
                    break;
                case 4:
                    mostrarMenuVerTareas();
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la tarea de inventario a completar: ");
                    int idInventario = leerEntero();
                    completarTareaInventario(idInventario);
                    break;
                case 6:
                    System.out.print("Ingrese el ID de la tarea administrativa a completar: ");
                    int idAdmin = leerEntero();
                    completarTareaAdministrativa(idAdmin);
                    break;
                case 7:
                    mostrarMenuEliminarTareas();
                    break;
                case 8:
                    mostrarEstadisticas();
                    break;
                case 9:
                    System.out.println("¡Gracias por usar el sistema de la farmacia!");
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenuAgregarTarea() {
        System.out.println("\n=== AGREGAR NUEVA TAREA ===");
        System.out.println("1. Tarea urgente (entrega inmediata)");
        System.out.println("2. Receta medica");
        System.out.println("3. Tarea de inventario");
        System.out.println("4. Tarea administrativa");
        System.out.print("Seleccione el tipo de tarea: ");
        
        int tipo = leerEntero();
        
        System.out.print("Descripcion de la tarea: ");
        String descripcion = scanner.nextLine();
        
        // Seleccionar prioridad para todos los tipos de tarea
        String prioridad = seleccionarPrioridad();
        
        switch (tipo) {
            case 1:
                agregarTareaUrgente(descripcion, prioridad);
                break;
            case 2:
                agregarReceta(descripcion, prioridad);
                break;
            case 3:
                agregarTareaInventario(descripcion, prioridad);
                break;
            case 4:
                agregarTareaAdministrativa(descripcion, prioridad);
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }