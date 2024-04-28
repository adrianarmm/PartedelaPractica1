package EXPERIMENTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class PoblacionBacterias {
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private int numBacteriasIniciales;
    private String temperatura;
    private String luminosidad;
    private int[] dosisComida;

    public PoblacionBacterias(String nombre, String fechaInicio, String fechaFin, int numBacteriasIniciales,
                              String temperatura, String luminosidad, int[] dosisComida) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacteriasIniciales = numBacteriasIniciales;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Fecha de inicio: " + fechaInicio + "\n" +
                "Fecha de fin: " + fechaFin + "\n" +
                "Número de bacterias iniciales: " + numBacteriasIniciales + "\n" +
                "Temperatura: " + temperatura + "\n" +
                "Luminosidad: " + luminosidad + "\n" +
                "Dosis de comida: " + Arrays.toString(dosisComida);
    }
}

public class Experimentos {
    private List<PoblacionBacterias> poblacionesBacterias;

    public Experimentos() {
        poblacionesBacterias = new ArrayList<>();
    }

    public void agregarPoblacionBacterias(PoblacionBacterias poblacion) {
        poblacionesBacterias.add(poblacion);
    }

    public List<String> nombresPoblacionesBacterias() {
        List<String> nombres = new ArrayList<>();
        for (PoblacionBacterias poblacion : poblacionesBacterias) {
            nombres.add(poblacion.toString());
        }
        return nombres;
    }

    public static void main(String[] args) {
        Experimentos experimentos = new Experimentos();
        PoblacionBacterias poblacion1 = new PoblacionBacterias("Población 1", "01/01/2021", "01/02/2021", 1000, "25°C", "Alta", new int[]{10, 20, 30});
        PoblacionBacterias poblacion2 = new PoblacionBacterias("Población 2", "01/02/2021", "01/03/2021", 2000, "30°C", "Baja", new int[]{20, 30, 40});
        PoblacionBacterias poblacion3 = new PoblacionBacterias("Población 3", "01/03/2021", "01/04/2021", 3000, "35°C", "Media", new int[]{30, 40, 50});
        experimentos.agregarPoblacionBacterias(poblacion1);
        experimentos.agregarPoblacionBacterias(poblacion2);
        experimentos.agregarPoblacionBacterias(poblacion3);
        experimentos.nombresPoblacionesBacterias().forEach(System.out::println);
    }
}
