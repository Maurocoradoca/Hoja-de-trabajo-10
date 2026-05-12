import java.util.*;

public class Floyd {
    private double[][] distancias;
    private int[][] siguientes;

    public void calcularRutas(Grafo grafo) {
        int n = grafo.getNumNodos();
        double[][] grafoMatriz = grafo.getMatriz();
        distancias = new double[n][n];
        siguientes = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = grafoMatriz[i][j];
                if (grafoMatriz[i][j] != Double.POSITIVE_INFINITY && i != j) {
                    siguientes[i][j] = j;
                } else {
                    siguientes[i][j] = -1; 
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        siguientes[i][j] = siguientes[i][k]; 
                    }
                }
            }
        }
    }
    
    public double[][] getDistancias() { return distancias; }

    public List<Integer> obtenerRuta(int origen, int destino) {
        List<Integer> ruta = new ArrayList<>();
        if (siguientes[origen][destino] == -1) return ruta; 
        
        ruta.add(origen);
        while (origen != destino) {
            origen = siguientes[origen][destino];
            ruta.add(origen);
        }
        return ruta;
    } 
}