import java.util.ArrayList;
import java.util.List;

public class Experimento {
    private List<CultivoDeBacterias> cultivoDeBacteriasList;

    public Experimento() {
        this.cultivoDeBacteriasList = new ArrayList<>();
    }

    public void agregarCultivoDeBacterias(CultivoDeBacterias cultivoDeBacterias) {
        cultivoDeBacteriasList.add(cultivoDeBacterias);
    }

    public void mostrarCultivosDeBacterias() {
        cultivoDeBacteriasList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Experimento experimento = new Experimento();
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 1", 50));
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 2", 20));
        experimento.agregarCultivoDeBacterias(new CultivoDeBacterias("Bacteria 3", 70));
        experimento.mostrarCultivosDeBacterias();
    }
}
