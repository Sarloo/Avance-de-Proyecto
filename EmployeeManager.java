import java.util.*;

public class EmployeeManager {
    private Map<String, EmployeeTree> arbolesPorDepartamento;
    private EmployeeTree arbolGeneral;
    private Map<Integer, Empleado> mapaEmpleados = new HashMap<>();

    public EmployeeManager() {
        this.arbolesPorDepartamento = new HashMap<>();
        this.arbolGeneral = new EmployeeTree("id");

        // Inicializar árboles para departamentos comunes
        String[] departamentos = { "FARMACIA", "VENTAS", "INVENTARIO", "ADMINISTRACION", "ATENCION_CLIENTE" };
        for (String dept : departamentos) {
            arbolesPorDepartamento.put(dept, new EmployeeTree("cargaTrabajo"));
        }
    }

    // Agregar empleado
    public void agregarEmpleado(Empleado empleado) {
        arbolGeneral.insertar(empleado);
        mapaEmpleados.put(empleado.getId(), empleado);

        String departamento = empleado.getDepartamento().toUpperCase();
        if (arbolesPorDepartamento.containsKey(departamento)) {
            arbolesPorDepartamento.get(departamento).insertar(empleado);
        } else {
            // Crear nuevo árbol para departamento no existente
            EmployeeTree nuevoArbol = new EmployeeTree("cargaTrabajo");
            nuevoArbol.insertar(empleado);
            arbolesPorDepartamento.put(departamento, nuevoArbol);
        }
    }

    // Buscar empleado por ID
    public Empleado buscarEmpleado(int id) {
        return arbolGeneral.buscarPorId(id);
    }

    public Empleado buscarEmpleadoHash(int id) {
        return mapaEmpleados.get(id);
    }

    // Asignar tarea óptima
    public Empleado asignarTarea(String departamento, String habilidadRequerida) {
        departamento = departamento.toUpperCase().trim();
        habilidadRequerida = habilidadRequerida.toLowerCase().trim();
        
        System.out.println("Buscando en departamento: " + departamento + ", habilidad: " + habilidadRequerida);
        
        if (arbolesPorDepartamento.containsKey(departamento)) {
            Empleado empleado = arbolesPorDepartamento.get(departamento).asignarTareaOptima(departamento, habilidadRequerida);
            if (empleado != null) {
                System.out.println("Empleado encontrado: " + empleado.getNombre());
            }
            return empleado;
        }
        
        System.out.println("Departamento no encontrado: " + departamento);
        return null;
    }

    // Mostrar todos los empleados de un departamento ordenados por carga
// Mostrar todos los empleados de un departamento ordenados por carga
public void mostrarEmpleadosDepartamento(String departamento) {
    departamento = departamento.toUpperCase();
    if (arbolesPorDepartamento.containsKey(departamento)) {
        List<Empleado> empleados = arbolesPorDepartamento.get(departamento).inOrder();
        System.out.println("\n=== EMPLEADOS DE " + departamento + " ===");
        
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en este departamento.");
        } else {
            // Mostrar empleados ordenados por carga de trabajo (menor a mayor)
            for (Empleado emp : empleados) {
                String disponibilidad = emp.estaDisponible() ? "DISPONIBLE" : "NO DISPONIBLE";
                System.out.println(emp + " | " + disponibilidad);
            }
            
            // Mostrar estadísticas del departamento
            int total = empleados.size();
            int disponibles = 0;
            for (Empleado emp : empleados) {
                if (emp.estaDisponible()) disponibles++;
            }
            
            System.out.println("\n--- ESTADÍSTICAS DEL DEPARTAMENTO ---");
            System.out.println("Total de empleados: " + total);
            System.out.println("Empleados disponibles: " + disponibles);
            System.out.println("Empleados no disponibles: " + (total - disponibles));
            if (total > 0) {
                System.out.printf("Porcentaje de disponibilidad: %.1f%%\n", 
                                 (disponibles * 100.0) / total);
            }
        }
    } else {
        System.out.println("Departamento no encontrado: " + departamento);
        System.out.println("Departamentos disponibles: " + 
                          String.join(", ", arbolesPorDepartamento.keySet()));
    }
        }

    // Obtener empleados disponibles con habilidad
    public List<Empleado> obtenerEmpleadosDisponibles(String habilidad) {
        List<Empleado> disponibles = new ArrayList<>();
        for (EmployeeTree arbol : arbolesPorDepartamento.values()) {
            disponibles.addAll(arbol.buscarDisponiblesConHabilidad(habilidad));
        }
        return disponibles;
    }

    // Actualizar carga de trabajo
    public void actualizarCargaTrabajo(int idEmpleado, int nuevaCarga) {
        Empleado empleado = buscarEmpleado(idEmpleado);
        if (empleado != null) {
            empleado.setCargaTrabajo(nuevaCarga);
            // Nota: Para mantener el orden, se debe eliminar y reinsertar el empleado en el árbol.
            // Esta funcionalidad está pendiente de implementación.
        }
    }

    // Algoritmo divide y vencerás para distribuir tareas
    public void distribuirTareasDivideVenceras(List<TareaFarmacia> tareas, List<Empleado> empleados) {
        distribuirRec(tareas, empleados, 0, tareas.size() - 1);
    }

    private void distribuirRec(List<TareaFarmacia> tareas, List<Empleado> empleados, int inicio, int fin) {
        if (inicio > fin)
            return;
        if (inicio == fin) {
            // Asigna la tarea al empleado con menor carga
            Empleado mejor = null;
            int menorCarga = Integer.MAX_VALUE;
            for (Empleado emp : empleados) {
                if (emp.getCargaTrabajo() < menorCarga) {
                    menorCarga = emp.getCargaTrabajo();
                    mejor = emp;
                }
            }
            if (mejor != null)
                mejor.incrementarCarga(1);
            return;
        }
        int medio = (inicio + fin) / 2;
        distribuirRec(tareas, empleados, inicio, medio);
        distribuirRec(tareas, empleados, medio + 1, fin);
    }

// En la clase EmployeeManager.java
public List<Empleado> obtenerTodosEmpleados() {
    return arbolGeneral.inOrder(); // Asumiendo que el árbol general tiene un método inOrder()
}

public List<Empleado> buscarEmpleadosPorDepartamento(String departamento) {
    EmployeeTree arbol = arbolesPorDepartamento.get(departamento);
    if (arbol != null) {
        return arbol.buscarPorDepartamento(departamento);
    }
    return new ArrayList<>();
}
}