
import java.util.Scanner; // Importar Scanner para entrada de usuario

// Clase principal con el menú de la aplicación
public class Main {
    private static Pila pilaUrgentes = new Pila(); // Pila para tareas urgentes
    private static Cola colaRecetas = new Cola(); // Cola para recetas
    private static ListaTareas listaGeneral = new ListaTareas(); // Lista para tareas generales
    private static int contadorId = 1; // Contador para IDs únicos
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada

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
                case 4: // Completar tarea específica
                    completarTareaEspecifica();
                    break;
                case 5: // Mostrar todas las tareas
                    mostrarTodasLasTareas();
                    break;
                case 6: // Mostrar tareas pendientes
                    mostrarTareasPendientes();
                    break;
                case 7: // Buscar tarea por ID
                    buscarTareaPorId();
                    break;
                case 8: // Eliminar tarea
                    eliminarTarea();
                    break;
                case 9: // Mostrar estadísticas
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
        System.out.println("4. Completar tarea específica por ID"); // Opción 4
        System.out.println("5. Mostrar todas las tareas"); // Opción 5
        System.out.println("6. Mostrar tareas pendientes"); // Opción 6
        System.out.println("7. Buscar tarea por ID"); // Opción 7
        System.out.println("8. Eliminar tarea"); // Opción 8
        System.out.println("9. Mostrar estadísticas"); // Opción 9
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

    // Método para agregar una nueva tarea (orden modificado)
private static void agregarTarea() {
    scanner.nextLine(); // Limpiar buffer
    
    System.out.println("\n--- AGREGAR NUEVA TAREA ---"); // Título
    
    // PRIMERO: Mostrar tipos de tarea y solicitar tipo
    System.out.println("Tipos de tarea:"); 
    System.out.println("1. URGENTE");
    System.out.println("2. RECETA");
    System.out.println("3. INVENTARIO");
    System.out.println("4. ADMINISTRATIVA");
    System.out.print("Seleccione el tipo (1-4): "); // Solicitar tipo
    
    int tipoOp = obtenerOpcion(); // Obtener opción de tipo
    String tipo = ""; // Variable para almacenar tipo
    
    // Asignar tipo según opción
    switch (tipoOp) {
        case 1: tipo = "URGENTE"; break;
        case 2: tipo = "RECETA"; break;
        case 3: tipo = "INVENTARIO"; break;
        case 4: tipo = "ADMINISTRATIVA"; break;
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
    
    // Obtener fecha actual
    String fecha = java.time.LocalDate.now().toString(); // Fecha actual en formato string
    
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
        
        // Buscar en la lista si no se encontró en la cola
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
        
        // Para la pila y cola, marcamos como completadas en lugar de eliminar
        if (pilaUrgentes.completarTarea(id)) { 
            System.out.println("Tarea URGENTE marcada como completada"); // Mensaje informativo
            eliminada = true; // Marcar como procesada
        }
        
        if (colaRecetas.completarReceta(id)) { 
            System.out.println("RECETA marcada como completada"); // Mensaje informativo
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
        
        int generalTotal = listaGeneral.tamanio(); // Total de tareas generales
        int generalPendientes = listaGeneral.tareasPendientes(); // Pendientes generales
        int generalCompletadas = generalTotal - generalPendientes; // Completadas generales
        
        int totalTareas = urgentesTotal + recetasTotal + generalTotal; // Total de todas las tareas
        int totalPendientes = urgentesPendientes + recetasPendientes + generalPendientes; // Total pendientes
        int totalCompletadas = urgentesCompletadas + recetasCompletadas + generalCompletadas; // Total completadas
        
        // Mostrar estadísticas
        System.out.println("Total de tareas en el sistema: " + totalTareas); // Total
        System.out.println("Tareas pendientes: " + totalPendientes); // Pendientes
        System.out.println("Tareas completadas: " + totalCompletadas); // Completadas
        
        System.out.println("\nPor tipo:"); // Subtítulo
        System.out.println("- URGENTES: " + urgentesTotal + " (Pendientes: " + urgentesPendientes + 
                          ", Completadas: " + urgentesCompletadas + ")"); // Estadísticas urgentes
        System.out.println("- RECETAS: " + recetasTotal + " (Pendientes: " + recetasPendientes + 
                          ", Completadas: " + recetasCompletadas + ")"); // Estadísticas recetas
        System.out.println("- GENERALES: " + generalTotal + " (Pendientes: " + generalPendientes + 
                          ", Completadas: " + generalCompletadas + ")"); // Estadísticas generales
        
        // Calcular y mostrar porcentajes
        if (totalTareas > 0) { // Evitar división por cero
            double porcentajeCompletadas = (totalCompletadas * 100.0) / totalTareas; // Porcentaje completadas
            System.out.printf("Porcentaje de tareas completadas: %.2f%%\n", porcentajeCompletadas); // Mostrar porcentaje
        }
    }
}
