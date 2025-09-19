public class EmployeeNode {
    private Empleado empleado;
    private EmployeeNode izquierdo;
    private EmployeeNode derecho;
    
    public EmployeeNode(Empleado empleado) {
        this.empleado = empleado;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    // Getters y setters
    public Empleado getEmpleado() { return empleado; }
    public EmployeeNode getIzquierdo() { return izquierdo; }
    public EmployeeNode getDerecho() { return derecho; }
    
    public void setIzquierdo(EmployeeNode izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(EmployeeNode derecho) { this.derecho = derecho; }
}