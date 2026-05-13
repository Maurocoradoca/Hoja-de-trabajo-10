import static org.junit.Assert.*;
import org.junit.Test;

public class GrafoTest {
    @Test
    public void testGrafoDinamicoYFloyd() {
        Grafo grafo = new Grafo(10);
        grafo.agregarRuta("A", "B", 10);
        grafo.agregarRuta("B", "C", 5);
        grafo.agregarRuta("A", "C", 20); 
        
        Floyd floyd = new Floyd();
        floyd.calcularRutas(grafo);
        
        int a = grafo.getIndiceCiudad("A");
        int c = grafo.getIndiceCiudad("C");
        
        // Verifica que Floyd elige A->B->C  sobre A->C 
        assertEquals(15.0, floyd.getDistancias()[a][c], 0.001);

        // Simulamos bloqueo
        grafo.eliminarRuta("B", "C");
        floyd.calcularRutas(grafo);
        
        // Ahora la ruta directa de 20 es la única opción
        assertEquals(20.0, floyd.getDistancias()[a][c], 0.001);
    }
}