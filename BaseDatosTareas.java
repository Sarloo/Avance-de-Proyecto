import java.util.ArrayList;
import java.util.List;

public class BaseDatosTareas {
    public static List<TareaFarmacia> obtenerTareasEjemplo() {
        List<TareaFarmacia> lista = new ArrayList<>();
        lista.add(new TareaFarmacia(1, "Preparar receta de amoxicilina", "RECETA", "ALTA", "2025-09-01", 10));
        lista.add(new TareaFarmacia(2, "Inventario de analgésicos", "INVENTARIO", "MEDIA", "2025-09-02", 30));
        lista.add(new TareaFarmacia(3, "Atender cliente con receta controlada", "URGENTE", "ALTA", "2025-09-03", 15));
        lista.add(new TareaFarmacia(4, "Registrar nueva entrada de insulina", "INVENTARIO", "MEDIA", "2025-09-04", 20));
        lista.add(new TareaFarmacia(5, "Completar pedido de antibióticos", "PRIORITARIA", "ALTA", "2025-09-05", 25));
        lista.add(new TareaFarmacia(6, "Actualizar precios de medicamentos", "ADMINISTRATIVA", "BAJA", "2025-09-06", 40));
        lista.add(new TareaFarmacia(7, "Preparar receta de paracetamol", "RECETA", "MEDIA", "2025-09-07", 10));
        lista.add(new TareaFarmacia(8, "Inventario de jarabes", "INVENTARIO", "BAJA", "2025-09-08", 35));
        lista.add(new TareaFarmacia(9, "Atender cliente con alergia", "URGENTE", "ALTA", "2025-09-09", 12));
        lista.add(new TareaFarmacia(10, "Registrar entrada de vacunas", "INVENTARIO", "MEDIA", "2025-09-10", 18));
        lista.add(new TareaFarmacia(11, "Completar pedido de analgésicos", "PRIORITARIA", "MEDIA", "2025-09-11", 22));
        lista.add(new TareaFarmacia(12, "Actualizar datos de proveedores", "ADMINISTRATIVA", "BAJA", "2025-09-12", 45));
        lista.add(new TareaFarmacia(13, "Preparar receta de ibuprofeno", "RECETA", "ALTA", "2025-09-13", 9));
        lista.add(new TareaFarmacia(14, "Inventario de cremas", "INVENTARIO", "MEDIA", "2025-09-14", 28));
        lista.add(new TareaFarmacia(15, "Atender cliente con dolor agudo", "URGENTE", "ALTA", "2025-09-15", 14));
        lista.add(new TareaFarmacia(16, "Registrar entrada de vitaminas", "INVENTARIO", "BAJA", "2025-09-16", 16));
        lista.add(new TareaFarmacia(17, "Completar pedido de antigripales", "PRIORITARIA", "MEDIA", "2025-09-17", 20));
        lista.add(new TareaFarmacia(18, "Actualizar inventario de pastillas", "ADMINISTRATIVA", "MEDIA", "2025-09-18", 38));
        lista.add(new TareaFarmacia(19, "Preparar receta de loratadina", "RECETA", "MEDIA", "2025-09-19", 11));
        lista.add(new TareaFarmacia(20, "Inventario de soluciones", "INVENTARIO", "BAJA", "2025-09-20", 32));
        lista.add(new TareaFarmacia(21, "Atender cliente con receta pediátrica", "URGENTE", "ALTA", "2025-09-21", 13));
        lista.add(new TareaFarmacia(22, "Registrar entrada de antisépticos", "INVENTARIO", "MEDIA", "2025-09-22", 19));
        lista.add(new TareaFarmacia(23, "Completar pedido de antihistamínicos", "PRIORITARIA", "ALTA", "2025-09-23", 24));
        lista.add(new TareaFarmacia(24, "Actualizar datos de clientes", "ADMINISTRATIVA", "BAJA", "2025-09-24", 42));
        lista.add(new TareaFarmacia(25, "Preparar receta de omeprazol", "RECETA", "MEDIA", "2025-09-25", 8));
        return lista;
    }
}