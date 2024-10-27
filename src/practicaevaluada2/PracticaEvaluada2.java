package practicaevaluada2;

import java.util.ArrayList;
import java.util.List;

public class PracticaEvaluada2 {
    public static void main(String[] args) {
  int cantidadClientes = 3; //  definimos una cantidad de clientes 
        List<Cliente> clientes = new ArrayList<>(); //Creamos el arreglo para guardar los clientes 
        
        for (int i = 0; i < cantidadClientes; i++) { // se hace un for para  
            Cliente cliente = new Cliente(i + 1);
            cliente.setNombre("Cliente " + (i + 1));
            clientes.add(cliente);
            cliente.start();
        }

        // Esperar a que todos los clientes terminen sus compras
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los clientes han finalizado sus compras.");

        // Mostrar los resultados finales de la simulación
        Cliente.mostrarResultados();
    }
}