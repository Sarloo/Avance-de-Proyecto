// Implementación de una lista enlazada usando nodos
public class ListaTareas {
    private Node cabeza; // Referencia al primer nodo de la lista
    private int tamaño; // Contador del tamaño de la lista
    private java.util.HashMap<Integer, TareaFarmacia> mapaTareas = new java.util.HashMap<>();

    // Constructor para inicializar una lista vacía
    public ListaTareas() {
        cabeza = null; // Inicializar cabeza como nula
        tamaño = 0; // Inicializar tamaño en 0
    }

    // Método para insertar una tarea al final de la lista
    public void insertar(TareaFarmacia tarea) {
        Node nuevoNodo = new Node(tarea); // Crear nuevo nodo con la tarea
        if (cabeza == null) { // Verificar si la lista está vacía
            cabeza = nuevoNodo; // La cabeza apunta al nuevo nodo
        } else {
            Node actual = cabeza; // Empezar desde la cabeza
            while (actual.getSiguiente() != null) { // Buscar el último nodo
                actual = actual.getSiguiente(); // Mover al siguiente nodo
            }
            actual.setSiguiente(nuevoNodo); // El último nodo apunta al nuevo
        }
        mapaTareas.put(tarea.getId(), tarea);
        tamaño++; // Incrementar el tamaño
    }

