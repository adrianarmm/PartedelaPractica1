package EXPERIMENTO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class MAIN {
    private String nombre;
    private int cantidad;

    public MAIN (String nombre, int cantidad) {
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

    public static void imprimirDetalles(JFrame frame, int poblacion, double tasaCrecimiento) {
        int nuevaPoblacion = crecimientoBacterias(poblacion, tasaCrecimiento);
        int diferenciaCrecimiento = nuevaPoblacion - poblacion;
        double porcentajeCambio = ((double) diferenciaCrecimiento / poblacion) * 100;

        JOptionPane.showMessageDialog(frame,
                "<html>Información detallada de la población de bacterias:<br>" +
                        "Población inicial: " + poblacion + "<br>" +
                        "Tasa de crecimiento: " + (tasaCrecimiento * 100) + "%" + "<br>" +
                        "---- Simulación de crecimiento ----<br>" +
                        "Población después del crecimiento: " + nuevaPoblacion + "<br>" +
                        "Aumento de la población: " + diferenciaCrecimiento + "<br>" +
                        "Porcentaje de cambio: " + String.format("%.2f", porcentajeCambio) + "%" + "</html>");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cultivo de Bacterias y Simulación de Crecimiento");
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
            CultivoDeBacterias cultivo = new CultivoDeBacterias(nombre, cantidad);
            imprimirDetalles(frame, cantidad, tasaCrecimiento);
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(growthRateLabel);
        frame.add(growthRateField);
        frame.add(simulateButton);

        frame.setVisible(true);
    }
}