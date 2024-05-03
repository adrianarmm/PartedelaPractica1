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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CultivoDeBacterias gui = new CultivoDeBacterias();
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
        contentPane.add(nombreField);

        cantidadLabel = new JLabel("Cantidad:");
        contentPane.add(cantidadLabel);
        cantidadField = new JTextField(10);
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

        CultivoDeBacterias cultivo = new CultivoDeBacterias("Cultivo 1", 1000);
        nombreField.setText(cultivo.getNombre());
        cantidadField.setText(String.valueOf(cultivo.getCantidad()));

        incrementarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                    return;
                }
                cultivo.incrementarCantidad(cantidad);
                cantidadField.setText(String.valueOf(cultivo.getCantidad()));
            }
        });

        decrementarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                    return;
                }
                cultivo.decrementarCantidad(cantidad);
                cantidadField.setText(String.valueOf(cultivo.getCantidad()));
            }
        });

        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cultivo.imprimirDetalles();
                detallesArea.setText(cultivo.getNombre() + "\n" + cultivo.getCantidad());
            }
        });

        frame.setVisible(true);
    }
}