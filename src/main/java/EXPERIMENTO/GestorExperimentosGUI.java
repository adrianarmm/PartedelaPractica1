package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Random;

public class GestorExperimentosGUI {
    private JFrame marco;
    private JTextField campoNombre, campoCantidadInicial, campoTasaCrecimiento;
    private JButton botonAgregar, botonEliminar, botonMostrarInfo, botonGuardar, botonCargar, botonSimularCrecimiento, botonSimularEliminacion;
    private JList<String> listaCultivos;
    private DefaultListModel<String> modeloLista;
    private ExperimentoManageer manejadorExperimentos;
    private experimentos experimentoActual;

    public GestorExperimentosGUI() throws IOException {
        manejadorExperimentos = new ExperimentoManageer();
        experimentoActual = manejadorExperimentos.crearNuevoExperimento();
        crearGUI();
    }

    private void crearGUI() {
        marco = new JFrame("Gestor de Experimentos de Cultivos Bacterianos");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(700, 500);
        marco.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(4, 2));
        campoNombre = new JTextField(20);
        campoCantidadInicial = new JTextField(20);
        campoTasaCrecimiento = new JTextField(20);

        panelSuperior.add(new JLabel("Nombre del Cultivo:"));
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Cantidad Inicial:"));
        panelSuperior.add(campoCantidadInicial);
        panelSuperior.add(new JLabel("Tasa de Crecimiento (%):"));
        panelSuperior.add(campoTasaCrecimiento);

        botonAgregar = new JButton("Agregar Cultivo");
        botonEliminar = new JButton("Eliminar Cultivo");
        botonSimularCrecimiento = new JButton("Simular Crecimiento");
        botonSimularEliminacion = new JButton("Simular Eliminación");

        panelSuperior.add(botonAgregar);
        panelSuperior.add(botonEliminar);

        botonAgregar.addActionListener(this::agregarCultivo);
        botonEliminar.addActionListener(this::eliminarCultivo);
        botonSimularCrecimiento.addActionListener(this::simularCrecimiento);
        botonSimularEliminacion.addActionListener(this::simularEliminacion);

        modeloLista = new DefaultListModel<>();
        listaCultivos = new JList<>(modeloLista);
        JScrollPane desplazamiento = new JScrollPane(listaCultivos);
        marco.add(desplazamiento, BorderLayout.CENTER);

        botonMostrarInfo = new JButton("Mostrar Información");
        botonGuardar = new JButton("Guardar Experimento");
        botonCargar = new JButton("Cargar Experimento");

        botonMostrarInfo.addActionListener(this::mostrarInformacion);
        botonGuardar.addActionListener(this::guardarExperimento);
        botonCargar.addActionListener(this::cargarExperimento);

        JPanel panelInferior = new JPanel();
        panelInferior.add(botonMostrarInfo);
        panelInferior.add(botonGuardar);
        panelInferior.add(botonCargar);
        panelInferior.add(botonSimularCrecimiento);
        panelInferior.add(botonSimularEliminacion);
        marco.add(panelInferior, BorderLayout.SOUTH);

        marco.setVisible(true);
    }

    private void cargarExperimento(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(marco);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                experimentoActual = (experimentos) ois.readObject();
                modeloLista.clear();
                // Verifica que el objeto y la lista no sean nulos.
                if (experimentoActual != null && experimentoActual.getCultivoDeBacteriasList() != null) {
                    // Añade cada cultivo al modelo de la lista para visualización.
                    for (CultivoDeBacterias cultivo : experimentoActual.getCultivoDeBacteriasList()) {
                        modeloLista.addElement(cultivo.getNombre() + " - " + cultivo.getCantidad());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(marco, "Error al cargar el experimento: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }



    private void guardarExperimento(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(marco);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(experimentoActual);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void mostrarInformacion(ActionEvent actionEvent) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            JOptionPane.showMessageDialog(marco, experimentoActual.obtenerDetallesCultivo(nombre));
        }
    }

    private void eliminarCultivo(ActionEvent actionEvent) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            CultivoDeBacterias cultivoParaEliminar;
            cultivoParaEliminar = (CultivoDeBacterias) experimentoActual.getCultivoDeBacteriasList().stream()
                    .filter(c -> c.getClass().equals(nombre))
                    .findFirst().orElse(null);
            if (cultivoParaEliminar != null) {
                experimentoActual.eliminarCultivoDeBacterias(cultivoParaEliminar);
                modeloLista.remove(indice);
            }
        }
    }

    private void agregarCultivo(ActionEvent e) {
        String nombre = campoNombre.getText();
        try {
            int cantidadInicial = Integer.parseInt(campoCantidadInicial.getText());
            CultivoDeBacterias nuevoCultivo = new CultivoDeBacterias(nombre, cantidadInicial);
            experimentoActual.agregarCultivoDeBacterias(nuevoCultivo);
            modeloLista.addElement(nombre + " - " + cantidadInicial);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(marco, "Por favor ingrese un número válido para la cantidad inicial.");
        }
    }

    private void simularCrecimiento(ActionEvent e) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            int cantidadInicial = Integer.parseInt(modeloLista.get(indice).split(" - ")[1]);
            double tasaCrecimiento = Double.parseDouble(campoTasaCrecimiento.getText()) / 100;
            int nuevaPoblacion = ExperimentoBacterias.crecimientoBacterias(cantidadInicial, tasaCrecimiento);
            modeloLista.set(indice, nombre + " - " + nuevaPoblacion);
            JOptionPane.showMessageDialog(marco, "Población después del crecimiento: " + nuevaPoblacion);
        }
    }

    private void simularEliminacion(ActionEvent e) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            int cantidadInicial = Integer.parseInt(modeloLista.get(indice).split(" - ")[1]);
            double porcentajeEliminacion = Double.parseDouble(JOptionPane.showInputDialog(marco, "Ingrese el porcentaje de eliminación:")) / 100;
            int poblacionPostEliminacion = ExperimentoBacterias.eliminarBacterias(cantidadInicial, porcentajeEliminacion);
            modeloLista.set(indice, nombre + " - " + poblacionPostEliminacion);
            JOptionPane.showMessageDialog(marco, "Población después de la eliminación: " + poblacionPostEliminacion);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GestorExperimentosGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
