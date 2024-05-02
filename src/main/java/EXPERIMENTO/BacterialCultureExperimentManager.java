package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BacterialCultureExperimentManager {
    private JFrame frame;
    private JTextField nameField;
    private JTextField initialQuantityField;
    private JButton addButton;
    private JButton removeButton;
    private JButton displayInfoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JList<String> cultureList;
    private DefaultListModel<String> listModel;
    private ExperimentoManageer experimentManager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new BacterialCultureExperimentManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public BacterialCultureExperimentManager() throws IOException {
        experimentManager = new ExperimentoManager();
        currentExperiment = experimentManager.crearNuevoExperimento();  // Usar un solo experimento en la GUI
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Bacterial Culture Experiment Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel initialQuantityLabel = new JLabel("Initial Quantity:");
        initialQuantityField = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(initialQuantityLabel);
        topPanel.add(initialQuantityField);
        topPanel.add(addButton);
        topPanel.add(removeButton);

        addButton.addActionListener(this::addCulture);
        removeButton.addActionListener(this::removeCulture);

        frame.add(topPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        cultureList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(cultureList);
        frame.add(scrollPane, BorderLayout.CENTER);

        displayInfoButton = new JButton("Display Info");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        displayInfoButton.addActionListener(this::displayInfo);
        saveButton.addActionListener(this::saveExperiment);
        loadButton.addActionListener(this::loadExperiment);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(displayInfoButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addCulture(ActionEvent e) {
        String name = nameField.getText();
        int initialQuantity;
        try {
            initialQuantity = Integer.parseInt(initialQuantityField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid initial quantity.");
            return;
        }
        CultivoDeBacterias culture = new CultivoDeBacterias(name, initialQuantity);
        experimentManager.agregarCultivoDeBacterias(currentExperiment, culture);
        listModel.addElement(name);
    }


    private void removeCulture() {
        int index = cultureList.getSelectedIndex();
        if (index != -1) {
            String name = listModel.get(index);
            experimentManager.eliminarCultivoDeBacterias(experimentManager.crearNuevoExperimento(), new CultivoDeBacterias(name, 0));
            listModel.remove(index);
        }
    }

    private void displayInfo() {
        int index = cultureList.getSelectedIndex();
        if (index != -1) {
            String name = listModel.get(index);
            experimentManager.verInformacionDetalladaDeCultivoDeBacterias(experimentManager.crearNuevoExperimento(), name);
        }
    }

    private void saveExperiment() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                experimentManager.guardarExperimento(experimentManager.crearNuevoExperimento(), fileName);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving the experiment: " + e.getMessage());
            }
        }
    }

    private void loadExperiment() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                experimentManager.abrirExperimento(fileName);
                listModel.clear();
                for (CultivoDeBacterias culture : experimentManager.crearNuevoExperimento().getCultivoDeBacteriasList()) {
                    listModel.addElement(culture.getNombre());
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Error loading the experiment: " + e.getMessage());
            }
        }
    }
}