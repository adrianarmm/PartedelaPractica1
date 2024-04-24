import java.io.*;
import java.util.Optional;

public class ExperimentoManager {

    public Experimento abrirExperimento(String fileName) {
        Experimento experimento = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            experimento = (Experimento) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada al deserializar: " + e.getMessage());
        }
        return experimento;
    }

    public Experimento crearNuevoExperimento() {
        return new Experimento();
    }

    public void agregarCultivoDeBacterias(Experimento experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento != null && bacteriaCulture != null) {
            experimento.agregarCultivoDeBacterias(bacteriaCulture);
        } else {
            System.out.println("Experimento o CultivoDeBacterias no pueden ser nulos");
        }
    }

    public void verNombresDeCultivosDeBacterias(Experimento experimento) {
        if (experimento != null) {
            experimento.getCultivoDeBacteriasList().forEach(cultivo -> System.out.println(cultivo.getNombre()));
        } else {
            System.out.println("Experimento no puede ser nulo");
        }
    }

    public void eliminarCultivoDeBacterias(Experimento experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento != null && bacteriaCulture != null) {
            experimento.getCultivoDeBacteriasList().remove(bacteriaCulture);
        } else {
            System.out.println("Experimento o CultivoDeBacterias no pueden ser nulos");
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(Experimento experimento, String nombreCultivo) {
        if (experimento != null) {
            Optional<CultivoDeBacterias> cultivo = experimento.getCultivoDeBacteriasList()
                    .stream()
                    .filter(c -> c.getNombre().equals(nombreCultivo))
                    .findFirst();
            cultivo.ifPresent(System.out::println);
        } else {
            System.out.println("Experimento no puede ser nulo");
        }
    }

    public void guardarExperimento(Experimento experimento, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
