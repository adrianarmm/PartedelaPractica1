import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Experimento {
    private List<CultivoDeBacterias> cultivoDeBacteriasList;

    // Constructor que inicializa la lista de cultivos
    public Experimento() {
        this.cultivoDeBacteriasList = new ArrayList<>();
    }

    // Método para agregar un cultivo de bacterias al experimento
    public void agregarCultivoDeBacterias(CultivoDeBacterias cultivoDeBacterias) {
        if (cultivoDeBacterias != null) {
            cultivoDeBacteriasList.add(cultivoDeBacterias);
        } else {
            System.out.println("No se puede agregar un cultivo nulo.");
        }
    }

    // Método para mostrar todos los cultivos de bacterias
    public void mostrarCultivosDeBacterias() {
        if (cultivoDeBacteriasList.isEmpty()) {
            System.out.println("No hay cultivos de bacterias para mostrar.");
        } else {
            cultivoDeBacteriasList.forEach(System.out::println);
        }
    }

    // Método para obtener los nombres de todos los cultivos de bacterias
    public List<String> obtenerNombresDeCultivos() {
        return cultivoDeBacteriasList.stream()
                .map(CultivoDeBacterias::getNombre)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Experimento experimento = new Experimento();
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 1", 50));
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 2", 20));
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 3", 70));

        experimento.mostrarCultivosDeBacterias();
        System.out.println("Nombres de todos los cultivos: " + experimento.obtenerNombresDeCultivos());
    }
}
