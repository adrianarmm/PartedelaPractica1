package EXPERIMENTO;

import java.util.ArrayList;
import java.util.List;

public class experimentos {
    private List<CultivoDeBacterias> cultivoDeBacteriasList; // Asegúrate de que la lista sea de tipo CultivoDeBacterias

    public experimentos() {
        this.cultivoDeBacteriasList = new ArrayList<>();
    }

    public void agregarCultivoDeBacterias(CultivoDeBacterias cultivoDeBacterias) {
        cultivoDeBacteriasList.add(cultivoDeBacterias);
    }

    public List<CultivoDeBacterias> getCultivoDeBacteriasList() {
        return cultivoDeBacteriasList;
    }

    public void setCultivoDeBacteriasList(List<CultivoDeBacterias> cultivoDeBacteriasList) {
        this.cultivoDeBacteriasList = cultivoDeBacteriasList;
    }

    public void mostrarCultivosDeBacterias() {
        cultivoDeBacteriasList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        experimentos experimento = new experimentos();
        CultivoDeBacterias cultivoDeBacterias1 = new CultivoDeBacterias("Bacteria 1", 100);
        CultivoDeBacterias cultivoDeBacterias2 = new CultivoDeBacterias("Bacteria 2", 200);
        CultivoDeBacterias cultivoDeBacterias3 = new CultivoDeBacterias("Bacteria 3", 300);
        experimento.agregarCultivoDeBacterias(cultivoDeBacterias1);
        experimento.agregarCultivoDeBacterias(cultivoDeBacterias2);
        experimento.agregarCultivoDeBacterias(cultivoDeBacterias3);
        experimento.mostrarCultivosDeBacterias();
    }

    public void agregarPoblacionBacterias(PoblacionBacterias poblacion1) {

    }

    public Iterable<Object> nombresPoblacionesBacterias() {
        return null;
    }

    public void eliminarCultivoDeBacterias(CultivoDeBacterias cultivoDeBacterias) {

    }
}
