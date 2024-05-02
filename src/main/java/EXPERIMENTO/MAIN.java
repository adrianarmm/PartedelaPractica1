package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class MAIN {
    private String nombre;
    private int cantidad;

    public MAIN(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CultivoDeBacterias{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cultivo y Experimento de Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Nombre del cultivo:");
        JTextField nameField = new JTextField(15);
        JLabel quantityLabel = new JLabel("Población inicial:");
        JTextField quantityField = new JTextField(5);
        JLabel growthRateLabel = new JLabel("Tasa de crecimiento (%):");
        JTextField growthRateField = new JTextField(5);
        JLabel eliminationRateLabel = new JLabel("Porcentaje de eliminación (%):");
        JTextField eliminationRateField = new JTextField(5);
        JButton simulateButton = new JButton("Simular Crecimiento y Eliminación");

        simulateButton.addActionListener(e -> {
            String nombre = nameField.getText();
            int cantidad = Integer.parseInt(quantityField.getText());
            double tasaCrecimiento = Double.parseDouble(growthRateField.getText()) / 100;
            double porcentajeEliminacion = Double.parseDouble(eliminationRateField.getText()) / 100;

            int poblacionDespuesCrecimiento = crecimientoBacterias(cantidad, tasaCrecimiento);
            int poblacionDespuesEliminacion = eliminarBacterias(poblacionDespuesCrecimiento, porcentajeEliminacion);

            JOptionPane.showMessageDialog(frame,
                    "<html>Resultado del experimento con " + nombre + ":<br>" +
                            "Población inicial: " + cantidad + "<br>" +
                            "Población después del crecimiento: " + poblacionDespuesCrecimiento + "<br>" +
                            "Población después de la eliminación: " + poblacionDespuesEliminacion + "</html>");
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(growthRateLabel);
        frame.add(growthRateField);
        frame.add(eliminationRateLabel);
        frame.add(eliminationRateField);
        frame.add(simulateButton);

        frame.setVisible(true);
    }
}
