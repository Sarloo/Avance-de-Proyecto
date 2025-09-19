import java.util.ArrayList;
import java.util.List;

public class BaseDatosEmpleados {
    public static List<Empleado> obtenerEmpleadosEjemplo() {
        List<Empleado> lista = new ArrayList<>();
        String[] departamentos = {"FARMACIA", "VENTAS", "INVENTARIO", "ADMINISTRACION", "ATENCION_CLIENTE"};
        String[][] puestos = {
            {"Farmaceutico", "Tecnico", "Auxiliar"},
            {"Vendedor", "Cajero", "Promotor"},
            {"Almacenista", "Organizador", "Controlador"},
            {"Gerente", "Contador", "Recepcionista"},
            {"Asesor", "Consultor", "Servicio"}
        };
        String[] nombres = {
            "María", "Carlos", "Ana", "Pedro", "Laura", "Jorge", "Lucía", "Miguel", "Sofía", "Luis",
            "Elena", "Andrés", "Paula", "Manuel", "Carmen", "Raúl", "Valeria", "David", "Isabel", "Sergio",
            "Patricia", "Fernando", "Gabriela", "Ricardo", "Daniela", "Alejandro", "Marta", "Francisco", "Sara", "Juan",
            "Rosa", "Alberto", "Natalia", "Hugo", "Beatriz", "Pablo", "Claudia", "Adrián", "Silvia", "Iván",
            "Julia", "Emilio", "Teresa", "Roberto", "Cristina", "Álvaro", "Lorena", "Óscar", "Eva", "Guillermo",
            "Andrea", "Enrique", "Noelia", "Martín", "Alicia", "Samuel", "Nuria", "Vicente", "Mónica", "Rubén",
            "Patricio", "Esther", "Javier", "Verónica", "Tomás", "Marina", "Felipe", "Sandra", "Gonzalo", "Irene",
            "Emilia", "Eduardo", "Ariadna", "Ramón", "Lidia", "Mateo", "Elsa", "Cristóbal", "Jimena", "Agustín",
            "Olga", "Sebastián", "Berta", "Joaquín", "Ainhoa", "Diego", "Pilar", "Lucas", "Miriam", "Mario",
            "Victoria", "Damián", "Helena", "Saúl", "Aitana", "Bruno", "Carla", "Leandro", "Nicolás", "Fabiola"
        };
        String[] apellidos = {
            "López", "Ruiz", "Martínez", "Gómez", "Díaz", "Sánchez", "Morales", "Jiménez", "Romero", "Torres",
            "Vargas", "Castro", "Ramos", "Ortega", "Silva", "Molina", "Delgado", "Ortiz", "Ramírez", "Herrera"
        };
        String[] habilidadesComunes = {
            "medicamentos", "recetas", "ventas", "inventario",
            "atencion_cliente", "administracion", "gestion",
            "organizacion", "logistica", "comunicacion"
        };

        for (int i = 1; i <= 100; i++) {
            int deptIdx = (i - 1) % departamentos.length;
            String departamento = departamentos[deptIdx];
            String puesto = puestos[deptIdx][(i - 1) % puestos[deptIdx].length];
            String nombre = nombres[(i - 1) % nombres.length] + " " +
                            apellidos[(i * 3) % apellidos.length] + " " +
                            apellidos[(i * 7) % apellidos.length];
            // Asignar 3 habilidades variadas
            String[] habilidades = new String[3];
            for (int h = 0; h < 3; h++) {
                habilidades[h] = habilidadesComunes[(i + h * 2) % habilidadesComunes.length];
            }
            Empleado emp = new Empleado(i, nombre, departamento, puesto, habilidades);
            // Asignar carga de trabajo pseudoaleatoria entre 0 y 10
            emp.setCargaTrabajo((i * 3 + deptIdx * 2) % 11);
            lista.add(emp);
        }
        return lista;
    }
}