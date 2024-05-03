package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class GestorExperimentosGUI {
    private JFrame marco;
    private JTextField campoNombre, campoCantidadInicial, campoTasaCrecimiento;
    private JButton botonAgregar, botonEliminar, botonMostrarInfo, botonGuardar, botonCargar, botonSimularCrecimiento;
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
        marco.setSize(700, 500); // Ajustado para dar espacio a nuevos controles
        marco.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(4, 2)); // Aumentado para incluir tasa de crecimiento
        JLabel etiquetaNombre = new JLabel("Nombre del Cultivo:");
        campoNombre = new JTextField(20);
        JLabel etiquetaCantidadInicial = new JLabel("Cantidad Inicial:");
        campoCantidadInicial = new JTextField(20);
        JLabel etiquetaTasaCrecimiento = new JLabel("Tasa de Crecimiento (%):");
        campoTasaCrecimiento = new JTextField(20);

        botonAgregar = new JButton("Agregar Cultivo");
        botonEliminar = new JButton("Eliminar Cultivo");
        botonSimularCrecimiento = new JButton("Simular Crecimiento");

        panelSuperior.add(etiquetaNombre);
        panelSuperior.add(campoNombre);
        panelSuperior.add(etiquetaCantidadInicial);
        panelSuperior.add(campoCantidadInicial);
        panelSuperior.add(etiquetaTasaCrecimiento);
        panelSuperior.add(campoTasaCrecimiento);
        panelSuperior.add(botonAgregar);
        panelSuperior.add(botonEliminar);

        int cantidad = 0;
        botonAgregar.addActionListener(actionEvent -> agregarCultivo(actionEvent, cantidad));
        botonEliminar.addActionListener(this::eliminarCultivo);
        botonSimularCrecimiento.addActionListener(this::simularCrecimiento);

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
        marco.add(panelInferior, BorderLayout.SOUTH);

        marco.setVisible(true);
    }

    private void guardarExperimento(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(marco);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(experimentoActual);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(marco, "Error al guardar el experimento: " + e.getMessage());
            }
        }
    }

    private void mostrarInformacion(ActionEvent actionEvent) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            int cantidad = Integer.parseInt(modeloLista.get(indice).split(" - ")[1]);
            JOptionPane.showMessageDialog(marco, "Nombre: " + nombre + "\nCantidad: " + cantidad);
        } else {
            JOptionPane.showMessageDialog(marco, "Seleccione un cultivo para mostrar información.");
        }
    }

    private void eliminarCultivo(ActionEvent actionEvent) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            modeloLista.remove(indice);
        } else {
            JOptionPane.showMessageDialog(marco, "Seleccione un cultivo para eliminar.");
        }
    }

    private void agregarCultivo(ActionEvent actionEvent, int cantidad) {
        String nombre = campoNombre.getText();
        int cantidadInicial = Integer.parseInt(campoCantidadInicial.getText());
        CultivoDeBacterias cultivo = new CultivoDeBacterias(nombre, cantidadInicial);
    }

    private void cargarExperimento(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(marco);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                experimentoActual = (experimentos) ois.readObject();
                modeloLista.clear();
                experimentoActual.getCultivoDeBacteriasList().forEach(cultivo -> modeloLista.addElement(cultivo.getNombre() + " - " + cultivo.getCantidadInicial()));
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(marco, "Error al cargar el experimento: " + e.getMessage());
            }
        }
    }

    private void simularCrecimiento(ActionEvent e) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            int poblacionInicial = Integer.parseInt(modeloLista.get(indice).split(" - ")[1]);
            double tasaCrecimiento = Double.parseDouble(campoTasaCrecimiento.getText()) / 100;
            int nuevaPoblacion = DetallesPoblacionBacterias.crecimientoBacterias(poblacionInicial, tasaCrecimiento);
            modeloLista.set(indice, nombre + " - " + nuevaPoblacion);
            JOptionPane.showMessageDialog(marco, "Población después del crecimiento: " + nuevaPoblacion);
        } else {
            JOptionPane.showMessageDialog(marco, "Seleccione un cultivo para simular el crecimiento.");
        }
    }

    // Métodos existentes agregarCultivo, eliminarCultivo, mostrarInformacion, guardarExperimento, cargarExperimento...

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
