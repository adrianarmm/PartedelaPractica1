package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class MAIN extends JFrame implements ActionListener {

    public JTextField nombreField;
    public JTextField cantidadField;
    public JTextArea detallesArea;
    public JButton agregarCultivoButton;
    public JButton eliminarCultivoButton;
    public JButton verNombresButton;
    public JButton verDetallesButton;
    public JButton guardarButton;
    public JButton guardarComoButton;
    public JButton abrirButton;
    public JLabel nombreLabel;
    public JLabel cantidadLabel;
    public experimentos experimento;

    public MAIN() {
        setTitle("Experimento con bacterias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel de información
        JPanel informacionPanel = new JPanel();
        informacionPanel.setLayout(new GridLayout(4, 2));
        nombreLabel = new JLabel("Nombre: ");
        cantidadLabel = new JLabel("Cantidad: ");
        nombreField = new JTextField();
        cantidadField = new JTextField();
        agregarCultivoButton = new JButton("Agregar cultivo");
        agregarCultivoButton.addActionListener(this);
        eliminarCultivoButton = new JButton("Eliminar cultivo");
        eliminarCultivoButton.addActionListener(this);
        verNombresButton = new JButton("Ver nombres de cultivos");
        verNombresButton.addActionListener(this);
        verDetallesButton = new JButton("Ver detalles de cultivo");
        verDetallesButton.addActionListener(this);
        informacionPanel.add(nombreLabel);
        informacionPanel.add(nombreField);
        informacionPanel.add(cantidadLabel);
        informacionPanel.add(cantidadField);
        informacionPanel.add(agregarCultivoButton);
        informacionPanel.add(eliminarCultivoButton);
        informacionPanel.add(verNombresButton);
        informacionPanel.add(verDetallesButton);

        // Panel de detalles
        detallesArea = new JTextArea();
        JScrollPane detallesScroll = new JScrollPane(detallesArea);

        // Panel de botones de archivo
        JPanel botonesArchivoPanel = new JPanel();
        botonesArchivoPanel.setLayout(new GridLayout(1, 3));
        guardarButton = new JButton("Guardar experimento");
        guardarButton.addActionListener(this);
        guardarComoButton = new JButton("Guardar como");
        guardarComoButton.addActionListener(this);
        abrirButton = new JButton("Abrir experimento");
        abrirButton.addActionListener(this);
        botonesArchivoPanel.add(guardarButton);
        botonesArchivoPanel.add(guardarComoButton);
        botonesArchivoPanel.add(abrirButton);

        // Agregar todos los paneles al JFrame
        add(informacionPanel, BorderLayout.NORTH);
        add(detallesScroll, BorderLayout.CENTER);
        add(botonesArchivoPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == agregarCultivoButton) {
                if (!cantidadField.getText().isEmpty()) {
                    agregarCultivoDeBacterias(nombreField.getText(), Integer.parseInt(cantidadField.getText()));
                } else {
                    detallesArea.append("Debe especificar una cantidad para agregar un cultivo de bacterias.\n");
                }
            } else if (e.getSource() == eliminarCultivoButton) {
                eliminarCultivoDeBacterias(nombreField.getText());
            } else if (e.getSource() == verNombresButton) {
                verNombresDeCultivosDeBacterias();
            } else if (e.getSource() == verDetallesButton) {
                verInformacionDetalladaDeCultivoDeBacterias(nombreField.getText());
            } else if (e.getSource() == guardarButton) {
                guardarExperimento();
            } else if (e.getSource() == guardarComoButton) {
                guardarExperimentoComo();
            } else if (e.getSource() == abrirButton) {
                abrirExperimento();
            }
        } catch (IOException | ClassNotFoundException ex) {
            // Handle the exception appropriately
            ex.printStackTrace();
        }
    }

    public void abrirExperimento() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            setExperimento(experimentos.abrirExperimento(file.getAbsolutePath()));
            detallesArea.append("Experimento cargado desde: " + file.getAbsolutePath() + "\n");
        }
    }

    public void guardarExperimento() throws IOException {
        String ruta = "experimento.ser"; // Ruta predeterminada o lógica interna para determinar la ruta de guardado
        guardarExperimento(ruta);
    }
    public void guardarExperimento(String ruta) throws IOException {
        // Implementación del método para guardar el experimento en la ruta proporcionada
    }


    public void guardarExperimentoComo() throws IOException {
        if (getExperimento() != null) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getAbsolutePath().endsWith(".ser")) {
                    file = new File(file.getAbsolutePath() + ".ser");
                }
                getExperimento().guardarExperimento(    file.getAbsolutePath());
                detallesArea.append("Experimento guardado en: " + file.getAbsolutePath() + "\n");
            }
        } else {
            detallesArea.append("No hay ningún experimento para guardar\n");
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(String nombre) {
        Optional<CultivoDeBacterias> cultivoOptional = getExperimento().obtenerDetallesCultivo(nombre);
        if (cultivoOptional.isPresent()) {
            CultivoDeBacterias cultivo = cultivoOptional.get();
            detallesArea.append("Nombre: " + cultivo.getNombre() + "\n");
            detallesArea.append("Cantidad: " + cultivo.getCantidad() + "\n");
            detallesArea.append("Características de bacterias:\n");
            detallesArea.append(" - Colonia: " + cultivo.getBacterias().getColonia() + "\n");
            detallesArea.append(" - Genotipo: " + cultivo.getBacterias().getGenotipo() + "\n");
            detallesArea.append(" - Plásmidos: " + cultivo.getBacterias().getPlasmidos() + "\n");
        } else {
            detallesArea.append("No se encontró el cultivo de bacterias: " + nombre + "\n");
        }
    }

    public void verNombresDeCultivosDeBacterias() {
        getExperimento().mostrarCultivosDeBacterias();
    }

    public void eliminarCultivoDeBacterias(String nombre) {
        Optional<CultivoDeBacterias> cultivoOptional = getExperimento().obtenerDetallesCultivo(nombre);
        if (cultivoOptional.isPresent()) {
            getExperimento().eliminarCultivoDeBacterias(cultivoOptional.get());
            detallesArea.append("Cultivo de bacterias eliminado: " + nombre + "\n");
        } else {
            detallesArea.append("No se encontró el cultivo de bacterias: " + nombre + "\n");
        }
    }

    public void agregarCultivoDeBacterias(String nombre, int cantidad) {
        Bacterias bacterias = new Bacterias("Colonia", "Genotipo", new ArrayList<>());
        CultivoDeBacterias nuevoCultivo = new CultivoDeBacterias(nombre, cantidad, bacterias);
        getExperimento().agregarCultivoDeBacterias(nuevoCultivo);
        detallesArea.append("Cultivo de bacterias agregado: " + nombre + "\n");
    }

    public void setExperimento(experimentos experimento) {
        this.experimento = experimento;
    }

    public experimentos getExperimento() {
        if (experimento == null) {
            experimento = new experimentos();
        }
        return this.experimento;
    }

    public static void main(String[] args) {
        MAIN main = new MAIN();
        main.setVisible(true);
    }
}

class Bacterias implements Serializable {
    private String colonia;
    private String genotipo;
    private ArrayList<String> plasmidos;

    public Bacterias(String colonia, String genotipo, ArrayList<String> plasmidos) {
        this.colonia = colonia;
        this.genotipo = genotipo;
        this.plasmidos = plasmidos;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getGenotipo() {
        return genotipo;
    }

    public void setGenotipo(String genotipo) {
        this.genotipo = genotipo;
    }

    public ArrayList<String> getPlasmidos() {
        return plasmidos;
    }

    public void setPlasmidos(ArrayList<String> plasmidos) {
        this.plasmidos = plasmidos;
    }
}

class CultivoDeBacterias implements Serializable {
    private String nombre;
    private int cantidad;
    private Bacterias bacterias;

    public CultivoDeBacterias(String nombre, int cantidad, Bacterias bacterias) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.bacterias = bacterias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bacterias getBacterias() {
        return bacterias;
    }

    public void setBacterias(Bacterias bacterias) {
        this.bacterias = bacterias;
    }

    public void isEmpty () {
    }
}



