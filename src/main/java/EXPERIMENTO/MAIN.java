package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class MAIN extends JFrame implements ActionListener {

    public JTextField nombreField;
    public JTextField inicioField;
    public JTextField finField;
    public JTextField cantidadField;
    public JTextField temperaturaField;
    public JComboBox<String> luminosidadCombo;
    public JTextField comidaInicialField;
    public JTextField diaIncrementoField;
    public JTextField comidaIncrementoField;
    public JTextField comidaFinalField;
    public JTextArea detallesArea;
    public JButton agregarCultivoButton;
    public JButton eliminarCultivoButton;
    public JButton verNombresButton;
    public JButton verDetallesButton;
    public JButton guardarButton;
    public JButton guardarComoButton;
    public JButton abrirButton;
    public JLabel nombreLabel;
    public JLabel inicioLabel;
    public JLabel finLabel;
    public JLabel cantidadLabel;
    public JLabel temperaturaLabel;
    public JLabel luminosidadLabel;
    public JLabel comidaInicialLabel;
    public JLabel diaIncrementoLabel;
    public JLabel comidaIncrementoLabel;
    public JLabel comidaFinalLabel;
    public experimentos experimento;

    public MAIN() {
        setTitle("Experimento con bacterias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel de información
        JPanel informacionPanel = new JPanel();
        informacionPanel.setLayout(new GridLayout(10, 2));
        nombreLabel = new JLabel("Nombre: ");
        inicioLabel = new JLabel("Fecha de inicio (YYYY-MM-DD): ");
        finLabel = new JLabel("Fecha de fin (YYYY-MM-DD): ");
        cantidadLabel = new JLabel("Cantidad inicial de bacterias: ");
        temperaturaLabel = new JLabel("Temperatura: ");
        luminosidadLabel = new JLabel("Luminosidad: ");
        comidaInicialLabel = new JLabel("Comida inicial (día 1): ");
        diaIncrementoLabel = new JLabel("Día de incremento de comida: ");
        comidaIncrementoLabel = new JLabel("Comida en día de incremento: ");
        comidaFinalLabel = new JLabel("Comida final (día 30): ");
        nombreField = new JTextField();
        inicioField = new JTextField();
        finField = new JTextField();
        cantidadField = new JTextField();
        temperaturaField = new JTextField();
        String[] luminosidades = {"Alta", "Media", "Baja"};
        luminosidadCombo = new JComboBox<>(luminosidades);
        comidaInicialField = new JTextField();
        diaIncrementoField = new JTextField();
        comidaIncrementoField = new JTextField();
        comidaFinalField = new JTextField();
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
        informacionPanel.add(inicioLabel);
        informacionPanel.add(inicioField);
        informacionPanel.add(finLabel);
        informacionPanel.add(finField);
        informacionPanel.add(cantidadLabel);
        informacionPanel.add(cantidadField);
        informacionPanel.add(temperaturaLabel);
        informacionPanel.add(temperaturaField);
        informacionPanel.add(luminosidadLabel);
        informacionPanel.add(luminosidadCombo);
        informacionPanel.add(comidaInicialLabel);
        informacionPanel.add(comidaInicialField);
        informacionPanel.add(diaIncrementoLabel);
        informacionPanel.add(diaIncrementoField);
        informacionPanel.add(comidaIncrementoLabel);
        informacionPanel.add(comidaIncrementoField);
        informacionPanel.add(comidaFinalLabel);
        informacionPanel.add(comidaFinalField);
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
                    agregarCultivoDeBacterias(nombreField.getText(),
                            LocalDate.parse(inicioField.getText()),
                            LocalDate.parse(finField.getText()),
                            Integer.parseInt(cantidadField.getText()),
                            Double.parseDouble(temperaturaField.getText()),
                            luminosidadCombo.getSelectedItem().toString(),
                            Integer.parseInt(comidaInicialField.getText()),
                            Integer.parseInt(diaIncrementoField.getText()),
                            Integer.parseInt(comidaIncrementoField.getText()),
                            Integer.parseInt(comidaFinalField.getText()));
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
        String ruta = "experimento.ser";
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
                getExperimento().guardarExperimento(file.getAbsolutePath());
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
            detallesArea.append("Fechas: " + cultivo.getFechaInicio() + " - " + cultivo.getFechaFin() + "\n");
            detallesArea.append("Cantidad inicial: " + cultivo.getCantidad() + "\n");
            detallesArea.append("Temperatura: " + cultivo.getTemperatura() + "\n");
            detallesArea.append("Luminosidad: " + cultivo.getLuminosidad() + "\n");
            detallesArea.append("Dosis de comida:\n");
            detallesArea.append(" - Inicial: " + cultivo.getComidaInicial() + "\n");
            detallesArea.append(" - Incremento en día " + cultivo.getDiaIncremento() + ": " +
                    cultivo.getComidaIncremento() + "\n");
            detallesArea.append(" - Final: " + cultivo.getComidaFinal() + "\n");
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

    public void agregarCultivoDeBacterias(String nombre, LocalDate inicio, LocalDate fin, int cantidad,
                                          double temperatura, String luminosidad, int comidaInicial,
                                          int diaIncremento, int comidaIncremento, int comidaFinal) {
        DosisComida dosisComida = new DosisComida(comidaInicial, diaIncremento, comidaIncremento, comidaFinal);
        CultivoDeBacterias nuevoCultivo = new CultivoDeBacterias(nombre, inicio, fin, cantidad,
                temperatura, luminosidad, dosisComida);
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

class DosisComida implements Serializable {
    private int comidaInicial;
    private int diaIncremento;
    private int comidaIncremento;
    private int comidaFinal;

    public DosisComida(int comidaInicial, int diaIncremento, int comidaIncremento, int comidaFinal) {
        this.comidaInicial = comidaInicial;
        this.diaIncremento = diaIncremento;
        this.comidaIncremento = comidaIncremento;
        this.comidaFinal = comidaFinal;
    }

    public int getComidaInicial() {
        return comidaInicial;
    }

    public void setComidaInicial(int comidaInicial) {
        this.comidaInicial = comidaInicial;
    }

    public int getDiaIncremento() {
        return diaIncremento;
    }

    public void setDiaIncremento(int diaIncremento) {
        this.diaIncremento = diaIncremento;
    }

    public int getComidaIncremento() {
        return comidaIncremento;
    }

    public void setComidaIncremento(int comidaIncremento) {
        this.comidaIncremento = comidaIncremento;
    }

    public int getComidaFinal() {
        return comidaFinal;
    }

    public void setComidaFinal(int comidaFinal) {
        this.comidaFinal = comidaFinal;
    }
}

class CultivoDeBacterias implements Serializable {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidad;
    private double temperatura;
    private String luminosidad;
    private DosisComida dosisComida;

    public CultivoDeBacterias(String nombre, LocalDate fechaInicio, LocalDate fechaFin, int cantidad,
                              double temperatura, String luminosidad, DosisComida dosisComida) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(String luminosidad) {
        this.luminosidad = luminosidad;
    }

    public DosisComida getDosisComida() {
        return dosisComida;
    }

    public void setDosisComida(DosisComida dosisComida) {
        this.dosisComida = dosisComida;
    }
}

class experimentos {
    public void agregarCultivoDeBacterias(CultivoDeBacterias cultivo) {
        // Implementación de agregar cultivo
    }

    public void eliminarCultivoDeBacterias(CultivoDeBacterias cultivo) {
        // Implementación de eliminar cultivo
    }

    public Optional<CultivoDeBacterias> obtenerDetallesCultivo(String nombre) {
        // Implementación de obtener detalles del cultivo
        return Optional.empty();
    }

    public void mostrarCultivosDeBacterias() {
        // Implementación de mostrar cultivos
    }

    public static experimentos abrirExperimento(String ruta) throws IOException, ClassNotFoundException {
        // Implementación de abrir experimento
        return new experimentos();
    }

    public void guardarExperimento(String ruta) throws IOException {
        // Implementación de guardar experimento
    }
}
