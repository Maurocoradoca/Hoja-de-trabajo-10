import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(50);
        GestorArchivo.cargarGrafo("guategrafo.txt", grafo);
        Floyd floyd = new Floyd();
        floyd.calcularRutas(grafo);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\nLogística COVID-19");
            System.out.println("1. Buscar ruta más corta");
            System.out.println("2. Indicar ciudad centro");
            System.out.println("3. Modificar rutas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                scanner.nextLine();
                continue;
            }

            if (opcion == 1) {
                System.out.print("Origen: ");
                String origen = scanner.nextLine();
                System.out.print("Destino: ");
                String destino = scanner.nextLine();
                
                Integer idOrigen = grafo.getIndiceCiudad(origen);
                Integer idDestino = grafo.getIndiceCiudad(destino);

                if (idOrigen != null && idDestino != null) {
                    List<Integer> ruta = floyd.obtenerRuta(idOrigen, idDestino);
                    double dist = floyd.getDistancias()[idOrigen][idDestino];
                    
                    if (dist == Double.POSITIVE_INFINITY) System.out.println("Ruta bloqueada o inexistente");
                    else {
                        System.out.println("Distancia: " + dist + " KM");
                        System.out.print("Ruta: ");
                        for (int id : ruta) System.out.print(grafo.getNombreCiudad(id) + " -> ");
                        System.out.println("Fin");
                    }
                } else System.out.println("Ciudades no registradas.");
            } else if (opcion == 2) {
                int centro = CalculadoraCentro.encontrarCentro(floyd.getDistancias(), grafo.getNumNodos());
                if (centro != -1) System.out.println("El centro logístico debe ser: " + grafo.getNombreCiudad(centro));
            }
            else if (opcion == 3) {
                System.out.println("a) Bloqueo de ruta | b) Nueva ruta");
                String sub = scanner.nextLine();
                System.out.print("Origen: "); String o = scanner.nextLine();
                System.out.print("Destino: "); String d = scanner.nextLine();

                if (sub.equalsIgnoreCase("a")) grafo.eliminarRuta(o, d);
                else if (sub.equalsIgnoreCase("b")) {
                    System.out.print("Distancia (KM): ");
                    grafo.agregarRuta(o, d, scanner.nextDouble());
                    scanner.nextLine();
                }
                
                floyd.calcularRutas(grafo); // Recálculo obligatorio tras el cambio
                System.out.println("Datos actualizados.");
            }
        }
        scanner.close();
    }
}