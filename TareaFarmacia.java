public class TareaFarmacia {
    private int id;
    private String descripcion;
    private String tipo; // "URGENTE", "RECETA", "INVENTARIO", "ADMINISTRATIVA"
    private String prioridad; // "ALTA", "MEDIA", "BAJA"
    private String fechaCreacion;
    private String estado; // "PENDIENTE", "EN_PROCESO", "COMPLETADA"

    public TareaFarmacia(int id, String descripcion, String tipo, String prioridad, String fechaCreacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.estado = "PENDIENTE";
    }

    // Getters
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }
    public String getPrioridad() { return prioridad; }
    public String getFechaCreacion() { return fechaCreacion; }
    public String getEstado() { return estado; }
    
    // Setters
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ID: " + id + 
               " | " + descripcion + 
               " | Tipo: " + tipo + 
               " | Prioridad: " + prioridad + 
               " | Fecha: " + fechaCreacion +
               " | Estado: " + estado;
    }

    // Método para verificar si la tarea está completada
    public boolean estaCompletada() {
        return "COMPLETADA".equals(estado);
    }
}