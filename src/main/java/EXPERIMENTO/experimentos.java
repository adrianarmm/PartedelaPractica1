package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
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


    }

    public void mostrarCultivosDeBacterias() {
        modeloLista.clear();
        cultivoDeBacteriasList.forEach(cultivo -> modeloLista.addElement(cultivo.getNombre()));
    }

    public Optional<Object> getCultivoDeBacteriasList() {
        return Optional.ofNullable(cultivoDeBacteriasList);
    }

    public Optional<CultivoDeBacterias> obtenerDetallesCultivo(String nombre) {
        return cultivoDeBacteriasList.stream()
                .filter(cultivo -> cultivo.getNombre().equals(nombre))
                .findFirst();
    }

    public void eliminarCultivoDeBacterias(CultivoDeBacterias cultivoParaEliminar) {
        cultivoDeBacteriasList.remove(cultivoParaEliminar);

    }

    public void agregarCultivoDeBacterias(CultivoDeBacterias nuevoCultivo) {
        cultivoDeBacteriasList.add(nuevoCultivo);
    }
}
