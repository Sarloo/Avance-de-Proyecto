import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class ListaTareas {
    private ArrayList<TareaFarmacia> elementos;

    public ListaTareas() {
        elementos = new ArrayList<>();
    }

    // Insertar elemento en la lista
    public void insertar(TareaFarmacia tarea) {
        elementos.add(tarea);
    }

    // Marcar como completada en lugar de eliminar
    public boolean completarTarea(int id) {
        for (TareaFarmacia tarea : elementos) {
            if (tarea.getId() == id && !tarea.estaCompletada()) {
                tarea.setEstado("COMPLETADA");
                return true;
            }
        }
        return false;
    }

    // Eliminar elemento por ID (opcional)
    public boolean eliminar(int id) {
        Iterator<TareaFarmacia> iterator = elementos.iterator();
        while (iterator.hasNext()) {
            TareaFarmacia tarea = iterator.next();
            if (tarea.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Buscar elemento por ID
    public TareaFarmacia buscar(int id) {
        for (TareaFarmacia tarea : elementos) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    // Buscar elementos por tipo
    public ArrayList<TareaFarmacia> buscarPorTipo(String tipo) {
        ArrayList<TareaFarmacia> resultado = new ArrayList<>();
        for (TareaFarmacia tarea : elementos) {
            if (tarea.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    // Obtener tamaño de la lista
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
        
        System.out.println("=== TAREAS PENDIENTES (Ordenadas por Prioridad) ===");
        if (pendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes");
        } else {
            for (int i = 0; i < pendientes.size(); i++) {
                System.out.println((i + 1) + ". " + pendientes.get(i));
            }
        }
        
        System.out.println("\n=== TAREAS COMPLETADAS ===");
        if (completadas.isEmpty()) {
            System.out.println("No hay tareas completadas");
        } else {
            for (int i = 0; i < completadas.size(); i++) {
                System.out.println((i + 1) + ". " + completadas.get(i));
            }
        }
    }

    // Mostrar todas las tareas (completadas al final)
    public void mostrarTodas() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
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
        
        System.out.println("=== TODAS LAS TAREAS ===");
        
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