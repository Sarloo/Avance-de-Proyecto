// Clase Node para implementar estructuras de datos personalizadas
public class Node {
    TareaFarmacia tarea; // Almacena la tarea
    Node siguiente; // Referencia al siguiente nodo

    // Constructor para inicializar un nodo con una tarea
    public Node(TareaFarmacia tarea) {
        this.tarea = tarea; // Asignar tarea
        this.siguiente = null; // Inicializar siguiente como nulo
    }

    // Método getter para obtener la tarea
    public TareaFarmacia getTarea() { return tarea; } // Retornar tarea

    // Método getter para obtener el siguiente nodo
    public Node getSiguiente() { return siguiente; } // Retornar siguiente nodo

    // Método setter para establecer el siguiente nodo
    public void setSiguiente(Node siguiente) { this.siguiente = siguiente; } // Cambiar siguiente nodo
}