package Guardar;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class GuardarArchivos extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public GuardarArchivos() {
        initializeComponents();
        setUpFrame();
    }
}


