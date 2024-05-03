package EXPERIMENTO;

import java.io.*;
import java.util.ArrayList;

public class ExperimentoManager {

    public static experimentos abrirExperimento(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (experimentos) ois.readObject();
        }
    }

    public experimentos crearNuevoExperimento() {
        return new experimentos();
    }

    public void agregarCultivoDeBacterias(experimentos experimento, CultivoDeBacterias bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("El experimento o el cultivo de bacterias no pueden ser nulos.");
        }
        experimento.agregarCultivoDeBacterias(bacteriaCulture);
    }

    public void verNombresDeCultivosDeBacterias(experimentos experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        experimento.mostrarCultivosDeBacterias();
    }

    public void eliminarCultivoDeBacterias(experimentos experimento, String nombreCultivo) {
        if (experimento == null || nombreCultivo == null || nombreCultivo.isEmpty()) {
            throw new IllegalArgumentException("El experimento o el nombre del cultivo no pueden ser nulos o vacíos.");
        }
        experimento.eliminarCultivoDeBacterias(nombreCultivo);
    }


    public void verInformacionDetalladaDeCultivoDeBacterias(experimentos experimento, String nombreCultivo) {
        if (experimento == null || nombreCultivo == null || nombreCultivo.isEmpty()) {
            throw new IllegalArgumentException("El experimento o el nombre del cultivo no pueden ser nulos o vacíos.");
        }
        experimento.verInformacionDetalladaDeCultivoDeBacterias(nombreCultivo);
    }

    public void guardarExperimento(experimentos experimento, String fileName) throws IOException {
        if (experimento == null) {
            throw new IllegalArgumentException("No se puede guardar un experimento nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        }
    }

    public static void main(String[] args) {
        ExperimentoManager experimentoManager = new ExperimentoManager();
        experimentos experimento = experimentoManager.crearNuevoExperimento();
        experimentoManager.agregarCultivoDeBacterias(experimento, new CultivoDeBacterias("Bacteria 1", 100, new Bacterias("O1", "P1", new ArrayList<>())));
        experimentoManager.agregarCultivoDeBacterias(experimento, new CultivoDeBacterias("Bacteria 2", 200, new Bacterias("O2", "P2", new ArrayList<> ())));
        experimentoManager.agregarCultivoDeBacterias(experimento, new CultivoDeBacterias("Bacteria 3", 300, new Bacterias("O3", "P3", new ArrayList<>())));
        experimentoManager.verNombresDeCultivosDeBacterias(experimento);
        experimentoManager.eliminarCultivoDeBacterias(experimento, "Bacteria 2");
        experimentoManager.verNombresDeCultivosDeBacterias(experimento);
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 1");
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 2"); // Intentamos ver detalles de un cultivo eliminado
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 3");
        try {
            experimentoManager.guardarExperimento(experimento, "experimento.ser");
            System.out.println("Experimento guardado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar el experimento: " + e.getMessage());
        }
    }
}

