package EXPERIMENTO;

import java.io.*;
import java.util.Optional;

public class ExperimentoManager {

    public experimentos abrirExperimento(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (experimentos) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public experimentos crearNuevoExperimento() {
        return new experimentos();
    }

    public void agregarCultivoDeBacterias(experimentos experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("Experimento y CultivoDeBacterias no pueden ser nulos.");
        }
        experimento.agregarCultivoDeBacterias(bacteriaCulture);
    }

    public void verNombresDeCultivosDeBacterias(experimentos experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.getCultivoDeBacteriasList().forEach(cultivo -> System.out.println(cultivo.getNombre()));
    }

    public void eliminarCultivoDeBacterias(experimentos experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("Experimento y CultivoDeBacterias no pueden ser nulos.");
        }
        if (!experimento.getCultivoDeBacteriasList().remove(bacteriaCulture)) {
            System.out.println("El cultivo no se encontró en el experimento.");
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(experimentos experimento, String nombreCultivo) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.getCultivoDeBacteriasList().stream()
                .filter(cultivo -> cultivo.getNombre().equals(nombreCultivo))
                .findFirst();
        cultivoDeBacterias.ifPresentOrElse(System.out::println, () -> System.out.println("Cultivo no encontrado."));
    }

    public void guardarExperimento(experimentos experimento, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.out.println("Error al guardar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public void mostrarCultivosDeBacterias(experimentos experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.mostrarCultivosDeBacterias();
    }

    public static void main(String[] args) {
        ExperimentoManager experimentoManager = new ExperimentoManager();
        experimentos experimento = experimentoManager.crearNuevoExperimento();
        CultivoDeBacterias cultivoDeBacterias1 = new CultivoDeBacterias("Bacteria 1", 100);
        CultivoDeBacterias cultivoDeBacterias2 = new CultivoDeBacterias("Bacteria 2", 200);
        CultivoDeBacterias cultivoDeBacterias3 = new CultivoDeBacterias("Bacteria 3", 300);
        experimentoManager.agregarCultivoDeBacterias(experimento);
        experimentoManager.agregarCultivoDeBacterias(experimento);
        experimentoManager.agregarCultivoDeBacterias(experimento);
        experimentoManager.mostrarCultivosDeBacterias(experimento);
    }
}
