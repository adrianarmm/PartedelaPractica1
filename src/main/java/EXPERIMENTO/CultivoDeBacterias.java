package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CultivoDeBacterias {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField cantidadField;
    private JLabel nombreLabel;
    private JLabel cantidadLabel;
    private JButton incrementarButton;
    private JButton decrementarButton;
    private JButton imprimirButton;
    private JTextArea detallesArea;
    private String nombre;
    private int cantidad;

    public CultivoDeBacterias(String s, int i) {
        this.nombre = s;
        this.cantidad = i;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CultivoDeBacterias gui = new CultivoDeBacterias("Cultivo 1", 1000);
            gui.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Cultivo de Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(6, 2));

        nombreLabel = new JLabel("Nombre:");
        contentPane.add(nombreLabel);
        nombreField = new JTextField(10);
        nombreField.setText(nombre);
        contentPane.add(nombreField);

        cantidadLabel = new JLabel("Cantidad:");
        contentPane.add(cantidadLabel);
        cantidadField = new JTextField(10);
        cantidadField.setText(String.valueOf(cantidad));
        contentPane.add(cantidadField);

        incrementarButton = new JButton("Incrementar cantidad");
        contentPane.add(incrementarButton);
        decrementarButton = new JButton("Decrementar cantidad");
        contentPane.add(decrementarButton);
        imprimirButton = new JButton("Imprimir detalles");
        contentPane.add(imprimirButton);

        detallesArea = new JTextArea(5, 20);
        detallesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detallesArea);
        contentPane.add(scrollPane);

        incrementarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newCantidad;
                try {
                    newCantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                    return;
                }
                incrementarCantidad(newCantidad);
                cantidadField.setText(String.valueOf(cantidad));
            }
        });

        decrementarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newCantidad;
                try {
                    newCantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                    return;
                }
                decrementarCantidad(newCantidad);
                cantidadField.setText(String.valueOf(cantidad));
            }
        });

        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirDetalles();
                detallesArea.setText("Cantidad: " + cantidad + "\n" +
                        "Cantidad Field: " +cantidadField.getText() + "\n" +
                        "Nombre: " + nombre + "\n" +
                        "Nombre Field: " + nombreField.getText());
            }
        });

        frame.setVisible(true);
    }

    private void decrementarCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad -= cantidad;
        } else {
            JOptionPane.showMessageDialog(frame, "La cantidad debe ser mayor que cero.");
        }
    }

    private void incrementarCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        } else {
            JOptionPane.showMessageDialog(frame, "La cantidad debe ser mayor que cero.");
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private void imprimirDetalles() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad: " + cantidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}