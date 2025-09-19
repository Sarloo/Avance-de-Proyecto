<<<<<<< HEAD
public class Empleado {
    private int id;
    private String nombre;
    private String departamento;
    private String puesto;
    private String[] habilidades;
    private int cargaTrabajo; // 1-10 scale

    public Empleado(int id, String nombre, String departamento, String puesto, String[] habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.puesto = puesto;
        this.habilidades = habilidades;
        this.cargaTrabajo = 0;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public String getPuesto() { return puesto; }
    public String[] getHabilidades() { return habilidades; }
    public int getCargaTrabajo() { return cargaTrabajo; }
    
    public void setCargaTrabajo(int carga) { 
        this.cargaTrabajo = Math.min(Math.max(carga, 0), 10); 
    }
    
    public void incrementarCarga(int cantidad) {
        this.cargaTrabajo = Math.min(this.cargaTrabajo + cantidad, 10);
    }
    
    public boolean tieneHabilidad(String habilidad) {
        for (String h : habilidades) {
            if (h.equalsIgnoreCase(habilidad)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean estaDisponible() {
        return cargaTrabajo <= 7; // Disponible si carga <= 7/10
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | " + departamento + 
               " | " + puesto + " | Carga: " + cargaTrabajo + "/10";
    }
=======
public class Empleado {
    private int id;
    private String nombre;
    private String departamento;
    private String puesto;
    private String[] habilidades;
    private int cargaTrabajo; // 1-10 scale

    public Empleado(int id, String nombre, String departamento, String puesto, String[] habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.puesto = puesto;
        this.habilidades = habilidades;
        this.cargaTrabajo = 0;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public String getPuesto() { return puesto; }
    public String[] getHabilidades() { return habilidades; }
    public int getCargaTrabajo() { return cargaTrabajo; }
    
    public void setCargaTrabajo(int carga) { 
        this.cargaTrabajo = Math.min(Math.max(carga, 0), 10); 
    }
    
    public void incrementarCarga(int cantidad) {
        this.cargaTrabajo = Math.min(this.cargaTrabajo + cantidad, 10);
    }
    
    public boolean tieneHabilidad(String habilidad) {
        for (String h : habilidades) {
            if (h.equalsIgnoreCase(habilidad)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean estaDisponible() {
        return cargaTrabajo <= 7; // Disponible si carga <= 7/10
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | " + departamento + 
               " | " + puesto + " | Carga: " + cargaTrabajo + "/10";
    }
>>>>>>> c260dc38702821dfef1e46ca702fc453cc19e75d
}