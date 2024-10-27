package practicaevaluada2;



import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Cliente extends Thread {

    private static int totalProductos = 0;
    private static int totalTiempo = 0;
    private static int totalClientes = 0;

    private String nombre;
    private int productosSeleccionados;
    private int tiempoTotal;
    private int id;

    public Cliente(int id) {
        this.id = id;
        this.nombre = "Cliente " + id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public static void setTotalProductos(int totalProductos) {
        Cliente.totalProductos = totalProductos;
    }

    public static void setTotalTiempo(int totalTiempo) {
        Cliente.totalTiempo = totalTiempo;
    }

    public static void setTotalClientes(int totalClientes) {
        Cliente.totalClientes = totalClientes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProductosSeleccionados(int productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public Cliente(String nombre, int productosSeleccionados, int tiempoTotal, int id) {
        this.nombre = nombre;
        this.productosSeleccionados = productosSeleccionados;
        this.tiempoTotal = tiempoTotal;
        this.id = id;
    }

 public int getClienteId() {
    return id;
}

    @Override
    public void run() {
        Random random = new Random();
        
        // Simular el tiempo de llegada
        int tiempoLlegada = 300 + random.nextInt(301); // Genera un número entre 300 y 600 ms.
        try {
            TimeUnit.MILLISECONDS.sleep(tiempoLlegada);
            System.out.println(nombre + " duro  " + tiempoLlegada + " ms.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Generar el número de pasillos que recorrerá (entre 8 y 13)
        int numeroPasillos = 8 + random.nextInt(6); // Genera un número entre 8 y 13
        productosSeleccionados = 0;

        // Recorrer los pasillos y seleccionar productos
        for (int i = 0; i < numeroPasillos; i++) {
            int productosEnPasillo = random.nextInt(7); // Productos seleccionados entre 0 y 6
            productosSeleccionados += productosEnPasillo;

            int tiempoSeleccion = 200 + random.nextInt(601); // Tiempo entre 200 y 800 ms
            try {
                TimeUnit.MILLISECONDS.sleep(tiempoSeleccion);
                System.out.println(nombre + " agarro  " + productosEnPasillo
                        + " productos en el pasillo " + (i + 1) + " en " + tiempoSeleccion + " ms.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simular el tiempo en la caja
        int tiempoCaja = productosSeleccionados * (20 + random.nextInt(61)); // 20 a 80 ms por producto
        try {
            TimeUnit.MILLISECONDS.sleep(tiempoCaja);
            System.out.println(nombre + " tiene  " + tiempoCaja + " ms en la caja.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Acumular resultados de manera segura usando synchronized
        synchronized (Cliente.class) {
            totalProductos += productosSeleccionados;
            totalTiempo += tiempoLlegada + tiempoCaja;
            totalClientes++;
        }

        // Imprimir un mensaje indicando que el cliente ha terminado su compra
        System.out.println(nombre + " termino su compra.");
    }

    // Método estático para mostrar los resultados finales
    public static void mostrarResultados() {
        System.out.println("\n Resumen del super ");
        System.out.println("Total de clientes atendidos: " + totalClientes);
        System.out.println("Total de productos seleccionados: " + totalProductos);
        if (totalClientes > 0) {
           System.out.println("Tiempo promedio  de los clientes: " + (totalTiempo / totalClientes) + " ms.");
        } else {
            System.out.println("No se atendieron clientes.");
        }
    }
}
