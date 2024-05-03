package EXPERIMENTO;

import java.io.*;
import java.util.Optional;


public class ExperimentoManageer {

    public experimentos abrirExperimento(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (experimentos) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public experimentos crearNuevoExperimento() {
        return new experimentos();
    }

    public void agregarCultivoDeBacterias(experimentos experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (bacteriaCulture == null) {
            System.err.println("Intento de agregar un cultivo nulo al experimento.");
            return;
        }
        experimento.agregarCultivoDeBacterias(bacteriaCulture);
    }

    public void verNombresDeCultivosDeBacterias(experimentos experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.mostrarCultivosDeBacterias();
    }

    public void eliminarCultivoDeBacterias(experimentos experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (bacteriaCulture == null) {
            System.err.println("Intento de eliminar un cultivo nulo del experimento.");
            return;
        }
        experimento.eliminarCultivoDeBacterias(bacteriaCulture);
    }


    public void verInformacionDetalladaDeCultivoDeBacterias(experimentos experimento, String nombreCultivo) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (nombreCultivo == null || nombreCultivo.isEmpty()) {
            System.err.println("Nombre de cultivo de bacterias no válido.");
            return;
        }
        Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.obtenerDetallesCultivo(nombreCultivo);
        cultivoDeBacterias.ifPresent(cultivo -> System.out.println(cultivo));
    }

    public void guardarExperimento(experimentos experimento, String fileName) throws IOException {
        if (experimento == null) {
            throw new IllegalArgumentException("No se puede guardar un experimento nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.err.println("Error al guardar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public void mostrarCultivosDeBacterias(experimentos experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("Experimento no puede ser nulo.");
        }
        experimento.mostrarCultivosDeBacterias();
    }

    public void eliminarCultivoDeBacterias(experimentos experimento, String nombreCultivo) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (nombreCultivo == null || nombreCultivo.isEmpty()) {
            System.err.println("Nombre de cultivo de bacterias no válido.");
            return;
        }
        Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.obtenerDetallesCultivo(nombreCultivo);
        cultivoDeBacterias.ifPresent(experimento::eliminarCultivoDeBacterias);
    }

    public void agregarCultivoDeBacterias(experimentos experimento, String nombre, int cantidad) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (nombre == null || nombre.isEmpty()) {
            System.err.println("Nombre de cultivo de bacterias no válido.");
            return;
        }
        CultivoDeBacterias cultivo = new CultivoDeBacterias(nombre, cantidad);
        experimento.agregarCultivoDeBacterias(cultivo);
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(experimentos experimento, CultivoDeBacterias cultivo) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        if (cultivo == null) {
            System.err.println("Cultivo de bacterias no válido.");
            return;
        }
        Optional<CultivoDeBacterias> cultivoDeBacterias = experimento.obtenerDetallesCultivo(cultivo.getNombre());
        cultivoDeBacterias.ifPresent(c -> System.out.println(c));
    }

    public void guardarExperimento(experimentos experimento, File file) throws IOException {
        if (experimento == null) {
            throw new IllegalArgumentException("No se puede guardar un experimento nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(experimento);
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.err.println("Error al guardar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public void abrirExperimento(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            experimentos experimento = (experimentos) ois.readObject();
            System.out.println(experimento);
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el experimento: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
ExperimentoManageer experimentoManageer = new ExperimentoManageer();
        experimentos experimento = experimentoManageer.crearNuevoExperimento();
        experimentoManageer.agregarCultivoDeBacterias(experimento, "Bacteria 1", 100);
        experimentoManageer.agregarCultivoDeBacterias(experimento, "Bacteria 2", 200);
        experimentoManageer.agregarCultivoDeBacterias(experimento, "Bacteria 3", 300);
        experimentoManageer.mostrarCultivosDeBacterias(experimento);
        experimentoManageer.eliminarCultivoDeBacterias(experimento, "Bacteria 2");
        experimentoManageer.mostrarCultivosDeBacterias(experimento);
        experimentoManageer.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 1");
        experimentoManageer.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 2");
        experimentoManageer.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 3");
    }
}
