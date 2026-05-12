import java.util.*;

public class Grafo {
    private double[][] matrizAdyacencia;
    private Map<String, Integer> ciudadAIndice;
    private Map<Integer, String> indiceACiudad;
    private int numNodos;
    private static final double INF = Double.POSITIVE_INFINITY;

    public Grafo(int maxNodos) {
        matrizAdyacencia = new double[maxNodos][maxNodos];
        ciudadAIndice = new HashMap<>();
        indiceACiudad = new HashMap<>();
        numNodos = 0;

        for (int i = 0; i < maxNodos; i++) {
            Arrays.fill(matrizAdyacencia[i], INF);
            matrizAdyacencia[i][i] = 0.0; // Distancia a sí mismo es 0
        }
    }

    public void agregarCiudad(String ciudad) {
        if (!ciudadAIndice.containsKey(ciudad)) {
            ciudadAIndice.put(ciudad, numNodos);
            indiceACiudad.put(numNodos, ciudad);
            numNodos++;
        }
    }

    public double[][] getMatriz() { return matrizAdyacencia; }
    public int getNumNodos() { return numNodos; }
    public String getNombreCiudad(int indice) { return indiceACiudad.get(indice); }
    public Integer getIndiceCiudad(String ciudad) { return ciudadAIndice.get(ciudad); }
}