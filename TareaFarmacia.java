// Clase que representa una tarea en la farmacia
public class TareaFarmacia {
    private int id; // Identificador único de la tarea
    private String descripcion; // Descripción de la tarea
    private String tipo; // Tipo: "URGENTE", "RECETA", "INVENTARIO", "ADMINISTRATIVA", "PRIORITARIA"
    private String prioridad; // Prioridad: "ALTA", "MEDIA", "BAJA"
    private String fechaCreacion; // Fecha de creación de la tarea
    private String estado; // Estado: "PENDIENTE", "EN_PROCESO", "COMPLETADA"
<<<<<<< HEAD
    private int tiempoEstimado; // Tiempo estimado en minutos

    // Constructor para inicializar una nueva tarea
    public TareaFarmacia(int id, String descripcion, String tipo, String prioridad, String fechaCreacion, int tiempoEstimado) {
=======

    // Constructor para inicializar una nueva tarea
    public TareaFarmacia(int id, String descripcion, String tipo, String prioridad, String fechaCreacion) {
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
        this.id = id; // Asignar ID
        this.descripcion = descripcion; // Asignar descripción
        this.tipo = tipo; // Asignar tipo
        this.prioridad = prioridad; // Asignar prioridad
        this.fechaCreacion = fechaCreacion; // Asignar fecha de creación
        this.estado = "PENDIENTE"; // Estado inicial siempre es PENDIENTE
<<<<<<< HEAD
        this.tiempoEstimado = tiempoEstimado;
=======
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
    }

    // Métodos getter para acceder a los atributos
    public int getId() { return id; } // Obtener ID
    public String getDescripcion() { return descripcion; } // Obtener descripción
    public String getTipo() { return tipo; } // Obtener tipo
    public String getPrioridad() { return prioridad; } // Obtener prioridad
    public String getFechaCreacion() { return fechaCreacion; } // Obtener fecha de creación
    public String getEstado() { return estado; } // Obtener estado
<<<<<<< HEAD
    public int getTiempoEstimado() { return tiempoEstimado; } // Obtener tiempo estimado
=======
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
    
    // Método setter para modificar el estado
    public void setEstado(String estado) { this.estado = estado; } // Cambiar estado

    // Método para representar la tarea como cadena de texto
<<<<<<< HEAD
=======
    @Override
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
    public String toString() {
        return "ID: " + id + // Mostrar ID
               " | " + descripcion + // Mostrar descripción
               " | Tipo: " + tipo + // Mostrar tipo
               " | Prioridad: " + prioridad + // Mostrar prioridad
               " | Fecha: " + fechaCreacion + // Mostrar fecha
               " | Estado: " + estado; // Mostrar estado
    }

    // Método para verificar si la tarea está completada
    public boolean estaCompletada() {
        return "COMPLETADA".equals(estado); // Retornar true si estado es COMPLETADA
    }
}