package EXPERIMENTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class experimentos implements Serializable {
    private ArrayList<CultivoDeBacterias> cultivoDeBacteriasList;

    public experimentos() {
        cultivoDeBacteriasList = new ArrayList<>();
    }

    public void mostrarCultivosDeBacterias() {
        for (CultivoDeBacterias cultivo : cultivoDeBacteriasList) {
            System.out.println(cultivo.getNombre());
        }
    }

    public Optional<CultivoDeBacterias> obtenerDetallesCultivo( String nombre) {
        return cultivoDeBacteriasList.stream()
                .filter(cultivo -> cultivo.getNombre().equals(nombre))
                .findFirst();
    }

    public boolean eliminarCultivoDeBacterias(CultivoDeBacterias cultivoParaEliminar) {
        return cultivoDeBacteriasList.remove(cultivoParaEliminar);
    }

    public void agregarCultivoDeBacterias(CultivoDeBacterias nuevoCultivo) {
        cultivoDeBacteriasList.add(nuevoCultivo);
    }

    public void guardarExperimento(String ruta) throws IOException {
        // Implementación del método para guardar el experimento en la ruta proporcionada
    }


    public static experimentos abrirExperimento(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (experimentos) ois.readObject();
        }
    }




    public void verInformacionDetalladaDeCultivoDeBacterias(String nombreCultivo) {
        Optional<CultivoDeBacterias> cultivoOptional = obtenerDetallesCultivo(nombreCultivo);
        if (cultivoOptional.isPresent()) {
            CultivoDeBacterias cultivo = cultivoOptional.get();
            System.out.println("Información detallada de cultivo de bacterias:");
            System.out.println("Nombre: " + cultivo.getNombre());
            System.out.println("Cantidad: " + cultivo.getCantidad());
            Bacterias bacterias = cultivo.getBacterias();
            System.out.println("Características de bacterias:");
            System.out.println("- Colonia: " + bacterias.getColonia());
            System.out.println("- Genotipo: " + bacterias.getGenotipo());
            System.out.println("- Plásmidos: " + bacterias.getPlasmidos());
        } else {
            System.out.println("No se encontró el cultivo de bacterias: " + nombreCultivo);
        }
    }

    // Getters y setters para la lista de cultivos (opcional)
    public ArrayList<CultivoDeBacterias> getCultivoDeBacteriasList() {
        return cultivoDeBacteriasList;
    }

    public void setCultivoDeBacteriasList(ArrayList<CultivoDeBacterias> cultivoDeBacteriasList) {
        this.cultivoDeBacteriasList = cultivoDeBacteriasList;
    }
}
