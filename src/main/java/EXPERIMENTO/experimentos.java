package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class experimentos extends JFrame {
    private List<CultivoDeBacterias> cultivoDeBacteriasList;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaCultivos;
    private JTextField nombreField, cantidadField;
    private JTextArea detallesArea;

    public experimentos() {
        cultivoDeBacteriasList = new ArrayList<>();
        initUI();
    }

    private void initUI() {
        setTitle("Gestor de Cultivos de Bacterias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaCultivos = new JList<>(modeloLista);
        listaCultivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCultivos.addListSelectionListener(e -> mostrarDetallesCultivo());

        JScrollPane scrollPane = new JScrollPane(listaCultivos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        nombreField = new JTextField(10);
        cantidadField = new JTextField(10);
        JButton agregarBtn = new JButton("Agregar");
        JButton eliminarBtn = new JButton("Eliminar");

        agregarBtn.addActionListener(this::agregarCultivo);
        eliminarBtn.addActionListener(this::eliminarCultivo);

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(agregarBtn);
        panel.add(eliminarBtn);

        add(panel, BorderLayout.NORTH);

        detallesArea = new JTextArea(5, 20);
        detallesArea.setEditable(false);
        add(new JScrollPane(detallesArea), BorderLayout.SOUTH);
    }

    private void agregarCultivo(ActionEvent e) {
        String nombre = nombreField.getText();
        int cantidad = Integer.parseInt(cantidadField.getText());
        CultivoDeBacterias cultivo = new CultivoDeBacterias(nombre, cantidad);
        cultivoDeBacteriasList.add(cultivo);
        modeloLista.addElement(nombre + " - " + cantidad);
        nombreField.setText("");
        cantidadField.setText("");
    }

    private void eliminarCultivo(ActionEvent e) {
        int index = listaCultivos.getSelectedIndex();
        if (index != -1) {
            cultivoDeBacteriasList.remove(index);
            modeloLista.remove(index);
        }
    }

    private void mostrarDetallesCultivo() {
        int index = listaCultivos.getSelectedIndex();
        if (index != -1) {
            String nombre = modeloLista.getElementAt(index).split(" - ")[0];
            Optional<CultivoDeBacterias> cultivo = cultivoDeBacteriasList.stream()
                    .filter(c -> c.getNombre().equals(nombre))
                    .findFirst();
            detallesArea.setText(cultivo.map(CultivoDeBacterias::toString).orElse("Cultivo no encontrado."));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new experimentos().setVisible(true);
        });
    }

    public Iterable<Object> getCultivoDeBacteriasList() {
                return null;
    }

    public void eliminarCultivoDeBacterias(CultivoDeBacterias cultivoParaEliminar)
    {

    }

    public void agregarCultivoDeBacterias(CultivoDeBacterias nuevoCultivo) {

    }
}
