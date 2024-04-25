
import java.util.Random;
public class ExperimentoBacterias {

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        int variacion = rand.nextInt(11) - 5; // Variación aleatoria de +/- 5%
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
}
    public static int eliminarBacterias(int poblacion, double porcentajeEliminacion) {
        int eliminadas = (int) (poblacion * porcentajeEliminacion);
        return poblacion - eliminadas;
    }