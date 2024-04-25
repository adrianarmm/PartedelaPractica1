import java.util.ArrayList;
import java.util.List;

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
                "Dosis de comida: " + dosisComida;
    }
}

class Experimento {
    private List<PoblacionBacterias> poblacionesBacterias;

    public Experimento() {
        poblacionesBacterias = new ArrayList<>();
    }

    public void agregarPoblacionBacterias(PoblacionBacterias poblacion) {
        poblacionesBacterias.add(poblacion);
    }