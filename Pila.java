import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Pila {
    private ArrayList<TareaFarmacia> elementos;

    public Pila() {
        elementos = new ArrayList<>();
    }

    // Agregar elemento a la pila (push)
    public void apilar(TareaFarmacia tarea) {
        elementos.add(tarea);
    }

    // Marcar como completada en lugar de eliminar
    public TareaFarmacia procesarTarea() {
        if (estaVacia()) {
            return null;
        }
        
        // Obtener la última tarea sin eliminarla
        TareaFarmacia tarea = elementos.get(elementos.size() - 1);
        if (!tarea.estaCompletada()) {
            tarea.setEstado("COMPLETADA");
            return tarea;
        }
        return null;
    }

    // Completar tarea específica por ID
    public boolean completarTarea(int id) {
        for (TareaFarmacia tarea : elementos) {
            if (tarea.getId() == id && !tarea.estaCompletada()) {
                tarea.setEstado("COMPLETADA");
                return true;
            }
        }
        return false;
    }

    // Ver el elemento superior sin eliminarlo (peek)
    public TareaFarmacia cima() {
        if (estaVacia()) {
            return null;
        }
        return elementos.get(elementos.size() - 1);
    }

    // Verificar si la pila está vacía
    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    // Obtener tamaño de la pila
    public int tamanio() {
        return elementos.size();
    }

    // Obtener número de tareas pendientes
    public int tareasPendientes() {
        int count = 0;
        for (TareaFarmacia tarea : elementos) {
            if (!tarea.estaCompletada()) {
                count++;
            }
        }
        return count;
    }

    // Mostrar tareas ordenadas: pendientes primero (por prioridad), luego completadas
    public void mostrar() {
        ArrayList<TareaFarmacia> pendientes = new ArrayList<>();
        ArrayList<TareaFarmacia> completadas = new ArrayList<>();
        
        for (TareaFarmacia tarea : elementos) {
            if (!tarea.estaCompletada()) {
                pendientes.add(tarea);
            } else {
                completadas.add(tarea);
            }
        }
        
        if (pendientes.isEmpty() && completadas.isEmpty()) {
            System.out.println("No hay tareas");
            return;
        }
        
        // Ordenar pendientes por prioridad
        ordenarPorPrioridad(pendientes);
        
        System.out.println("=== TAREAS URGENTES PENDIENTES (Ordenadas por Prioridad) ===");
        if (pendientes.isEmpty()) {
            System.out.println("No hay tareas urgentes pendientes");
        } else {
            for (int i = 0; i < pendientes.size(); i++) {
                System.out.println((i + 1) + ". " + pendientes.get(i));
            }
        }
        
        System.out.println("\n=== TAREAS URGENTES COMPLETADAS ===");
        if (completadas.isEmpty()) {
            System.out.println("No hay tareas urgentes completadas");
        } else {
            for (int i = 0; i < completadas.size(); i++) {
                System.out.println((i + 1) + ". " + completadas.get(i));
            }
        }
    }

    // Mostrar todas las tareas (completadas al final)
    public void mostrarTodas() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia");
            return;
        }
        
        ArrayList<TareaFarmacia> pendientes = new ArrayList<>();
        ArrayList<TareaFarmacia> completadas = new ArrayList<>();
        
        for (TareaFarmacia tarea : elementos) {
            if (!tarea.estaCompletada()) {
                pendientes.add(tarea);
            } else {
                completadas.add(tarea);
            }
        }
        
        // Ordenar pendientes por prioridad
        ordenarPorPrioridad(pendientes);
        
        System.out.println("=== TODAS LAS TAREAS URGENTES ===");
        
        System.out.println("\n--- PENDIENTES ---");
        if (pendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes");
        } else {
            for (int i = 0; i < pendientes.size(); i++) {
                System.out.println((i + 1) + ". " + pendientes.get(i));
            }
        }
        
        System.out.println("\n--- COMPLETADAS ---");
        if (completadas.isEmpty()) {
            System.out.println("No hay tareas completadas");
        } else {
            for (int i = 0; i < completadas.size(); i++) {
                System.out.println((i + 1) + ". " + completadas.get(i));
            }
        }
    }

    // Método para ordenar por prioridad
    private void ordenarPorPrioridad(ArrayList<TareaFarmacia> lista) {
        Collections.sort(lista, new Comparator<TareaFarmacia>() {
            @Override
            public int compare(TareaFarmacia t1, TareaFarmacia t2) {
                int valor1 = obtenerValorPrioridad(t1.getPrioridad());
                int valor2 = obtenerValorPrioridad(t2.getPrioridad());
                return Integer.compare(valor2, valor1);
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
    }

    // Obtener todos los elementos
    public ArrayList<TareaFarmacia> getElementos() {
        return new ArrayList<>(elementos);
    }
}