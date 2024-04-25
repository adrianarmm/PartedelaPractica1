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

class Experimentos {
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
}

class Main {
    public static void main(String[] args) {
        // Crear una población de bacterias
        PoblacionBacterias poblacion1 = new PoblacionBacterias("Poblacion1", "2024-04-01", "2024-04-10", 100,
                "25°C", "Alta", new int[]{10, 15, 20, 30});

        Experimentos experimentos = new Experimentos();
        experimentos.agregarPoblacionBacterias(poblacion1);

        List<String> nombresPoblaciones = experimentos.nombresPoblacionesBacterias();
        if (!nombresPoblaciones.isEmpty()) {
            System.out.println("Nombres de las poblaciones de bacterias en el experimento actual:");
            for (String nombre : nombresPoblaciones) {
                System.out.println(nombre);
            }
        } else {
            System.out.println("No hay poblaciones de bacterias en el experimento actual.");
        }
    }
}