import java.io.*;
import java.util.Optional;

public class ExperimentoManager {

    public Experimento abrirExperimento(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Experimento) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public Experimento crearNuevoExperimento() {
        return new Experimento();
    }

    public void agregarCultivoDeBacterias(Experimento experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("Experimento y CultivoDeBacterias no pueden ser nulos.");
        }
        experimento.agregarCultivoDeBacterias(bacteriaCulture);
    }

    public void verNombresDeCultivosDeBacterias(Experimento experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.getCultivoDeBacteriasList().forEach(cultivo -> System.out.println(cultivo.getNombre()));
    }

    public void eliminarCultivoDeBacterias(Experimento experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("Experimento y CultivoDeBacterias no pueden ser nulos.");
        }
        if (!experimento.getCultivoDeBacteriasList().remove(bacteriaCulture)) {
            System.out.println("El cultivo no se encontró en el experimento.");
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(Experimento experimento, String nombreCultivo) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.getCultivoDeBacteriasList().stream()
                .filter(cultivo -> cultivo.getNombre().equals(nombreCultivo))
                .findFirst();
        cultivoDeBacterias.ifPresentOrElse(System.out::println, () -> System.out.println("Cultivo no encontrado."));
    }

    public void guardarExperimento(Experimento experimento, String fileName) throws IOException {
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

    public void mostrarCultivosDeBacterias(Experimento experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.mostrarCultivosDeBacterias();
    }
}
