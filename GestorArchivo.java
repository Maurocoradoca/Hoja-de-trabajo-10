import java.io.*;

public class GestorArchivo {
    public static void cargarGrafo(String rutaArchivo, Grafo grafo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length == 3) {
                    grafo.agregarRuta(partes[0], partes[1], Double.parseDouble(partes[2])); 
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }
}