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
