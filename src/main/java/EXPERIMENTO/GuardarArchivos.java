package EXPERIMENTO;

import javax.swing.*;
import java.io.*;

public class GuardarArchivos extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public GuardarArchivos() {
        initializeComponents();
        setUpFrame();
    }

    private void initializeComponents() {
        textArea = new JTextArea();
        fileChooser = new JFileChooser();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem saveItem = new JMenuItem("Guardar");
        JMenuItem saveAsItem = new JMenuItem("Guardar como");

        saveItem.addActionListener(e -> saveFile());
        saveAsItem.addActionListener(e -> saveFileAs());

        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        add(scrollPane);
    }

    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveFile() {
        if (currentFile != null) {
            writeFile(currentFile);
        } else {
            saveFileAs();
        }
    }

    private void saveFileAs() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            writeFile(currentFile);
        }
    }

    private void writeFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuardarArchivos());
    }
}


