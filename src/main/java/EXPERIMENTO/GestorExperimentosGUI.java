package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class GestorExperimentosGUI {
    private JFrame marco;
    private JTextField campoNombre;
    private JTextField campoCantidadInicial;
    private JButton botonAgregar, botonEliminar, botonMostrarInfo, botonGuardar, botonCargar;
    private JList<String> listaCultivos;
    private DefaultListModel<String> modeloLista;
    private ManejadorExperimentos manejadorExperimentos;
    private Experimentos experimentoActual;

    public GestorExperimentosGUI() throws IOException {
        manejadorExperimentos = new ManejadorExperimentos();
        experimentoActual = manejadorExperimentos.crearNuevoExperimento();
        crearGUI();
    }

    private void crearGUI() {
        marco = new JFrame("Gestor de Experimentos de Cultivos Bacterianos");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(600, 400);
        marco.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(3, 2));
        JLabel etiquetaNombre = new JLabel("Nombre del Cultivo:");
        campoNombre = new JTextField(20);
        JLabel etiquetaCantidadInicial = new JLabel("Cantidad Inicial:");
        campoCantidadInicial = new JTextField(20);
        botonAgregar = new JButton("Agregar Cultivo");
        botonEliminar = new JButton("Eliminar Cultivo");

        panelSuperior.add(etiquetaNombre);
        panelSuperior.add(campoNombre);
        panelSuperior.add(etiquetaCantidadInicial);
        panelSuperior.add(campoCantidadInicial);
        panelSuperior.add(botonAgregar);
        panelSuperior.add(botonEliminar);

        botonAgregar.addActionListener(this::agregarCultivo);
        botonEliminar.addActionListener(this::eliminarCultivo);

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
        marco.add(panelInferior, BorderLayout.SOUTH);

        marco.setVisible(true);
    }

    private void agregarCultivo(ActionEvent e) {
        String nombre = campoNombre.getText();
        try {
            int cantidadInicial = Integer.parseInt(campoCantidadInicial.getText());
            CultivoDeBacterias cultivo = new CultivoDeBacterias(nombre, cantidadInicial);
            experimentoActual.agregarCultivoDeBacterias(cultivo);
            modeloLista.addElement(nombre + " - " + cantidadInicial);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(marco, "Por favor ingrese un número válido para la cantidad inicial.");
        }
    }

    private void eliminarCultivo(ActionEvent e) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            experimentoActual.eliminarCultivoDeBacterias(new CultivoDeBacterias(nombre, 0));
            modeloLista.remove(indice);
        }
    }

    private void mostrarInformacion(ActionEvent e) {
        int indice = listaCultivos.getSelectedIndex();
        if (indice != -1) {
            String nombre = modeloLista.get(indice).split(" - ")[0];
            JOptionPane.showMessageDialog(marco, experimentoActual.obtenerDetallesCultivo(nombre));
        }
    }

    private void guardarExperimento(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser();
        if (selectorArchivo.showSaveDialog(marco) == JFileChooser.APPROVE_OPTION) {
            try {
                manejadorExperimentos.guardarExperimento(experimentoActual, selectorArchivo.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(marco, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }

    private void cargarExperimento(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser();
        if (selectorArchivo.showOpenDialog(marco) == JFileChooser.APPROVE_OPTION) {
            try {
                experimentoActual = manejadorExperimentos.abrirExperimento(selectorArchivo.getSelectedFile().getAbsolutePath());
                modeloLista.clear();
                for (CultivoDeBacterias cultivo : experimentoActual.getCultivoDeBacteriasList()) {
                    modeloLista.addElement(cultivo.getNombre() + " - " + cultivo.getCantidad());
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(marco, "Error al cargar el archivo: " + ex.getMessage());
            }
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
