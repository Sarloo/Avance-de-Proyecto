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