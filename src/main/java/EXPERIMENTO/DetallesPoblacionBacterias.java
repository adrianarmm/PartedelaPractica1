package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DetallesPoblacionBacterias {
    private JFrame frame;
    private JTextField poblacionInicialField;
    private JTextField tasaCrecimientoField;
    private JLabel poblacionInicialLabel;
    private JLabel tasaCrecimientoLabel;
    private JButton imprimirButton;
    private JTextArea detallesArea;

    public static void main(String[] args, String detalles) {
        SwingUtilities.invokeLater(() -> {
            DetallesPoblacionBacterias gui = new DetallesPoblacionBacterias();
            gui.createAndShowGUI(detalles);
        });
    }

    public void createAndShowGUI(String detalles) {
        frame = new JFrame("Detalles de Población de Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(5, 2));

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

        imprimirButton = new JButton("Imprimir detalles");
        contentPane.add(imprimirButton);

        detallesArea = new JTextArea(10, 20);
        detallesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detallesArea);
        contentPane.add(scrollPane);

        imprimirButton.addActionListener(new ActionListener() {
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
                imprimirDetalles(poblacionInicial, tasaCrecimiento);
                detallesArea.setText(detalles);
            }
        });

        frame.setVisible(true);
    }

    private void imprimirDetalles(int poblacion, double tasaCrecimiento) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Información detallada de la población de bacterias:\n");
        detalles.append("Población inicial: ").append(poblacion).append("\n");
        detalles.append("Tasa de crecimiento: ").append(tasaCrecimiento * 100).append("%\n");

        detalles.append("---- Simulación de crecimiento ----\n");
        int nuevaPoblacion = crecimientoBacterias(poblacion, tasaCrecimiento);
        detalles.append("Población después del crecimiento: ").append(nuevaPoblacion).append("\n");
        int diferenciaCrecimiento = nuevaPoblacion - poblacion;
        detalles.append("Aumento de la población: ").append(diferenciaCrecimiento).append("\n");

        double porcentajeCambio = ((double) diferenciaCrecimiento / poblacion) * 100;
        detalles.append("Porcentaje de cambio: ").append(porcentajeCambio).append("%\n");
    }

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        int variacion = rand.nextInt(11) - 5; // Variación aleatoria de +/- 5%
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
    }
}