public class CalculadoraCentro {
    public static int encontrarCentro(double[][] matrizDistancias, int numNodos) {
        double[] excentricidades = new double[numNodos];
        
        for (int j = 0; j < numNodos; j++) {
            double maxColumna = 0;
            for (int i = 0; i < numNodos; i++) {
                if (matrizDistancias[i][j] > maxColumna && matrizDistancias[i][j] != Double.POSITIVE_INFINITY) {
                    maxColumna = matrizDistancias[i][j];
                }
            }
            excentricidades[j] = maxColumna;
        }

        double minExcentricidad = Double.POSITIVE_INFINITY;
        int centro = -1;
        for (int j = 0; j < numNodos; j++) {
            if (excentricidades[j] < minExcentricidad) {
                minExcentricidad = excentricidades[j];
                centro = j;
            }
        }
        return centro;
    }
}