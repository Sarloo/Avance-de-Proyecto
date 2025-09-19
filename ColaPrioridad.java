import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Implementación de una cola de prioridad usando un heap binario
public class ColaPrioridad {
    private ArrayList<TareaFarmacia> heap;
    private int tamaño;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor para inicializar una cola de prioridad vacía
    public ColaPrioridad() {
        heap = new ArrayList<>();
        tamaño = 0;
    }

    // Método para insertar una tarea en la cola de prioridad
    public void insertar(TareaFarmacia tarea) {
        heap.add(tarea);
        tamaño++;
        subir(tamaño - 1);
    }

    // Método para obtener la tarea con mayor prioridad sin eliminarla
    public TareaFarmacia frente() {
        if (estaVacia()) {
            return null;
        }
        return heap.get(0);
    }

    // Método para procesar (eliminar y retornar) la tarea con mayor prioridad
    public TareaFarmacia procesarTarea() {
        if (estaVacia()) {
            return null;
        }
        
        TareaFarmacia tarea = heap.get(0);
        if (!tarea.estaCompletada()) {
            tarea.setEstado("COMPLETADA");
        }
        
        // Mover la última tarea a la raíz y reordenar
        heap.set(0, heap.get(tamaño - 1));
        heap.remove(tamaño - 1);
        tamaño--;
        bajar(0);
        
        return tarea;
    }

    // Método para completar una tarea específica por ID
    public boolean completarTarea(int id) {
        for (int i = 0; i < tamaño; i++) {
            TareaFarmacia tarea = heap.get(i);
            if (tarea.getId() == id && !tarea.estaCompletada()) {
                tarea.setEstado("COMPLETADA");
                // Reordenar el heap después de cambiar la prioridad
                subir(i);
                bajar(i);
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para subir un elemento en el heap
    private void subir(int indice) {
        int hijo = indice;
        int padre = (hijo - 1) / 2;

        while (hijo > 0 && comparar(heap.get(hijo), heap.get(padre)) > 0) {
            intercambiar(hijo, padre);
            hijo = padre;
            padre = (hijo - 1) / 2;
        }
    }

    // Método auxiliar para bajar un elemento en el heap
    private void bajar(int indice) {
        int padre = indice;
        
        while (true) {
            int hijoIzq = 2 * padre + 1;
            int hijoDer = 2 * padre + 2;
            int mayor = padre;

            if (hijoIzq < tamaño && comparar(heap.get(hijoIzq), heap.get(mayor)) > 0) {
                mayor = hijoIzq;
            }

            if (hijoDer < tamaño && comparar(heap.get(hijoDer), heap.get(mayor)) > 0) {
                mayor = hijoDer;
            }

            if (mayor == padre) {
                break;
            }

            intercambiar(padre, mayor);
            padre = mayor;
        }
    }

    // Método para comparar tareas por prioridad y fecha
    private int comparar(TareaFarmacia t1, TareaFarmacia t2) {
        // Primero comparar por estado (las pendientes tienen prioridad)
        if (!t1.estaCompletada() && t2.estaCompletada()) {
            return 1;
        }
        if (t1.estaCompletada() && !t2.estaCompletada()) {
            return -1;
        }

        // Luego comparar por valor de prioridad
        int valorPrioridad1 = obtenerValorPrioridad(t1.getPrioridad());
        int valorPrioridad2 = obtenerValorPrioridad(t2.getPrioridad());
        
        if (valorPrioridad1 != valorPrioridad2) {
            return Integer.compare(valorPrioridad1, valorPrioridad2);
        }

        // Si misma prioridad, comparar por fecha (más antigua primero)
        try {
            LocalDate fecha1 = LocalDate.parse(t1.getFechaCreacion(), DATE_FORMATTER);
            LocalDate fecha2 = LocalDate.parse(t2.getFechaCreacion(), DATE_FORMATTER);
            return fecha1.compareTo(fecha2); // Fecha más antigua tiene prioridad
        } catch (Exception e) {
            return t1.getFechaCreacion().compareTo(t2.getFechaCreacion());
        }
    }

    // Método para convertir prioridad en valor numérico
    private int obtenerValorPrioridad(String prioridad) {
        switch (prioridad.toUpperCase()) {
            case "ALTA": return 3;
            case "MEDIA": return 2;
            case "BAJA": return 1;
            default: return 0;
        }
    }

    // Método para intercambiar dos elementos en el heap
    private void intercambiar(int i, int j) {
        TareaFarmacia temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return tamaño == 0;
    }

    // Método para obtener el tamaño de la cola
    public int tamanio() {
        return tamaño;
    }

    // Método para contar tareas pendientes
    public int tareasPendientes() {
        int count = 0;
        for (TareaFarmacia tarea : heap) {
            if (!tarea.estaCompletada()) {
                count++;
            }
        }
        return count;
    }

    // Método para mostrar tareas ordenadas por prioridad
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay tareas en la cola de prioridad");
            return;
        }

        // Crear copia para ordenar sin afectar el heap
        ArrayList<TareaFarmacia> copia = new ArrayList<>(heap);
        ordenarPorPrioridad(copia);

        System.out.println("=== TAREAS EN COLA DE PRIORIDAD ===");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println((i + 1) + ". " + copia.get(i));
        }
    }

    // Método para mostrar todas las tareas
    public void mostrarTodas() {
        if (estaVacia()) {
            System.out.println("La cola de prioridad está vacía");
            return;
        }

        // Crear copia para ordenar sin afectar el heap
        ArrayList<TareaFarmacia> copia = new ArrayList<>(heap);
        ordenarPorPrioridad(copia);

        System.out.println("=== TODAS LAS TAREAS PRIORITARIAS ===");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println((i + 1) + ". " + copia.get(i));
        }
    }

    // Método auxiliar para ordenar por prioridad
    private void ordenarPorPrioridad(ArrayList<TareaFarmacia> lista) {
        Collections.sort(lista, new Comparator<TareaFarmacia>() {
<<<<<<< HEAD
            @Override
=======
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
            public int compare(TareaFarmacia t1, TareaFarmacia t2) {
                return -comparar(t1, t2); // Orden descendente
            }
        });
    }

    // Método para obtener todos los elementos
    public ArrayList<TareaFarmacia> getElementos() {
        return new ArrayList<>(heap);
    }

    // Método para buscar una tarea por ID
    public TareaFarmacia buscar(int id) {
        for (TareaFarmacia tarea : heap) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }
}