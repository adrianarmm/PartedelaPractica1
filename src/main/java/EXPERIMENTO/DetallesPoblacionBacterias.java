package EXPERIMENTO;

import java.util.Random;

public class DetallesPoblacionBacterias {

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        // Calcular la nueva población con la tasa de crecimiento
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        // Aplicar una variación aleatoria de +/- 5%
        int variacion = rand.nextInt(11) - 5;
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
    }

    public static void imprimirDetalles(int poblacion, double tasaCrecimiento) {
        System.out.println("Información detallada de la población de bacterias:");
        System.out.println("Población inicial: " + poblacion);
        System.out.println("Tasa de crecimiento: " + (tasaCrecimiento * 100) + "%");

        System.out.println("---- Simulación de crecimiento ----");
        // Simular el crecimiento de la población
        int nuevaPoblacion = crecimientoBacterias(poblacion, tasaCrecimiento);
        System.out.println("Población después del crecimiento: " + nuevaPoblacion);
        int diferenciaCrecimiento = nuevaPoblacion - poblacion;
        System.out.println("Aumento de la población: " + diferenciaCrecimiento);

        double porcentajeCambio = ((double) diferenciaCrecimiento / poblacion) * 100;
        System.out.println("Porcentaje de cambio: " + porcentajeCambio + "%");
    }

    public static void main(String[] args) {
        int poblacionInicial = 1000; // Población inicial de bacterias
        double tasaCrecimiento = 0.05; // Tasa de crecimiento (5%)

        // Imprimir información detallada de la población de bacterias
        imprimirDetalles(poblacionInicial, tasaCrecimiento);
    }
}