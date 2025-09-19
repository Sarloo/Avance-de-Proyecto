//
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Cola {
    private Node frente; // Referencia al primer nodo de la cola
    private Node fin; // Referencia al último nodo de la cola
    private int tamaño; // Contador del tamaño de la cola

    // Constructor para inicializar una cola vacía
    public Cola() {
        frente = null; // Inicializar frente como nulo
        fin = null; // Inicializar fin como nulo
        tamaño = 0; // Inicializar tamaño en 0
    }

    // Método para agregar una tarea a la cola (enqueue)
    public void encolar(TareaFarmacia tarea) {
        Node nuevoNodo = new Node(tarea); // Crear nuevo nodo con la tarea
        if (estaVacia()) { // Verificar si la cola está vacía
            frente = nuevoNodo; // Frente y fin apuntan al nuevo nodo
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo); // El último nodo apunta al nuevo
            fin = nuevoNodo; // El nuevo nodo ahora es el último
        }
        tamaño++; // Incrementar el tamaño
    }

    // Método para procesar (completar) la primera tarea en la cola
    public TareaFarmacia procesarTarea() {
        if (estaVacia()) { // Verificar si la cola está vacía
            return null; // Retornar null si no hay elementos
        }
        
        TareaFarmacia tarea = frente.getTarea(); // Obtener tarea del frente
        if (!tarea.estaCompletada()) { // Verificar si no está completada
            tarea.setEstado("COMPLETADA"); // Cambiar estado a COMPLETADA
        }
        
        // Remover la tarea procesada
        frente = frente.getSiguiente(); // Avanzar el frente
        tamaño--; // Decrementar tamaño
        if (frente == null) { // Si la cola queda vacía
            fin = null; // Actualizar fin
        }
        
        return tarea; // Retornar tarea procesada
    }

    // Método para completar una tarea específica por ID
    public boolean completarReceta(int id) {
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (tarea.getId() == id && !tarea.estaCompletada()) { // Buscar por ID y verificar estado
                tarea.setEstado("COMPLETADA"); // Cambiar estado a COMPLETADA
                return true; // Retornar éxito
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return false; // Retornar fracaso si no se encontró
    }

    // Método para ver la primera tarea sin eliminarla (peek)
    public TareaFarmacia frente() {
        if (estaVacia()) { // Verificar si la cola está vacía
            return null; // Retornar null si no hay elementos
        }
        return frente.getTarea(); // Retornar tarea del frente
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null; // Retornar true si frente es nulo
    }

    // Método para obtener el tamaño de la cola
    public int tamanio() {
        return tamaño; // Retornar tamaño
    }

    // Método para contar tareas pendientes
    public int tareasPendientes() {
        int count = 0; // Contador inicializado en 0
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            if (!actual.getTarea().estaCompletada()) { // Verificar si está pendiente
                count++; // Incrementar contador
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return count; // Retornar cantidad de pendientes
    }

    // Método para mostrar tareas ordenadas por prioridad
    public void mostrar() {
        if (estaVacia()) { // Verificar si la cola está vacía
            System.out.println("No hay tareas"); // Mostrar mensaje
            return; // Salir del método
        }
        
        // Crear listas separadas para pendientes y completadas
        java.util.ArrayList<TareaFarmacia> pendientes = new java.util.ArrayList<>();
        java.util.ArrayList<TareaFarmacia> completadas = new java.util.ArrayList<>();
        
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (!tarea.estaCompletada()) { // Verificar si está pendiente
                pendientes.add(tarea); // Agregar a pendientes
            } else {
                completadas.add(tarea); // Agregar a completadas
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        
        // Ordenar pendientes por prioridad
        ordenarPorPrioridad(pendientes);
        
        System.out.println("=== RECETAS PENDIENTES (Ordenadas por Prioridad) ===");
        if (pendientes.isEmpty()) { // Verificar si hay pendientes
            System.out.println("No hay recetas pendientes"); // Mostrar mensaje
        } else {
            for (int i = 0; i < pendientes.size(); i++) { // Recorrer pendientes
                System.out.println((i + 1) + ". " + pendientes.get(i)); // Mostrar tarea
            }
        }
        
        System.out.println("\n=== RECETAS COMPLETADAS ===");
        if (completadas.isEmpty()) { // Verificar si hay completadas
            System.out.println("No hay recetas completadas"); // Mostrar mensaje
        } else {
            for (int i = 0; i < completadas.size(); i++) { // Recorrer completadas
                System.out.println((i + 1) + ". " + completadas.get(i)); // Mostrar tarea
            }
        }
    }

    // Método para mostrar todas las tareas
    public void mostrarTodas() {
        if (estaVacia()) { // Verificar si la cola está vacía
            System.out.println("La cola esta vacia"); // Mostrar mensaje
            return; // Salir del método
        }
        
        // Crear listas separadas para pendientes and completadas
        java.util.ArrayList<TareaFarmacia> pendientes = new java.util.ArrayList<>();
        java.util.ArrayList<TareaFarmacia> completadas = new java.util.ArrayList<>();
        
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (!tarea.estaCompletada()) { // Verificar si está pendiente
                pendientes.add(tarea); // Agregar a pendientes
            } else {
                completadas.add(tarea); // Agregar a completadas
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        
        // Ordenar pendientes por prioridad
        ordenarPorPrioridad(pendientes);
        
        System.out.println("=== TODAS LAS RECETAS ===");
        
        System.out.println("\n--- PENDIENTES ---");
        if (pendientes.isEmpty()) { // Verificar si hay pendientes
            System.out.println("No hay recetas pendientes"); // Mostrar mensaje
        } else {
            for (int i = 0; i < pendientes.size(); i++) { // Recorrer pendientes
                System.out.println((i + 1) + ". " + pendientes.get(i)); // Mostrar tarea
            }
        }
        
        System.out.println("\n--- COMPLETADAS ---");
        if (completadas.isEmpty()) { // Verificar si hay completadas
            System.out.println("No hay recetas completadas"); // Mostrar mensaje
        } else {
            for (int i = 0; i < completadas.size(); i++) { // Recorrer completadas
                System.out.println((i + 1) + ". " + completadas.get(i)); // Mostrar tarea
            }
        }
    }

    // Método auxiliar para ordenar por prioridad
    private void ordenarPorPrioridad(java.util.ArrayList<TareaFarmacia> lista) {
        java.util.Collections.sort(lista, new java.util.Comparator<TareaFarmacia>() {
            @Override
            public int compare(TareaFarmacia t1, TareaFarmacia t2) {
                int valor1 = obtenerValorPrioridad(t1.getPrioridad()); // Obtener valor numérico de prioridad 1
                int valor2 = obtenerValorPrioridad(t2.getPrioridad()); // Obtener valor numérico de prioridad 2
                return Integer.compare(valor2, valor1); // Comparar (orden descendente)
            }
            
            // Método para convertir prioridad en valor numérico
            private int obtenerValorPrioridad(String prioridad) {
                switch (prioridad.toUpperCase()) { // Convertir a mayúsculas para comparar
                    case "ALTA": return 3; // Alta = 3
                    case "MEDIA": return 2; // Media = 2
                    case "BAJA": return 1; // Baja = 1
                    default: return 0; // Desconocida = 0
                }
            }
        });
    }

    // Método para obtener todos los elementos (para compatibilidad)
    public java.util.ArrayList<TareaFarmacia> getElementos() {
        java.util.ArrayList<TareaFarmacia> resultado = new java.util.ArrayList<>(); // Crear lista
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            resultado.add(actual.getTarea()); // Agregar tarea a la lista
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return resultado; // Retornar lista
    }
}

// CLASE PRINCIPAL MAIN
public class Main {
    private static Pila pilaUrgentes = new Pila();
    private static Cola colaRecetas = new Cola();
    private static ColaPrioridad colaPrioridad = new ColaPrioridad();
    private static ListaTareas listaGeneral = new ListaTareas();
    private static EmployeeManager gestorEmpleados = new EmployeeManager();
    private static int contadorId = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Método principal
    public static void main(String[] args) {
        int opcion; // Variable para almacenar la opción del menú
        
        // Cargar algunos empleados de ejemplo
        cargarEmpleadosEjemplo();
        
        // Bucle principal del menú
        do {
            mostrarMenu(); // Mostrar el menú
            opcion = obtenerOpcion(); // Obtener opción del usuario
            
            // Procesar la opción seleccionada
            switch (opcion) {
                case 1: // Agregar tarea
                    agregarTarea(); 
                    break;
                case 2: // Procesar tarea urgente
                    procesarTareaUrgente();
                    break;
                case 3: // Procesar receta
                    procesarReceta();
                    break;
                case 4: // Procesar tarea prioritaria
                    procesarTareaPrioritaria();
                    break;
                case 5: // Completar tarea específica
                    completarTareaEspecifica();
                    break;
                case 6: // Mostrar todas las tareas
                    mostrarTodasLasTareas();
                    break;
                case 7: // Mostrar tareas pendientes
                    mostrarTareasPendientes();
                    break;
                case 8: // Buscar tarea por ID
                    buscarTareaPorId();
                    break;
                case 9: // Eliminar tarea
                    eliminarTarea();
                    break;
                case 10: // Mostrar estadísticas
                    mostrarEstadisticas();
                    break;
                case 11: // Gestión de empleados
                    gestionarEmpleados();
                    break;
                case 12: // Asignar tarea a empleado
                    asignarTareaEmpleado();
                    break;
                case 13: // Mostrar tiempo estimado total de tareas pendientes
                    mostrarTiempoEstimadoTotal();
                    break;
                case 0: // Salir
                    System.out.println("Saliendo del sistema..."); // Mensaje de despedida
                    break;
                default: // Opción inválida
                    System.out.println("Opción no válida. Intente de nuevo."); // Mensaje de error
            }
            
            System.out.println(); // Línea en blanco para separar
        } while (opcion != 0); // Continuar hasta que se seleccione salir
        
        scanner.close(); // Cerrar scanner
    }

    // Método para mostrar el menú principal
    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE GESTIÓN DE FARMACIA ===");
        System.out.println("1. Agregar nueva tarea");
        System.out.println("2. Procesar tarea urgente (Pila)");
        System.out.println("3. Procesar receta (Cola)");
        System.out.println("4. Procesar tarea prioritaria (Cola de Prioridad)");
        System.out.println("5. Completar tarea específica por ID");
        System.out.println("6. Mostrar todas las tareas");
        System.out.println("7. Mostrar tareas pendientes");
        System.out.println("8. Buscar tarea por ID");
        System.out.println("9. Eliminar tarea");
        System.out.println("10. Mostrar estadísticas");
        System.out.println("11. Gestionar empleados");
        System.out.println("12. Asignar tarea a empleado");
        System.out.println("13. Mostrar tiempo estimado total de tareas pendientes");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Método para obtener una opción válida del usuario
    private static int obtenerOpcion() {
        while (!scanner.hasNextInt()) { // Verificar si la entrada es un número
            System.out.println("Por favor, ingrese un número válido."); // Mensaje de error
            scanner.next(); // Limpiar entrada inválida
            System.out.print("Seleccione una opción: "); // Volver a solicitar
        }
        return scanner.nextInt(); // Retornar opción numérica
    }

    // Método para obtener una fecha válida del usuario
    private static String obtenerFecha() {
        scanner.nextLine(); // Limpiar buffer
        while (true) {
            System.out.print("Ingrese la fecha (YYYY-MM-DD) o presione Enter para fecha actual: ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                // Usar fecha actual si no se ingresa nada
                return LocalDate.now().toString();
            }
            
            try {
                // Validar formato de fecha
                LocalDate fecha = LocalDate.parse(input, DATE_FORMATTER);
                return fecha.toString();
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD (ej: 2024-01-15)");
            }
        }
    }

    // Método para agregar una nueva tarea con selección de fecha
    private static void agregarTarea() {
        scanner.nextLine(); // Limpiar buffer
        
        System.out.println("\n--- AGREGAR NUEVA TAREA ---"); // Título
        
        // PRIMERO: Mostrar tipos de tarea y solicitar tipo
        System.out.println("Tipos de tarea:"); 
        System.out.println("1. URGENTE");
        System.out.println("2. RECETA");
        System.out.println("3. INVENTARIO");
        System.out.println("4. ADMINISTRATIVA");
        System.out.println("5. PRIORITARIA");
        System.out.print("Seleccione el tipo (1-5): "); // Solicitar tipo
        
        int tipoOp = obtenerOpcion(); // Obtener opción de tipo
        scanner.nextLine(); // Limpiar buffer
        String tipo = ""; // Variable para almacenar tipo
        
        // Asignar tipo según opción
        switch (tipoOp) {
            case 1: tipo = "URGENTE"; break;
            case 2: tipo = "RECETA"; break;
            case 3: tipo = "INVENTARIO"; break;
            case 4: tipo = "ADMINISTRATIVA"; break;
            case 5: tipo = "PRIORITARIA"; break;
            default: 
                System.out.println("Opción inválida, se asignará ADMINISTRATIVA"); // Mensaje de error
                tipo = "ADMINISTRATIVA"; // Valor por defecto
        }
        
        // SEGUNDO: Solicitar descripción después del tipo
        System.out.print("Descripción: "); // Solicitar descripción
        String descripcion = scanner.nextLine(); // Leer descripción
        
        // TERCERO: Mostrar niveles de prioridad
        System.out.println("Prioridad:");
        System.out.println("1. ALTA");
        System.out.println("2. MEDIA");
        System.out.println("3. BAJA");
        System.out.print("Seleccione la prioridad (1-3): "); // Solicitar prioridad
        
        int prioridadOp = obtenerOpcion(); // Obtener opción de prioridad
        scanner.nextLine(); // Limpiar buffer
        String prioridad = ""; // Variable para almacenar prioridad
        
        // Asignar prioridad según opción
        switch (prioridadOp) {
            case 1: prioridad = "ALTA"; break;
            case 2: prioridad = "MEDIA"; break;
            case 3: prioridad = "BAJA"; break;
            default: 
                System.out.println("Opción inválida, se asignará MEDIA"); // Mensaje de error
                prioridad = "MEDIA"; // Valor por defecto
        }
        
        // CUARTO: Solicitar fecha
        System.out.println("Fecha de la tarea:");
        String fecha = obtenerFecha(); // Obtener fecha válida
        
        System.out.print("Tiempo estimado en minutos: "); // NUEVO CAMPO
        int tiempoEstimado = 0;
        while (true) {
            String tiempoStr = scanner.nextLine();
            try {
                tiempoEstimado = Integer.parseInt(tiempoStr);
                if (tiempoEstimado < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido para el tiempo estimado: ");
            }
        }
        
        // Crear nueva tarea
        TareaFarmacia nuevaTarea = new TareaFarmacia(contadorId++, descripcion, tipo, prioridad, fecha, tiempoEstimado);
        
        // Agregar a la estructura correspondiente
        if (tipo.equals("URGENTE")) { // Si es urgente
            pilaUrgentes.apilar(nuevaTarea); // Agregar a la pila
            System.out.println("Tarea URGENTE agregada a la pila con ID: " + nuevaTarea.getId()); // Mensaje de éxito
        } 
        else if (tipo.equals("RECETA")) { // Si es receta
            colaRecetas.encolar(nuevaTarea); // Agregar a la cola
            System.out.println("RECETA agregada a la cola con ID: " + nuevaTarea.getId()); // Mensaje de éxito
        } 
        else if (tipo.equals("PRIORITARIA")) { // Si es prioritaria
            colaPrioridad.insertar(nuevaTarea); // Agregar a la cola de prioridad
            System.out.println("Tarea PRIORITARIA agregada a la cola de prioridad con ID: " + nuevaTarea.getId()); // Mensaje de éxito
        }
        else { // Para otros tipos
            listaGeneral.insertar(nuevaTarea); // Agregar a la lista
            System.out.println("Tarea agregada a la lista general con ID: " + nuevaTarea.getId()); // Mensaje de éxito
        }
    }

    // Método para procesar una tarea urgente (de la pila)
    private static void procesarTareaUrgente() {
        System.out.println("\n--- PROCESAR TAREA URGENTE ---"); // Título
        
        if (pilaUrgentes.estaVacia()) { // Verificar si la pila está vacía
            System.out.println("No hay tareas urgentes pendientes"); // Mensaje informativo
            return; // Salir del método
        }
        
        // Procesar la tarea en la cima de la pila
        TareaFarmacia tareaProcesada = pilaUrgentes.procesarTarea();
        
        if (tareaProcesada != null) { // Si se procesó correctamente
            System.out.println("Tarea procesada: " + tareaProcesada); // Mostrar tarea procesada
        } else { // Si no se pudo procesar
            System.out.println("No hay tareas urgentes pendientes para procesar"); // Mensaje informativo
        }
    }

    // Método para procesar una receta (de la cola)
    private static void procesarReceta() {
        System.out.println("\n--- PROCESAR RECETA ---"); // Título
        
        if (colaRecetas.estaVacia()) { // Verificar si la cola está vacía
            System.out.println("No hay recetas pendientes"); // Mensaje informativo
            return; // Salir del método
        }
        
        // Procesar la primera receta pendiente en la cola
        TareaFarmacia recetaProcesada = colaRecetas.procesarTarea();
        
        if (recetaProcesada != null) { // Si se procesó correctamente
            System.out.println("Receta procesada: " + recetaProcesada); // Mostrar receta procesada
        } else { // Si no se pudo procesar
            System.out.println("No hay recetas pendientes para procesar"); // Mensaje informativo
        }
    }

    // Método para procesar una tarea prioritaria
    private static void procesarTareaPrioritaria() {
        System.out.println("\n--- PROCESAR TAREA PRIORITARIA ---"); // Título
        
        if (colaPrioridad.estaVacia()) { // Verificar si la cola de prioridad está vacía
            System.out.println("No hay tareas prioritarias pendientes"); // Mensaje informativo
            return; // Salir del método
        }
        
        // Procesar la tarea con mayor prioridad
        TareaFarmacia tareaProcesada = colaPrioridad.procesarTarea();
        
        if (tareaProcesada != null) { // Si se procesó correctamente
            System.out.println("Tarea prioritaria procesada: " + tareaProcesada); // Mostrar tarea procesada
        } else { // Si no se pudo procesar
            System.out.println("No hay tareas prioritarias pendientes para procesar"); // Mensaje informativo
        }
    }

    // Método para completar una tarea específica por ID
    private static void completarTareaEspecifica() {
        System.out.println("\n--- COMPLETAR TAREA POR ID ---"); // Título
        System.out.print("Ingrese el ID de la tarea a completar: "); // Solicitar ID
        
        int id = obtenerOpcion(); // Obtener ID
        
        // Buscar y completar en todas las estructuras
        boolean encontrada = false; // Bandera para indicar si se encontró
        
        if (pilaUrgentes.completarTarea(id)) { // Buscar en la pila
            System.out.println("Tarea URGENTE completada exitosamente"); // Mensaje de éxito
            encontrada = true; // Marcar como encontrada
        }
        
        if (colaRecetas.completarReceta(id)) { // Buscar en la cola
            System.out.println("RECETA completada exitosamente"); // Mensaje de éxito
            encontrada = true; // Marcar como encontrada
        }
        
        if (colaPrioridad.completarTarea(id)) { // Buscar en la cola de prioridad
            System.out.println("Tarea PRIORITARIA completada exitosamente"); // Mensaje de éxito
            encontrada = true; // Marcar como encontrada
        }
        
        if (listaGeneral.completarTarea(id)) { // Buscar en la lista
            System.out.println("Tarea general completada exitosamente"); // Mensaje de éxito
            encontrada = true; // Marcar como encontrada
        }
        
        if (!encontrada) { // Si no se encontró en ninguna estructura
            System.out.println("No se encontró una tarea pendiente con ID: " + id); // Mensaje de error
        }
    }

    // Método para mostrar todas las tareas
    private static void mostrarTodasLasTareas() {
        System.out.println("\n=== TODAS LAS TAREAS ==="); // Título
        
        System.out.println("\n--- TAREAS URGENTES (Pila) ---"); // Subtítulo
        if (pilaUrgentes.estaVacia()) { // Verificar si la pila está vacía
            System.out.println("No hay tareas urgentes"); // Mensaje informativo
        } else {
            pilaUrgentes.mostrarTodas(); // Mostrar todas las tareas urgentes
        }
        
        System.out.println("\n--- RECETAS (Cola) ---"); // Subtítulo
        if (colaRecetas.estaVacia()) { // Verificar si la cola está vacía
            System.out.println("No hay recetas"); // Mensaje informativo
        } else {
            colaRecetas.mostrarTodas(); // Mostrar todas las recetas
        }
        
        System.out.println("\n--- TAREAS PRIORITARIAS (Cola de Prioridad) ---"); // Nuevo subtítulo
        if (colaPrioridad.estaVacia()) { // Verificar si la cola de prioridad está vacía
            System.out.println("No hay tareas prioritarias"); // Mensaje informativo
        } else {
            colaPrioridad.mostrarTodas(); // Mostrar todas las tareas prioritarias
        }
        
        System.out.println("\n--- TAREAS GENERALES (Lista) ---"); // Subtítulo
        if (listaGeneral.estaVacia()) { // Verificar si la lista está vacía
            System.out.println("No hay tareas generales"); // Mensaje informativo
        } else {
            listaGeneral.mostrarTodas(); // Mostrar todas las tareas generales
        }
    }

    // Método para mostrar solo las tareas pendientes
    private static void mostrarTareasPendientes() {
        System.out.println("\n=== TAREAS PENDIENTES ==="); // Título
        
        System.out.println("\n--- TAREAS URGENTES PENDIENTES ---"); // Subtítulo
        if (pilaUrgentes.estaVacia()) { // Verificar si la pila está vacía
            System.out.println("No hay tareas urgentes"); // Mensaje informativo
        } else {
            pilaUrgentes.mostrar(); // Mostrar tareas urgentes pendientes
        }
        
        System.out.println("\n--- RECETAS PENDIENTES ---"); // Subtítulo
        if (colaRecetas.estaVacia()) { // Verificar si la cola está vacía
            System.out.println("No hay recetas"); // Mensaje informativo
        } else {
            colaRecetas.mostrar(); // Mostrar recetas pendientes
        }
        
        System.out.println("\n--- TAREAS PRIORITARIAS PENDIENTES ---"); // Nuevo subtítulo
        if (colaPrioridad.estaVacia()) { // Verificar si la cola de prioridad está vacía
            System.out.println("No hay tareas prioritarias"); // Mensaje informativo
        } else {
            colaPrioridad.mostrar(); // Mostrar tareas prioritarias pendientes
        }
        
        System.out.println("\n--- TAREAS GENERALES PENDientes ---"); // CORREGIDO
        if (listaGeneral.estaVacia()) { // Verificar si la lista está vacía
            System.out.println("No hay tareas generales"); // Mensaje informativo
        } else {
            listaGeneral.mostrar(); // Mostrar tareas generales pendientes
        }
    }

    // Método para buscar una tarea por ID
    private static void buscarTareaPorId() {
        System.out.println("\n--- BUSCAR TAREA POR ID ---"); // Título
        System.out.print("Ingrese el ID de la tarea a buscar: "); // Solicitar ID
        
        int id = obtenerOpcion(); // Obtener ID
        
        // Buscar en todas las estructuras
        TareaFarmacia tareaEncontrada = null; // Variable para almacenar tarea encontrada
        
        // Buscar en la pila
        for (TareaFarmacia t : pilaUrgentes.getElementos()) { // Recorrer elementos de la pila
            if (t.getId() == id) { // Comparar ID
                tareaEncontrada = t; // Almacenar tarea encontrada
                break; // Salir del bucle
            }
        }
        
        // Buscar en la cola si no se encontró en la pila
        if (tareaEncontrada == null) { 
            for (TareaFarmacia t : colaRecetas.getElementos()) { // Recorrer elementos de la cola
                if (t.getId() == id) { // Comparar ID
                    tareaEncontrada = t; // Almacenar tarea encontrada
                    break; // Salir del bucle
                }
            }
        }
        
        // Buscar en la cola de prioridad si no se encontró en la cola
        if (tareaEncontrada == null) { 
            tareaEncontrada = colaPrioridad.buscar(id); // Buscar en la cola de prioridad
        }
        
        // Buscar en la lista si no se encontró en la cola de prioridad
        if (tareaEncontrada == null) { 
            tareaEncontrada = listaGeneral.buscar(id); // Buscar en la lista
        }
        
        // Mostrar resultado de la búsqueda
        if (tareaEncontrada != null) { // Si se encontró
            System.out.println("Tarea encontrada: " + tareaEncontrada); // Mostrar tarea
        } else { // Si no se encontró
            System.out.println("No se encontró ninguna tarea con ID: " + id); // Mensaje de error
        }
    }

    // Método para eliminar una tarea
    private static void eliminarTarea() {
        System.out.println("\n--- ELIMINAR TAREA ---"); // Título
        System.out.print("Ingrese el ID de la tarea a eliminar: "); // Solicitar ID
        
        int id = obtenerOpcion(); // Obtener ID
        
        // Intentar eliminar de todas las estructuras
        boolean eliminada = false; // Bandera para indicar si se eliminó
        
        // Buscar y eliminar de la lista general
        if (listaGeneral.eliminar(id)) { 
            System.out.println("Tarea eliminada de la lista general"); // Mensaje de éxito
            eliminada = true; // Marcar como eliminada
        }
        
        // Para la pila, cola y cola de prioridad, marcamos como completadas en lugar de eliminar
        if (pilaUrgentes.completarTarea(id)) { 
            System.out.println("Tarea URGENTE marcada como completada"); // Mensaje informativo
            eliminada = true; // Marcar como procesada
        }
        
        if (colaRecetas.completarReceta(id)) { 
            System.out.println("RECETA marcada como completada"); // Mensaje informativo
            eliminada = true; // Marcar como procesada
        }
        
        if (colaPrioridad.completarTarea(id)) { 
            System.out.println("Tarea PRIORITARIA marcada como completada"); // Mensaje informativo
            eliminada = true; // Marcar como procesada
        }
        
        if (!eliminada) { // Si no se encontró en ninguna estructura
            System.out.println("No se encontró una tarea con ID: " + id); // Mensaje de error
        }
    }

    // Método para mostrar estadísticas del sistema
    private static void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ==="); // Título
        
        // Contar tareas por tipo y estado
        int urgentesTotal = pilaUrgentes.tamanio(); // Total de tareas urgentes
        int urgentesPendientes = pilaUrgentes.tareasPendientes(); // Pendientes urgentes
        int urgentesCompletadas = urgentesTotal - urgentesPendientes; // Completadas urgentes
        
        int recetasTotal = colaRecetas.tamanio(); // Total de recetas
        int recetasPendientes = colaRecetas.tareasPendientes(); // Pendientes recetas
        int recetasCompletadas = recetasTotal - recetasPendientes; // Completadas recetas
        
        int prioridadTotal = colaPrioridad.tamanio(); // Total de tareas prioritarias
        int prioridadPendientes = colaPrioridad.tareasPendientes(); // Pendientes prioritarias
        int prioridadCompletadas = prioridadTotal - prioridadPendientes; // Completadas prioritarias
        
        int generalTotal = listaGeneral.tamanio(); // Total de tareas generales
        int generalPendientes = listaGeneral.tareasPendientes(); // Pendientes generales
        int generalCompletadas = generalTotal - generalPendientes; // Completadas generales
        
        int totalTareas = urgentesTotal + recetasTotal + prioridadTotal + generalTotal; // Total de todas las tareas
        int totalPendientes = urgentesPendientes + recetasPendientes + prioridadPendientes + generalPendientes; // Total pendientes
        int totalCompletadas = urgentesCompletadas + recetasCompletadas + prioridadCompletadas + generalCompletadas; // Total completadas
        
        // Mostrar estadísticas
        System.out.println("Total de tareas en el sistema: " + totalTareas); // Total
        System.out.println("Tareas pendientes: " + totalPendientes); // Pendientes
        System.out.println("Tareas completadas: " + totalCompletadas); // Completadas
        
        System.out.println("\nPor tipo:"); // Subtítulo
        System.out.println("- URGENTES: " + urgentesTotal + " (Pendientes: " + urgentesPendientes + 
                          ", Completadas: " + urgentesCompletadas + ")"); // Estadísticas urgentes
        System.out.println("- RECETAS: " + recetasTotal + " (Pendientes: " + recetasPendientes + 
                          ", Completadas: " + recetasCompletadas + ")"); // Estadísticas recetas
        System.out.println("- PRIORITARIAS: " + prioridadTotal + " (Pendientes: " + prioridadPendientes + 
                          ", Completadas: " + prioridadCompletadas + ")"); // Estadísticas prioritarias
        System.out.println("- GENERALES: " + generalTotal + " (Pendientes: " + generalPendientes + 
                          ", Completadas: " + generalCompletadas + ")"); // Estadísticas generales
        
        // Calcular y mostrar porcentajes
        if (totalTareas > 0) { // Evitar división por cero
            double porcentajeCompletadas = (totalCompletadas * 100.0) / totalTareas; // Porcentaje completadas
            System.out.printf("Porcentaje de tareas completadas: %.2f%%\n", porcentajeCompletadas); // Mostrar porcentaje
        }
    }

    // Método para cargar empleados de ejemplo
    private static void cargarEmpleadosEjemplo() {
        List<Empleado> empleadosEjemplo = BaseDatosEmpleados.obtenerEmpleadosEjemplo();
        for (Empleado emp : empleadosEjemplo) {
            gestorEmpleados.agregarEmpleado(emp);
        }
        System.out.println("100 empleados de ejemplo cargados exitosamente");
    }

    // Nuevo método para gestión de empleados
    // Método para gestión de empleados
    private static void gestionarEmpleados() {
    System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
    System.out.println("1. Agregar empleado");
    System.out.println("2. Mostrar empleados por departamento");
    System.out.println("3. Buscar empleado por ID");
    System.out.println("4. Mostrar estadísticas de empleados");
    System.out.println("5. Mostrar empleados disponibles con habilidad");
    System.out.print("Seleccione opción: ");
    
    int opcion = obtenerOpcion();
    scanner.nextLine(); 
    
    switch (opcion) {
        case 1:
            agregarEmpleado();
            break;
        case 2:
            mostrarEmpleadosDepartamento();
            break;
        case 3:
            buscarEmpleadoPorId();
            break;
        case 4:
            mostrarEstadisticasEmpleados();
            break;
        case 5:
            mostrarEmpleadosDisponibles();
            break;
        default:
            System.out.println("Opción no válida");
    }
        }
    
private static void agregarEmpleado() {
    
    System.out.println("\n--- AGREGAR NUEVO EMPLEADO ---");
    
    // 1. MOSTRAR Y SELECCIONAR DEPARTAMENTO
    System.out.println("Departamentos disponibles:");
    String[] departamentos = {"FARMACIA", "VENTAS", "INVENTARIO", "ADMINISTRACION", "ATENCION_CLIENTE"};
    for (int i = 0; i < departamentos.length; i++) {
        System.out.println((i+1) + ". " + departamentos[i]);
    }
    System.out.print("Seleccione el departamento (1-" + departamentos.length + "): ");
    
    int deptOp = obtenerOpcion();
    String departamento;
    if (deptOp >= 1 && deptOp <= departamentos.length) {
        departamento = departamentos[deptOp-1];
    } else {
        System.out.println("Opción inválida, se asignará FARMACIA");
        departamento = "FARMACIA";
    }
    
    // 2. MOSTRAR PUESTOS SUGERIDOS SEGÚN DEPARTAMENTO
    System.out.println("\nPuestos sugeridos para " + departamento + ":");
    Map<String, String[]> puestosPorDept = new HashMap<>();
    puestosPorDept.put("FARMACIA", new String[]{"Farmaceutico", "Tecnico", "Auxiliar"});
    puestosPorDept.put("VENTAS", new String[]{"Vendedor", "Cajero", "Promotor"});
    puestosPorDept.put("INVENTARIO", new String[]{"Almacenista", "Organizador", "Controlador"});
    puestosPorDept.put("ADMINISTRACION", new String[]{"Gerente", "Contador", "Recepcionista"});
    puestosPorDept.put("ATENCION_CLIENTE", new String[]{"Asesor", "Consultor", "Servicio"});
    
    String[] puestosSugeridos = puestosPorDept.getOrDefault(departamento, new String[]{"Empleado"});
    for (int i = 0; i < puestosSugeridos.length; i++) {
        System.out.println((i+1) + ". " + puestosSugeridos[i]);
    }
    System.out.print("Seleccione el puesto (1-" + puestosSugeridos.length + ") o escriba uno personalizado: ");
    
    scanner.nextLine(); // Limpiar buffer
    String puestoInput = scanner.nextLine();
    String puesto;
    
    try {
        int puestoOp = Integer.parseInt(puestoInput);
        if (puestoOp >= 1 && puestoOp <= puestosSugeridos.length) {
            puesto = puestosSugeridos[puestoOp-1];
        } else {
            puesto = puestoInput;
        }
    } catch (NumberFormatException e) {
        puesto = puestoInput;
    }
    
    // 3. MOSTRAR Y SELECCIONAR HABILIDADES
    System.out.println("\nHabilidades disponibles (seleccione números separados por coma):");
    String[] habilidadesComunes = {
        "medicamentos", "recetas", "ventas", "inventario", 
        "atencion_cliente", "administracion", "gestion", 
        "organizacion", "logistica", "comunicacion"
    };
    
    for (int i = 0; i < habilidadesComunes.length; i++) {
        System.out.println((i+1) + ". " + habilidadesComunes[i]);
    }
    System.out.print("Seleccione habilidades (ej: 1,3,5) o escriba habilidades personalizadas: ");
    
    String habilidadesInput = scanner.nextLine();
    String[] habilidades;
    
    if (habilidadesInput.matches(".*\\d.*")) {
        // El usuario seleccionó números
        String[] numeros = habilidadesInput.split(",");
        List<String> habilidadesSeleccionadas = new ArrayList<>();
        
        for (String numStr : numeros) {
            try {
                int num = Integer.parseInt(numStr.trim());
                if (num >= 1 && num <= habilidadesComunes.length) {
                    habilidadesSeleccionadas.add(habilidadesComunes[num-1]);
                }
            } catch (NumberFormatException e) {
                // Ignorar números inválidos
            }
        }
        
        habilidades = habilidadesSeleccionadas.toArray(new String[0]);
    } else {
        // El usuario escribió habilidades personalizadas
        habilidades = habilidadesInput.split(",");
        // Limpiar espacios
        for (int i = 0; i < habilidades.length; i++) {
            habilidades[i] = habilidades[i].trim();
        }
    }
    
    // 4. SOLICITAR NOMBRE
    System.out.print("Nombre del empleado: ");
    String nombre = scanner.nextLine();
    
    // 5. CREAR Y AGREGAR EMPLEADO
    Empleado nuevoEmpleado = new Empleado(contadorId++, nombre, departamento, puesto, habilidades);
    gestorEmpleados.agregarEmpleado(nuevoEmpleado);
    System.out.println("Empleado agregado con ID: " + nuevoEmpleado.getId());
    System.out.println("Resumen: " + nuevoEmpleado.getNombre() + " | " + 
                      nuevoEmpleado.getDepartamento() + " | " + 
                      nuevoEmpleado.getPuesto());
    System.out.println("Habilidades: " + String.join(", ", nuevoEmpleado.getHabilidades()));
}

private static void mostrarEmpleadosDepartamento() {
    System.out.println("\nDepartamentos disponibles:");
    String[] departamentos = {"FARMACIA", "VENTAS", "INVENTARIO", "ADMINISTRACION", "ATENCION_CLIENTE"};
    for (int i = 0; i < departamentos.length; i++) {
        System.out.println((i+1) + ". " + departamentos[i]);
    }
    
    System.out.print("Seleccione el departamento (1-" + departamentos.length + "): ");
    
    int deptOp = obtenerOpcion();
    scanner.nextLine(); // Limpiar buffer
    
    String departamento;
    if (deptOp >= 1 && deptOp <= departamentos.length) {
        departamento = departamentos[deptOp-1];
    } else {
        System.out.println("Opción inválida, mostrando FARMACIA");
        departamento = "FARMACIA";
    }
    
    gestorEmpleados.mostrarEmpleadosDepartamento(departamento);
}
    
    private static void buscarEmpleadoPorId() {
        System.out.print("Ingrese el ID del empleado: ");
        int id = obtenerOpcion();
        Empleado empleado = gestorEmpleados.buscarEmpleado(id);
        if (empleado != null) {
            System.out.println("Empleado encontrado: " + empleado);
        } else {
            System.out.println("No se encontró empleado con ID: " + id);
        }
    }
    
// Método para mostrar estadísticas completas de empleados
private static void mostrarEstadisticasEmpleados() {
    System.out.println("\n=== ESTADÍSTICAS COMPLETAS DE EMPLEADOS ===");
    
    // Obtener todos los empleados a través del árbol general
    List<Empleado> todosEmpleados = gestorEmpleados.obtenerTodosEmpleados();
    
    if (todosEmpleados.isEmpty()) {
        System.out.println("No hay empleados registrados en el sistema.");
        return;
    }
    
    // Contadores para estadísticas
    int totalEmpleados = todosEmpleados.size();
    int disponibles = 0;
    int noDisponibles = 0;
    Map<String, Integer> porDepartamento = new HashMap<>();
    Map<String, Integer> porPuesto = new HashMap<>();
    Map<Integer, Integer> porCargaTrabajo = new HashMap<>();
    
    // Procesar cada empleado
    System.out.println("\n--- LISTA COMPLETA DE EMPLEADOS ---");
    for (Empleado emp : todosEmpleados) {
        // Mostrar información del empleado
        System.out.println(emp.toString());
        
        // Contar disponibilidad
        if (emp.estaDisponible()) {
            disponibles++;
        } else {
            noDisponibles++;
        }
        
        // Contar por departamento
        String dept = emp.getDepartamento();
        porDepartamento.put(dept, porDepartamento.getOrDefault(dept, 0) + 1);
        
        // Contar por puesto
        String puesto = emp.getPuesto();
        porPuesto.put(puesto, porPuesto.getOrDefault(puesto, 0) + 1);
        
        // Contar por carga de trabajo
        int carga = emp.getCargaTrabajo();
        porCargaTrabajo.put(carga, porCargaTrabajo.getOrDefault(carga, 0) + 1);
    }
    
    // Mostrar estadísticas generales
    System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
    System.out.println("Total de empleados: " + totalEmpleados);
    System.out.println("Empleados disponibles: " + disponibles);
    System.out.println("Empleados no disponibles: " + noDisponibles);
    System.out.printf("Porcentaje de disponibilidad: %.1f%%\n", 
                     (disponibles * 100.0) / totalEmpleados);
    
    // Mostrar distribución por departamento
    System.out.println("\n--- EMPLEADOS POR DEPARTAMENTO ---");
    for (Map.Entry<String, Integer> entry : porDepartamento.entrySet()) {
        System.out.printf("%-20s: %d empleados (%.1f%%)\n", 
                         entry.getKey(), 
                         entry.getValue(),
                         (entry.getValue() * 100.0) / totalEmpleados);
    }
    
    // Mostrar distribución por puesto
    System.out.println("\n--- EMPLEADOS POR PUESTO ---");
    for (Map.Entry<String, Integer> entry : porPuesto.entrySet()) {
        System.out.printf("%-20s: %d empleados\n", entry.getKey(), entry.getValue());
    }
    
    // Mostrar distribución por carga de trabajo
    System.out.println("\n--- DISTRIBUCIÓN POR CARGA DE TRABAJO ---");
    for (int i = 0; i <= 10; i++) {
        int cantidad = porCargaTrabajo.getOrDefault(i, 0);
        if (cantidad > 0) {
            System.out.printf("Carga %d/10: %d empleados\n", i, cantidad);
        }
    }
    
    // Empleados con mayor carga de trabajo
    System.out.println("\n--- EMPLEADOS CON MAYOR CARGA DE TRABAJO (≥8/10) ---");
    boolean haySobrecargados = false;
    for (Empleado emp : todosEmpleados) {
        if (emp.getCargaTrabajo() >= 8) {
            System.out.println("• " + emp.getNombre() + " - " + emp.getPuesto() + 
                             " - Carga: " + emp.getCargaTrabajo() + "/10");
            haySobrecargados = true;
        }
    }
    if (!haySobrecargados) {
        System.out.println("No hay empleados sobrecargados (todos tienen carga < 8)");
    }
}
    
private static void mostrarEmpleadosDisponibles() {
    System.out.println("\nHabilidades disponibles:");
    String[] habilidadesComunes = {
        "medicamentos", "recetas", "ventas", "inventario", 
        "atencion_cliente", "administracion", "gestion", 
        "organizacion", "logistica", "comunicacion"
    };
    
    for (int i = 0; i < habilidadesComunes.length; i++) {
        System.out.println((i + 1) + ". " + habilidadesComunes[i]);
    }
    
    System.out.print("\nIngrese el número o nombre de la habilidad requerida: ");
    String habilidadInput = scanner.nextLine();
    String habilidad;
    
    // Verificar si el usuario ingresó un número
    try {
        int opcion = Integer.parseInt(habilidadInput);
        if (opcion >= 1 && opcion <= habilidadesComunes.length) {
            habilidad = habilidadesComunes[opcion - 1];
        } else {
            habilidad = habilidadInput; // Si el número está fuera de rango, usar el texto
        }
    } catch (NumberFormatException e) {
        habilidad = habilidadInput; // Si no es número, usar el texto directamente
    }
    
    List<Empleado> disponibles = gestorEmpleados.obtenerEmpleadosDisponibles(habilidad);
    
    if (disponibles.isEmpty()) {
        System.out.println("No hay empleados disponibles con la habilidad: " + habilidad);
    } else {
        System.out.println("Empleados disponibles con habilidad '" + habilidad + "':");
        for (Empleado emp : disponibles) {
            System.out.println("  " + emp);
        }
    }
}
    
    private static void asignarTareaEmpleado() {
        System.out.println("\n--- ASIGNAR TAREA A EMPLEADO ---");
        
        // Mostrar departamentos disponibles primero
        System.out.println("Departamentos disponibles: FARMACIA, VENTAS, INVENTARIO, ADMINISTRACION, ATENCION_CLIENTE");
        System.out.print("Ingrese el departamento de la tarea: ");
        scanner.nextLine(); // Limpiar buffer
        String departamento = scanner.nextLine();
        
        // Mostrar habilidades comunes
        System.out.println("Habilidades comunes: medicamentos, recetas, ventas, inventario, atencion_cliente, administracion");
        System.out.print("Ingrese la habilidad requerida: ");
        String habilidad = scanner.nextLine();
        
        Empleado empleado = gestorEmpleados.asignarTarea(departamento, habilidad);
        
        if (empleado != null) {
            System.out.println("Tarea asignada a: " + empleado);
            empleado.incrementarCarga(2); // Aumentar carga de trabajo
            System.out.println("Carga de trabajo actualizada: " + empleado.getCargaTrabajo() + "/10");
        } else {
            System.out.println("No se encontró empleado disponible en " + departamento + 
                             " con habilidad: " + habilidad);
            System.out.println("Sugerencia: Verifique que el departamento y habilidad estén escritos correctamente");
        }
    }
    
    private static void mostrarTiempoEstimadoTotal() {
        int tiempo = listaGeneral.tiempoTotalPendiente();
        System.out.println("Tiempo estimado total de tareas pendientes: " + tiempo + " minutos");
    }

    
}