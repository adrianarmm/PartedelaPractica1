import java.util.Random;

public class DetallesPoblacionBacterias {

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        int variacion = rand.nextInt(11) - 5; // Variación aleatoria de +/- 5%
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
}
    public static void imprimirDetalles(int poblacion, double tasaCrecimiento) {
        System.out.println("Información detallada de la población de bacterias:");
        System.out.println("Población inicial: " + poblacion);
        System.out.println("Tasa de crecimiento: " + (tasaCrecimiento * 100) + "%");

        System.out.println("---- Simulación de crecimiento ----");
        int nuevaPoblacion = crecimientoBacterias(poblacion, tasaCrecimiento);
        System.out.println("Población después del crecimiento: " + nuevaPoblacion);
        int diferenciaCrecimiento = nuevaPoblacion - poblacion;
        System.out.println("Aumento de la población: " + diferenciaCrecimiento);