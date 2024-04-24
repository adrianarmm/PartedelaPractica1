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
            System.out.println("Experimento o CultivoDeBacterias no pueden ser nulos.");
        }
    }

    public void verNombresDeCultivosDeBacterias(Experimento experimento) {
        if (experimento != null) {
            experimento.getCultivoDeBacteriasList().forEach(cultivo -> System.out.println(cultivo.getNombre()));
        } else {
            System.out.println("Experimento no puede ser nulo.");
        }
    }

    public void eliminarCultivoDeBacterias(Experimento experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento != null && bacteriaCulture != null) {
            boolean removed = experimento.getCultivoDeBacteriasList().remove(bacteriaCulture);
            if (!removed) {
                System.out.println("El cultivo no se encontró en el experimento.");
            }
        } else {
            System.out.println("Experimento o CultivoDeBacterias no pueden ser nulos.");
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(Experimento experimento, String nombreCultivo) {
        if (experimento != null) {
            Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.getCultivoDeBacteriasList().stream()
                    .filter(cultivo -> cultivo.getNombre().equals(nombreCultivo))
                    .findFirst();
            cultivoDeBacterias.ifPresentOrElse(System.out::println, () -> System.out.println("Cultivo no encontrado."));
        } else {
            System.out.println("Experimento no puede ser nulo.");
        }

    }

    public void guardarExperimento(Experimento experimento, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public void mostrarCultivosDeBacterias(Experimento experimento) {
        if (experimento != null) {
            experimento.mostrarCultivosDeBacterias();
        } else {
            System.out.println("Experimento no puede ser nulo.");
        }
    }

    public static void main(String[] args) {
        ExperimentoManager experimentoManager = new ExperimentoManager();
        Experimento experimento = experimentoManager.crearNuevoExperimento();
        CultivoDeBacterias cultivoDeBacterias1 = new CultivoDeBacterias("Bacteria 1", 100);
        CultivoDeBacterias cultivoDeBacterias2 = new CultivoDeBacterias("Bacteria 2", 200);
        CultivoDeBacterias cultivoDeBacterias3 = new CultivoDeBacterias("Bacteria 3", 300);
        experimentoManager.agregarCultivoDeBacterias(experimento, cultivoDeBacterias1);
        experimentoManager.agregarCultivoDeBacterias(experimento, cultivoDeBacterias2);
        experimentoManager.agregarCultivoDeBacterias(experimento, cultivoDeBacterias3);
        experimentoManager.mostrarCultivosDeBacterias(experimento);
        experimentoManager.guardarExperimento(experimento, "experimento.ser");
        Experimento experimentoCargado = experimentoManager.abrirExperimento("experimento.ser");
        experimentoManager.mostrarCultivosDeBacterias(experimentoCargado);
    }
}
