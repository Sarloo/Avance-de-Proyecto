import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// CLASE COLA INTEGRADA EN MAIN.JAVA
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
            frente = frente.getSiguiente(); // Avanzar el frente
            tamaño--; // Decrementar tamaño
            if (frente == null) { // Si la cola queda vacía
                fin = null; // Actualizar fin
            }
            return tarea; // Retornar tarea procesada
        }
        return null; // Retornar null si ya estaba completada
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
    private static int contadorId = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Método principal
    public static void main(String[] args) {
        int opcion; // Variable para almacenar la opción del menú
        
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
        System.out.println("=== SISTEMA DE GESTIÓN DE FARMACIA ==="); // Título
        System.out.println("1. Agregar nueva tarea"); // Opción 1
        System.out.println("2. Procesar tarea urgente (Pila)"); // Opción 2
        System.out.println("3. Procesar receta (Cola)"); // Opción 3
        System.out.println("4. Procesar tarea prioritaria (Cola de Prioridad)"); // Nueva opción
        System.out.println("5. Completar tarea específica por ID"); // Opción 4
        System.out.println("6. Mostrar todas las tareas"); // Opción 5
        System.out.println("7. Mostrar tareas pendientes"); // Opción 6
        System.out.println("8. Buscar tarea por ID"); // Opción 7
        System.out.println("9. Eliminar tarea"); // Opción 8
        System.out.println("10. Mostrar estadísticas"); // Opción 9
        System.out.println("0. Salir"); // Opción 0
        System.out.print("Seleccione una opción: "); // Solicitar entrada
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
        scanner.nextLine(); // Limpiar buffer después de leer el número
        System.out.print("Descripción: "); // Solicitar descripción
        String descripcion = scanner.nextLine(); // Leer descripción
        
        // TERCERO: Mostrar niveles de prioridad
        System.out.println("Prioridad:");
        System.out.println("1. ALTA");
        System.out.println("2. MEDIA");
        System.out.println("3. BAJA");
        System.out.print("Seleccione la prioridad (1-3): "); // Solicitar prioridad
        
        int prioridadOp = obtenerOpcion(); // Obtener opción de prioridad
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
        
        // Crear nueva tarea
        TareaFarmacia nuevaTarea = new TareaFarmacia(contadorId++, descripcion, tipo, prioridad, fecha);
        
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
        
        System.out.println("\n--- TAREAS GENERALES PENDIENTES ---"); // Subtítulo
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
}