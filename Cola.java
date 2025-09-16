// Implementación de una cola usando nodos
public class Cola {
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
            fin = nuevoNodo; // El nuevo nodo es ahora el último
        }
        tamaño++; // Incrementar el tamaño
    }

    // Método para procesar (completar) la primera tarea pendiente
    public TareaFarmacia procesarTarea() {
        if (estaVacia()) { // Verificar si la cola está vacía
            return null; // Retornar null si no hay elementos
        }
        
        // Buscar la primera tarea pendiente
        Node actual = frente; // Empezar desde el frente
        while (actual != null) { // Recorrer todos los nodos
            TareaFarmacia tarea = actual.getTarea(); // Obtener tarea del nodo actual
            if (!tarea.estaCompletada()) { // Verificar si no está completada
                tarea.setEstado("COMPLETADA"); // Cambiar estado a COMPLETADA
                return tarea; // Retornar tarea procesada
            }
            actual = actual.getSiguiente(); // Mover al siguiente nodo
        }
        return null; // Retornar null si no hay pendientes
    }

    // Método para completar una receta específica por ID
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

    // Método para ver la primera tarea sin eliminarla (front)
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