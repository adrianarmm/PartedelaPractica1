package EXPERIMENTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class ExperimentoManager {

    public static experimentoss abrirExperimento(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (experimentoss) ois.readObject();
        }
    }

    public experimentoss crearNuevoExperimento() {
        return new experimentoss();
    }

    public void agregarCultivoDeBacterias(experimentoss experimento, Cultivo bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("El experimento o el cultivo de bacterias no pueden ser nulos.");
        }
        experimento.agregarCultivo(bacteriaCulture);
    }

    public void verNombresDeCultivosDeBacterias(experimentoss experimento) {
        if (experimento == null) {
            throw new IllegalArgumentException("El experimento no puede ser nulo.");
        }
        experimento.getNombresDeCultivosDeBacterias().forEach(System.out::println);
    }

    public void eliminarCultivoDeBacterias(experimentoss experimento, Cultivo bacteriaCulture) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException("El experimento o el cultivo de bacterias no pueden ser nulos.");
        }
        experimento.eliminarCultivo(bacteriaCulture.getNombre());
    }


    public void verInformacionDetalladaDeCultivoDeBacterias(experimentoss experimento, String nombreCultivo) {
        if (experimento == null || nombreCultivo == null || nombreCultivo.isEmpty()) {
            throw new IllegalArgumentException("El experimento o el nombre del cultivo no pueden ser nulos o vacíos.");
        }
        Cultivo cultivo = experimento.getCultivo(nombreCultivo);
        if (cultivo != null) {
            System.out.println(cultivo);
        } else {
            System.out.println("No se encontró el cultivo de bacterias: " + nombreCultivo);
        }
    }

    public void guardarExperimento(experimentoss experimento, String fileName) throws IOException {
        if (experimento == null) {
            throw new IllegalArgumentException("No se puede guardar un experimento nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(experimento);
        }
    }

    public static void main(String[] args) {
        ExperimentoManager experimentoManager = new ExperimentoManager();
        experimentoss experimento = experimentoManager.crearNuevoExperimento();
        experimentoManager.agregarCultivoDeBacterias(experimento, new Cultivo("Bacteria 1", 100, new Bacterias("O1", "P1", new ArrayList<>())));
        experimentoManager.agregarCultivoDeBacterias(experimento, new Cultivo("Bacteria 2", 200, new Bacterias("O2", "P2", new ArrayList<> ())));
        experimentoManager.agregarCultivoDeBacterias(experimento, new Cultivo("Bacteria 3", 300, new Bacterias("O3", "P3", new ArrayList<>())));
        experimentoManager.verNombresDeCultivosDeBacterias(experimento);
        experimentoManager.eliminarCultivoDeBacterias(experimento, experimento.getCultivo("Bacteria 2"));
        experimentoManager.verNombresDeCultivosDeBacterias(experimento);
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 1");
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 2");
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias(experimento, "Bacteria 3");
    }
}
