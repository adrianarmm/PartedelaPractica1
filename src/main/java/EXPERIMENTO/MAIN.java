package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MAIN extends JFrame {
    private JFrame frame;
    private JTextField poblacionInicialField;
    private JTextField tasaCrecimientoField;
    private JTextField porcentajeEliminacionField;
    private JLabel poblacionInicialLabel;
    private JLabel tasaCrecimientoLabel;
    private JLabel porcentajeEliminacionLabel;
    private JButton ejecutarButton;
    private JTextArea detallesArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MAIN gui = new MAIN();
            gui.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Experimento de Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(7, 2));

        poblacionInicialLabel = new JLabel("Población inicial:");
        contentPane.add(poblacionInicialLabel);
        poblacionInicialField = new JTextField(10);
        poblacionInicialField.setText("1000");
        contentPane.add(poblacionInicialField);

        tasaCrecimientoLabel = new JLabel("Tasa de crecimiento (%):");
        contentPane.add(tasaCrecimientoLabel);
        tasaCrecimientoField = new JTextField(10);
        tasaCrecimientoField.setText("5");
        contentPane.add(tasaCrecimientoField);

        porcentajeEliminacionLabel = new JLabel("Porcentaje de eliminación (%):");
        contentPane.add(porcentajeEliminacionLabel);
        porcentajeEliminacionField = new JTextField(10);
        porcentajeEliminacionField.setText("70");
        contentPane.add(porcentajeEliminacionField);

        ejecutarButton = new JButton("Ejecutar experimento");
        contentPane.add(ejecutarButton);

        detallesArea = new JTextArea(10, 20);
        detallesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detallesArea);
        contentPane.add(scrollPane);

        ejecutarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int poblacionInicial;
                try {
                    poblacionInicial = Integer.parseInt(poblacionInicialField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Población inicial inválida");
                    return;
                }
                double tasaCrecimiento;
                try {
                    tasaCrecimiento = Double.parseDouble(tasaCrecimientoField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Tasa de crecimiento inválida");
                    return;
                }
                double porcentajeEliminacion;
                try {
                    porcentajeEliminacion = Double.parseDouble(porcentajeEliminacionField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Porcentaje de eliminación inválido");
                    return;
                }
                ejecutarExperimento(poblacionInicial, tasaCrecimiento, porcentajeEliminacion);
            }
        });

        frame.setVisible(true);
    }

    private void ejecutarExperimento(int poblacionInicial, double tasaCrecimiento, double porcentajeEliminacion) {
        int poblacionDespuesCrecimiento = crecimientoBacterias(poblacionInicial, tasaCrecimiento);
        detallesArea.append("Población después del crecimiento: " + poblacionDespuesCrecimiento + "\n");

        int poblacionDespuesEliminacion = eliminarBacterias(poblacionDespuesCrecimiento, porcentajeEliminacion);
        detallesArea.append("Población después de la eliminación: " + poblacionDespuesEliminacion + "\n");
    }

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        int variacion = rand.nextInt(11) - 5; // Variación aleatoria de +/- 5%
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
    }

    public static int eliminarBacterias(int poblacion, double porcentajeEliminacion) {
        int eliminadas = (int) (poblacion * porcentajeEliminacion);
        return poblacion - eliminadas;
    }
}
