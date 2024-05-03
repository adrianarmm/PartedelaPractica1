package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MAIN {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField cantidadField;
    private JLabel nombreLabel;
    private JLabel cantidadLabel;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton verNombresButton;
    private JButton verDetallesButton;
    private JButton guardarButton;
    private JButton abrirButton;
    private JTextArea detallesArea;
    private experimentos experimento;
    private ExperimentoManageer ExperimentoManageer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MAIN gui = new MAIN();
            gui.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Experimento Manageer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(9, 2));

        nombreLabel = new JLabel("Nombre:");
        contentPane.add(nombreLabel);
        nombreField = new JTextField(10);
        contentPane.add(nombreField);

        cantidadLabel = new JLabel("Cantidad:");
        contentPane.add(cantidadLabel);
        cantidadField = new JTextField(10);
        contentPane.add(cantidadField);

        agregarButton = new JButton("Agregar cultivo");
        contentPane.add(agregarButton);
        eliminarButton = new JButton("Eliminar cultivo");
        contentPane.add(eliminarButton);
        verNombresButton = new JButton("Ver nombres de cultivos");
        contentPane.add(verNombresButton);
        verDetallesButton = new JButton("Ver detalles de cultivo");
        contentPane.add(verDetallesButton);
        guardarButton = new JButton("Guardar experimento");
        contentPane.add(guardarButton);
        abrirButton = new JButton("Abrir experimento");
        contentPane.add(abrirButton);

        detallesArea = new JTextArea(10, 20);
        detallesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detallesArea);
        contentPane.add(scrollPane);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                    return;
                }
                agregarCultivoDeBacterias(nombre, cantidad);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                eliminarCultivoDeBacterias(nombre);
            }
        });

        verNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verNombresDeCultivosDeBacterias();
            }
        });

        verDetallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre =nombreField.getText();
                verInformacionDetalladaDeCultivoDeBacterias(nombre);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("experimento.dat");
                guardarExperimento(experimento, file);
            }
        });

        abrirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("experimento.dat");
                try {
                    abrirExperimento(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.setVisible(true);
    }

    private void abrirExperimento(File file) throws IOException, ClassNotFoundException {
        try {
            ExperimentoManageer.abrirExperimento(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        detallesArea.append("Abierto experimento desde: " + file.getAbsolutePath() + "\n");
    }

    private void guardarExperimento(experimentos experimento, File file) {
        try {
            ExperimentoManageer.guardarExperimento(experimento, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        detallesArea.append("Guardado experimento en: " + file.getAbsolutePath() + "\n");
    }

    private void agregarCultivoDeBacterias(String nombre, int cantidad) {
        ExperimentoManageer.agregarCultivoDeBacterias(experimento, new CultivoDeBacterias(nombre, cantidad));
        detallesArea.append("Agregado cultivo: " + nombre + ", cantidad: " + cantidad + "\n");
    }

    private void eliminarCultivoDeBacterias(String nombre) {
        ExperimentoManageer.eliminarCultivoDeBacterias(experimento, new CultivoDeBacterias(nombre, 0));
        detallesArea.append("Eliminado cultivo: " + nombre + "\n");
    }

    private void verNombresDeCultivosDeBacterias() {
        ExperimentoManageer.verNombresDeCultivosDeBacterias(experimento);
    }

    private void verInformacionDetalladaDeCultivoDeBacterias(String nombre) {
        ExperimentoManageer.verInformacionDetalladaDeCultivoDeBacterias(experimento, nombre);
    }

    public void setExperimentoManageer(ExperimentoManageer experimentoManageer) {
        this.ExperimentoManageer = experimentoManageer;
    }

    public void setExperimento(experimentos experimento) {
        this.experimento = experimento;
    }
}