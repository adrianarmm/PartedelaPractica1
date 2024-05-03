package EXPERIMENTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class experimentos {
    private List<CultivoDeBacterias> cultivoDeBacteriasList;

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

    // Añadido para permitir la eliminación de cultivos por objeto directamente
    public void eliminarCultivoDeBacterias(CultivoDeBacterias cultivo) {
        cultivoDeBacteriasList.remove(cultivo);
    }

    // Añadido para obtener detalles de un cultivo específico por nombre
    public String obtenerDetallesCultivo(String nombre) {
        Optional<CultivoDeBacterias> cultivo = cultivoDeBacteriasList.stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst();
        return cultivo.map(CultivoDeBacterias::toString)
                .orElse("Cultivo no encontrado.");
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
        experimento.eliminarCultivoDeBacterias(cultivoDeBacterias1); // Test eliminación
        System.out.println("Después de eliminar:");
        experimento.mostrarCultivosDeBacterias();
        System.out.println("Detalles del cultivo:");
        System.out.println(experimento.obtenerDetallesCultivo("Bacteria 2"));
    }
}
