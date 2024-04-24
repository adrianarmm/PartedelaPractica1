import java.util.List;


public class Experimento {
    private List<CultivoDeBacterias> cultivoDeBacteriasList;


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

    public Experimento() {
        this.cultivoDeBacteriasList = new ArrayList<>();
    }

    public List<CultivoDeBacterias> getCultivoDeBacteriasList() {
        return cultivoDeBacteriasList;
    }

    public void setCultivoDeBacteriasList(List<CultivoDeBacterias> cultivoDeBacteriasList) {
        this.cultivoDeBacteriasList = cultivoDeBacteriasList;
    }

}