    // Método para completar una tarea específica por ID
    public boolean completarTarea(int id) {
        Node actual = cabeza; // Empezar desde la cabeza
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

    // Método para eliminar una tarea por ID
    public boolean eliminar(int id) {
        if (cabeza == null) { // Verificar si la lista está vacía
            return false; // Retornar fracaso
        }
        
        // Caso especial: eliminar la cabeza
        if (cabeza.getTarea().getId() == id) { // Verificar si la cabeza es el objetivo
            cabeza = cabeza.getSiguiente(); // La cabeza ahora es el siguiente nodo
            tamaño--; // Decrementar tamaño
            return true; // Retornar éxito
        }
        
        // Buscar el nodo a eliminar
        Node actual = cabeza; // Empezar desde la cabeza
        while (actual.getSiguiente() != null) { // Recorrer hasta el penúltimo nodo
            if (actual.getSiguiente().getTarea().getId() == id) { // Verificar si el siguiente es el objetivo
                actual.setSiguiente(actual.getSiguiente().getSiguiente()); // Saltar el nodo objetivo
                tamaño--; // Decrementar tamaño
                return true; // Retornar éxito
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return false; // Retornar fracaso si no se encontró
    }

    // Método para buscar una tarea por ID
    public TareaFarmacia buscar(int id) {
        Node actual = cabeza; // Empezar desde la cabeza
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (tarea.getId() == id) { // Buscar por ID
                return tarea; // Retornar tarea encontrada
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return null; // Retornar null si no se encontró
    }

    public TareaFarmacia buscarPorIdHash(int id) {
        return mapaTareas.get(id);
    }

    // Método para buscar tareas por tipo
    public java.util.ArrayList<TareaFarmacia> buscarPorTipo(String tipo) {
        java.util.ArrayList<TareaFarmacia> resultado = new java.util.ArrayList<>(); // Crear lista de resultados
        Node actual = cabeza; // Empezar desde la cabeza
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (tarea.getTipo().equalsIgnoreCase(tipo)) { // Buscar por tipo (case insensitive)
                resultado.add(tarea); // Agregar a resultados
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return resultado; // Retornar resultados
    }

    // Método para buscar tareas por descripción
    public java.util.List<TareaFarmacia> buscarPorDescripcion(String texto) {
        java.util.List<TareaFarmacia> resultado = new java.util.ArrayList<>();
        for (TareaFarmacia tarea : getElementos()) {
            if (tarea.getDescripcion().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null; // Retornar true si cabeza es nula
    }

    // Método para obtener el tamaño de la lista
    public int tamanio() {
        return tamaño; // Retornar tamaño
    }

    // Método para contar tareas pendientes
    public int tareasPendientes() {
        int count = 0; // Contador inicializado en 0
        Node actual = cabeza; // Empezar desde la cabeza
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
        if (estaVacia()) { // Verificar si la lista está vacía
            System.out.println("No hay tareas"); // Mostrar mensaje
            return; // Salir del método
        }
        
        // Crear listas separadas para pendientes y completadas
        java.util.ArrayList<TareaFarmacia> pendientes = new java.util.ArrayList<>();
        java.util.ArrayList<TareaFarmacia> completadas = new java.util.ArrayList<>();
        
        Node actual = cabeza; // Empezar desde la cabeza
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
        
        System.out.println("=== TAREAS PENDIENTES (Ordenadas por Prioridad) ===");
        if (pendientes.isEmpty()) { // Verificar si hay pendientes
            System.out.println("No hay tareas pendientes"); // Mostrar mensaje
        } else {
            for (int i = 0; i < pendientes.size(); i++) { // Recorrer pendientes
                System.out.println((i + 1) + ". " + pendientes.get(i)); // Mostrar tarea
            }
        }
        
        System.out.println("\n=== TAREAS COMPLETADAS ===");
        if (completadas.isEmpty()) { // Verificar si hay completadas
            System.out.println("No hay tareas completadas"); // Mostrar mensaje
        } else {
            for (int i = 0; i < completadas.size(); i++) { // Recorrer completadas
                System.out.println((i + 1) + ". " + completadas.get(i)); // Mostrar tarea
            }
        }
    }

    // Método para mostrar todas las tareas
    public void mostrarTodas() {
        if (estaVacia()) { // Verificar si la lista está vacía
            System.out.println("La lista esta vacia"); // Mostrar mensaje
            return; // Salir del método
        }
        
        // Crear listas separadas para pendientes y completadas
        java.util.ArrayList<TareaFarmacia> pendientes = new java.util.ArrayList<>();
        java.util.ArrayList<TareaFarmacia> completadas = new java.util.ArrayList<>();
        
        Node actual = cabeza; // Empezar desde la cabeza
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
        
        System.out.println("=== TODAS LAS TAREAS ===");
        
        System.out.println("\n--- PENDIENTES ---");
        if (pendientes.isEmpty()) { // Verificar si hay pendientes
            System.out.println("No hay tareas pendientes"); // Mostrar mensaje
        } else {
            for (int i = 0; i < pendientes.size(); i++) { // Recorrer pendientes
                System.out.println((i + 1) + ". " + pendientes.get(i)); // Mostrar tarea
            }
        }
        
        System.out.println("\n--- COMPLETADAS ---");
        if (completadas.isEmpty()) { // Verificar si hay completadas
            System.out.println("No hay tareas completadas"); // Mostrar mensaje
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
        Node actual = cabeza; // Empezar desde la cabeza
        while (actual != null) { // Recorrer todos los nodos
            resultado.add(actual.getTarea()); // Agregar tarea a la lista
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return resultado; // Retornar lista
    }

    // Método recursivo para calcular tiempo estimado total de tareas pendientes
    public int tiempoTotalPendiente() {
        return tiempoTotalPendienteRec(cabeza);
    }

    private int tiempoTotalPendienteRec(Node nodo) {
        if (nodo == null) return 0;
        int tiempo = 0;
        if (!nodo.getTarea().estaCompletada()) {
            tiempo += nodo.getTarea().getTiempoEstimado(); // Debes agregar este campo en TareaFarmacia
        }
        return tiempo + tiempoTotalPendienteRec(nodo.getSiguiente());
    }

    public java.util.List<TareaFarmacia> obtenerTareasOrdenadas() {
        java.util.ArrayList<TareaFarmacia> todas = getElementos();
        todas.sort(new java.util.Comparator<TareaFarmacia>() {
            @Override
            public int compare(TareaFarmacia t1, TareaFarmacia t2) {
                int prioridadComp = Integer.compare(
                    obtenerValorPrioridad(t2.getPrioridad()), 
                    obtenerValorPrioridad(t1.getPrioridad())
                );
                if (prioridadComp != 0) return prioridadComp;
                return t1.getFechaCreacion().compareTo(t2.getFechaCreacion());
            }
            private int obtenerValorPrioridad(String prioridad) {
                switch (prioridad.toUpperCase()) {
                    case "ALTA": return 3;
                    case "MEDIA": return 2;
                    case "BAJA": return 1;
                    default: return 0;
                }
            }
        });
        return todas;
    }
}