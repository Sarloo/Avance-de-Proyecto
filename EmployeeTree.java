import java.util.ArrayList;
import java.util.List;

public class EmployeeTree {
    private EmployeeNode raiz;
    private String criterio; // "id", "nombre", "cargaTrabajo"
    
    public EmployeeTree(String criterio) {
        this.raiz = null;
        this.criterio = criterio;
    }
    
    // Insertar empleado
    public void insertar(Empleado empleado) {
        raiz = insertarRec(raiz, empleado);
    }
    
    private EmployeeNode insertarRec(EmployeeNode nodo, Empleado empleado) {
        if (nodo == null) {
            return new EmployeeNode(empleado);
        }
        
        int comparacion = compararEmpleados(empleado, nodo.getEmpleado());
        
        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), empleado));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), empleado));
        }
        
        return nodo;
    }
    
    private int compararEmpleados(Empleado e1, Empleado e2) {
        switch (criterio) {
            case "id":
                return Integer.compare(e1.getId(), e2.getId());
            case "nombre":
                return e1.getNombre().compareToIgnoreCase(e2.getNombre());
            case "cargaTrabajo":
                return Integer.compare(e1.getCargaTrabajo(), e2.getCargaTrabajo());
            default:
                return Integer.compare(e1.getId(), e2.getId());
        }
    }
    
    // Buscar empleado por ID
    public Empleado buscarPorId(int id) {
        return buscarPorIdRec(raiz, id);
    }
    
    private Empleado buscarPorIdRec(EmployeeNode nodo, int id) {
        if (nodo == null) return null;
        
        if (nodo.getEmpleado().getId() == id) {
            return nodo.getEmpleado();
        }
        
        Empleado encontrado = buscarPorIdRec(nodo.getIzquierdo(), id);
        if (encontrado != null) return encontrado;
        
        return buscarPorIdRec(nodo.getDerecho(), id);
    }
    
    // Buscar empleados por departamento
    public List<Empleado> buscarPorDepartamento(String departamento) {
        List<Empleado> resultado = new ArrayList<>();
        buscarPorDepartamentoRec(raiz, departamento, resultado);
        return resultado;
    }
    
    private void buscarPorDepartamentoRec(EmployeeNode nodo, String departamento, List<Empleado> resultado) {
        if (nodo == null) return;
        
        if (nodo.getEmpleado().getDepartamento().equalsIgnoreCase(departamento)) {
            resultado.add(nodo.getEmpleado());
        }
        
        buscarPorDepartamentoRec(nodo.getIzquierdo(), departamento, resultado);
        buscarPorDepartamentoRec(nodo.getDerecho(), departamento, resultado);
    }
    
    // Buscar empleados disponibles con habilidad específica
    public List<Empleado> buscarDisponiblesConHabilidad(String habilidad) {
        List<Empleado> resultado = new ArrayList<>();
        buscarDisponiblesRec(raiz, habilidad, resultado);
        return resultado;
    }
    
    private void buscarDisponiblesRec(EmployeeNode nodo, String habilidad, List<Empleado> resultado) {
        if (nodo == null) return;
        
        Empleado emp = nodo.getEmpleado();
        if (emp.estaDisponible() && emp.tieneHabilidad(habilidad)) {
            resultado.add(emp);
        }
        
        buscarDisponiblesRec(nodo.getIzquierdo(), habilidad, resultado);
        buscarDisponiblesRec(nodo.getDerecho(), habilidad, resultado);
    }
    
// Asignar tarea a empleado con menor carga
public Empleado asignarTareaOptima(String departamento, String habilidadRequerida) {
    List<Empleado> empleadosDepartamento = buscarPorDepartamento(departamento);
    Empleado mejorCandidato = null;
    int menorCarga = Integer.MAX_VALUE;
    
    System.out.println("Empleados en departamento " + departamento + ": " + empleadosDepartamento.size());
    
    for (Empleado emp : empleadosDepartamento) {
        System.out.println("Evaluando: " + emp.getNombre() + 
                         ", disponible: " + emp.estaDisponible() + 
                         ", tiene habilidad: " + emp.tieneHabilidad(habilidadRequerida) +
                         ", carga: " + emp.getCargaTrabajo());
                         
        if (emp.estaDisponible() && emp.tieneHabilidad(habilidadRequerida)) {
            if (emp.getCargaTrabajo() < menorCarga) {
                menorCarga = emp.getCargaTrabajo();
                mejorCandidato = emp;
            }
        }
    }
    
    return mejorCandidato;
}
    
    // Recorrido in-order para mostrar empleados ordenados
    public List<Empleado> inOrder() {
        List<Empleado> resultado = new ArrayList<>();
        inOrderRec(raiz, resultado);
        return resultado;
    }
    
    private void inOrderRec(EmployeeNode nodo, List<Empleado> resultado) {
        if (nodo != null) {
            inOrderRec(nodo.getIzquierdo(), resultado);
            resultado.add(nodo.getEmpleado());
            inOrderRec(nodo.getDerecho(), resultado);
        }
    }
    
    // Obtener estadísticas
    public void mostrarEstadisticas() {
        List<Empleado> todos = inOrder();
        System.out.println("Total empleados: " + todos.size());
        
        // Contar por departamento
        java.util.Map<String, Integer> porDepartamento = new java.util.HashMap<>();
        for (Empleado emp : todos) {
            porDepartamento.put(emp.getDepartamento(), 
                porDepartamento.getOrDefault(emp.getDepartamento(), 0) + 1);
        }
        
        System.out.println("Empleados por departamento:");
        for (java.util.Map.Entry<String, Integer> entry : porDepartamento.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    // Verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }
}