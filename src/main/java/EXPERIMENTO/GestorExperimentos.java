package EXPERIMENTO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.HashMap;

public class GestorExperimentos extends JFrame implements ActionListener {

    private JButton btnVerGraficas;
    private JComboBox<String> languageComboBox;
    private String[] languages = {"Español", "English"};
    private JButton changeLanguageButton; // Botón para cambiar el idioma
    private JButton btnBlocDeNotas;
    private JButton btnCalcularTasaCrecimiento;
    private static String contraseñaEsperada = "filetedelomo"; // Cambiar por tu contraseña

    private HashMap<String, String> usuariosRegistrados;
    private JButton btnAbrirArchivo, btnCrearExperimento, btnCrearPoblacion, btnVisualizarPoblaciones, btnBorrarPoblacion, btnVerInfo, btnGuardar, btnGuardarComo;
    private Experimento experimentoActual;
    private ArrayList<PoblacionBacterias> poblaciones;
    private JButton btnCalcularEstadisticas;
    public GestorExperimentos() {

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.addActionListener(this);

        changeLanguageButton = new JButton("Cambiar Idioma");
        changeLanguageButton.addActionListener(this);

        add(languageComboBox);
        add(changeLanguageButton);



        btnBlocDeNotas = new JButton("Bloc de Notas");
        btnBlocDeNotas.addActionListener(this);
        // Color de fondo del botón
        btnBlocDeNotas.setBackground(new Color(150, 150, 150));
        // Color del texto del botón
        btnBlocDeNotas.setForeground(Color.WHITE);
        // Agregar el botón al panel
        add(btnBlocDeNotas);


        btnCalcularTasaCrecimiento = new JButton("Calcular Tasa de Crecimiento");
        btnCalcularTasaCrecimiento.addActionListener(this);
        add(btnCalcularTasaCrecimiento);


        btnCalcularEstadisticas = new JButton("Calcular estadísticas");
        btnCalcularEstadisticas.addActionListener(this);
        add(btnCalcularEstadisticas); // Agregar el botón al panel

        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField(15);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JPanel panelInicioSesion = new JPanel();
        panelInicioSesion.add(lblContraseña);
        panelInicioSesion.add(txtContraseña);
        panelInicioSesion.add(btnIniciarSesion);


        setTitle("Gestor de Experimentos");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        btnVerGraficas = new JButton("Ver Gráficas");
        btnVerGraficas.addActionListener(this);
        add(btnVerGraficas);

        btnAbrirArchivo = new JButton("Abrir archivo");
        btnCrearExperimento = new JButton("Crear experimento");
        btnCrearPoblacion = new JButton("Crear población");
        btnVisualizarPoblaciones = new JButton("Visualizar poblaciones");
        btnBorrarPoblacion = new JButton("Borrar población");
        btnVerInfo = new JButton("Ver información detallada");
        btnGuardar = new JButton("Guardar");
        btnGuardarComo = new JButton("Guardar como");

        btnAbrirArchivo.setBackground(new Color ( 200, 100, 100 ));
        btnCrearExperimento.setBackground(new Color( 200, 135, 100 ));
        btnCrearPoblacion.setBackground(new Color( 200, 165, 100 ));
        btnVisualizarPoblaciones.setBackground(new Color( 200, 198, 100 ));
        btnBorrarPoblacion.setBackground(new Color( 178, 200, 100 ));
        btnVerInfo.setBackground(new Color( 100, 200, 105 ));
        btnGuardar.setBackground(new Color( 100, 123, 200 ));
        btnGuardarComo.setBackground(new Color(100, 200, 173 ));
        btnCalcularEstadisticas.setBackground(new Color( 200, 100, 130 ));
        btnCalcularTasaCrecimiento.setBackground(new Color( 200, 100, 130 ));
        btnBlocDeNotas.setBackground(new Color( 200, 100, 130 ));
        changeLanguageButton.setBackground(new Color( 200, 100, 118 ));
        btnVerGraficas.setBackground(new Color( 200, 100, 123 ));


        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                char[] input = txtContraseña.getPassword();
                String contraseñaIngresada = new String(input);
                if (contraseñaIngresada.equals(contraseñaEsperada)) {
                    habilitarFuncionalidades();
                    JOptionPane.showMessageDialog(GestorExperimentos.this, "Inicio de sesión exitoso.");
                } else {
                    JOptionPane.showMessageDialog(GestorExperimentos.this, "Contraseña incorrecta. Inténtelo de nuevo.");
                }
                // Limpiar campo de contraseña
                txtContraseña.setText("");
            }
        });

        btnAbrirArchivo.addActionListener(this);
        btnCrearExperimento.addActionListener(this);
        btnCrearPoblacion.addActionListener(this);
        btnVisualizarPoblaciones.addActionListener(this);
        btnBorrarPoblacion.addActionListener(this);
        btnVerInfo.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnGuardarComo.addActionListener(this);

        usuariosRegistrados = new HashMap<>();
        add(lblContraseña);
        add(txtContraseña);
        add(btnIniciarSesion);


        add(btnAbrirArchivo);
        add(btnCrearExperimento);
        add(btnCrearPoblacion);
        add(btnVisualizarPoblaciones);
        add(btnBorrarPoblacion);
        add(btnVerInfo);
        add(btnGuardar);
        add(btnGuardarComo);

        poblaciones = new ArrayList<>();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnVerGraficas) {
            verGraficas();

        }

        if (e.getSource() == languageComboBox || e.getSource() == changeLanguageButton) {
            cambiarIdioma();    // Método para cambiar el idioma

        } else

        if (e.getSource() == btnBlocDeNotas) {
            abrirBlocDeNotas();
        } else if (e.getSource() == btnCalcularTasaCrecimiento) {
        }

        if (experimentoActual != null && !poblaciones.isEmpty()) {
            calcularTasaCrecimiento(    poblaciones.get(0).getNumBacterias(),
                    poblaciones.get(poblaciones.size() - 1).getNumBacterias(),
                    poblaciones.size() - 1);
        } else {
            JOptionPane.showMessageDialog(this, "No hay experimento o poblaciones para calcular la tasa de crecimiento.");
        }


        if (e.getSource() == btnCalcularEstadisticas) {
            calcularEstadisticas();
        } else


        if (e.getSource() == btnAbrirArchivo) {
            abrirArchivo();
        } else if (e.getSource() == btnCrearExperimento) {
            experimentoActual = new Experimento();
            JOptionPane.showMessageDialog(this, "Se ha creado un nuevo experimento.");
        } else if (e.getSource() == btnCrearPoblacion) {
            if (experimentoActual != null) {
                crearPoblacion();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, primero crea un experimento.");
            }
        } else if (e.getSource() == btnVisualizarPoblaciones) {
            visualizarPoblaciones();
        } else if (e.getSource() == btnBorrarPoblacion) {
            borrarPoblacion();
        } else if (e.getSource() == btnVerInfo) {
            verInformacion();
        } else if (e.getSource() == btnGuardar) {
            guardar();
        } else if (e.getSource() == btnGuardarComo) {
            guardarComo();
        }
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            // Aquí implementar la lógica para abrir el archivo y cargar los datos
            experimentoActual = new Experimento(archivoSeleccionado.getAbsolutePath());
            poblaciones = experimentoActual.getPoblaciones();
            JOptionPane.showMessageDialog(this, "Se ha cargado el experimento del archivo " + archivoSeleccionado.getAbsolutePath());
        }
    }



    private void crearPoblacion() {
        JTextField txtNombre = new JTextField();
        JTextField txtFechaInicio = new JTextField();
        JTextField txtFechaFin = new JTextField();
        JTextField txtNumBacterias = new JTextField();
        JTextField txtTemperatura = new JTextField();
        String[] luminosidadOptions = {"Alta", "Media", "Baja"};
        JComboBox<String> cmbLuminosidad = new JComboBox<>(luminosidadOptions);
        JTextField txtDosisComidaInicial = new JTextField();
        JTextField txtDiaIncrementoComida = new JTextField();
        JTextField txtComidaDiaIncremento = new JTextField();
        JTextField txtComidaDiaFinal = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nombre de la población:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha de inicio (dd/mm/aaaa):"));
        panel.add(txtFechaInicio);
        panel.add(new JLabel("Fecha de fin (dd/mm/aaaa):"));
        panel.add(txtFechaFin);
        panel.add(new JLabel("Número de bacterias iniciales:"));
        panel.add(txtNumBacterias);
        panel.add(new JLabel("Temperatura:"));
        panel.add(txtTemperatura);
        panel.add(new JLabel("Luminosidad:"));
        panel.add(cmbLuminosidad);
        panel.add(new JLabel("Dosis de comida inicial:"));
        panel.add(txtDosisComidaInicial);
        panel.add(new JLabel("Día de incremento de comida:"));
        panel.add(txtDiaIncrementoComida);
        panel.add(new JLabel("Comida en el día de incremento:"));
        panel.add(txtComidaDiaIncremento);
        panel.add(new JLabel("Comida en el último día:"));
        panel.add(txtComidaDiaFinal);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Crear población de bacterias",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText();
                String fechaInicio = txtFechaInicio.getText();
                String fechaFin = txtFechaFin.getText();
                int numBacterias = Integer.parseInt(txtNumBacterias.getText());
                int temperatura = Integer.parseInt(txtTemperatura.getText());
                String luminosidad = (String) cmbLuminosidad.getSelectedItem();
                int dosisComidaInicial = Integer.parseInt(txtDosisComidaInicial.getText());
                int diaIncrementoComida = Integer.parseInt(txtDiaIncrementoComida.getText());
                int comidaDiaIncremento = Integer.parseInt(txtComidaDiaIncremento.getText());
                int comidaDiaFinal = Integer.parseInt(txtComidaDiaFinal.getText());

                PoblacionBacterias poblacion = new PoblacionBacterias(nombre, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad,
                        dosisComidaInicial, diaIncrementoComida, comidaDiaIncremento, comidaDiaFinal);
                experimentoActual.agregarPoblacion(poblacion);
                poblaciones.add(poblacion);
                JOptionPane.showMessageDialog(this, "Se ha creado una nueva población de bacterias.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos para los campos numéricos.");
            }
        }
    }

    private void visualizarPoblaciones() {
        StringBuilder poblacionesTexto = new StringBuilder();
        for (PoblacionBacterias poblacion : poblaciones) {
            poblacionesTexto.append(poblacion.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(this, poblacionesTexto.toString(), "Poblaciones de bacterias", JOptionPane.INFORMATION_MESSAGE);
    }

    private void borrarPoblacion() {
        if (poblaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay poblaciones para borrar.");
            return;
        }

        String[] nombresPoblaciones = new String[poblaciones.size()];
        for (int i = 0; i < poblaciones.size(); i++) {
            nombresPoblaciones[i] = poblaciones.get(i).getNombre();
        }

        String poblacionSeleccionada = (String) JOptionPane.showInputDialog(this, "Selecciona una población para borrar:",
                "Borrar población", JOptionPane.QUESTION_MESSAGE, null, nombresPoblaciones, nombresPoblaciones[0]);

        if (poblacionSeleccionada != null) {
            for (PoblacionBacterias poblacion : poblaciones) {
                if (poblacion.getNombre().equals(poblacionSeleccionada)) {
                    poblaciones.remove(poblacion);
                    JOptionPane.showMessageDialog(this, "Se ha borrado la población seleccionada.");
                    return;
                }
            }
        }
    }

    private void verInformacion() {
        if (experimentoActual == null || poblaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay experimento o poblaciones para mostrar información.");
            return;
        }

        String[] nombresPoblaciones = new String[poblaciones.size()];
        for (int i = 0; i < poblaciones.size(); i++) {
            nombresPoblaciones[i] = poblaciones.get(i).getNombre();
        }

        String poblacionSeleccionada = (String) JOptionPane.showInputDialog(this, "Selecciona una población para ver información detallada:",
                "Información detallada", JOptionPane.QUESTION_MESSAGE, null, nombresPoblaciones, nombresPoblaciones[0]);

        if (poblacionSeleccionada != null) {
            for (PoblacionBacterias poblacion : poblaciones) {
                if (poblacion.getNombre().equals(poblacionSeleccionada)) {
                    JOptionPane.showMessageDialog(this, "Nombre: " + poblacion.getNombre() + "\n" +
                            "Fecha de inicio: " + poblacion.getFechaInicio() + "\n" +
                            "Fecha de fin: " + poblacion.getFechaFin() + "\n" +
                            "Número de bacterias iniciales: " + poblacion.getNumBacterias() + "\n" +
                            "Temperatura: " + poblacion.getTemperatura() + "\n" +
                            "Luminosidad: " + poblacion.getLuminosidad() + "\n" +
                            "Dosis de comida: " + poblacion.getDosisComida());
                    return;
                }
            }
        }
    }

    private void guardar() {
        if (experimentoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay experimento para guardar.");
            return;
        }

        if (experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El experimento no contiene poblaciones para guardar.");
            return;
        }

        if (experimentoActual.getNombreArchivo() == null) {
            guardarComo();
        } else {
            try {
                FileWriter writer = new FileWriter(experimentoActual.getNombreArchivo());
                for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
                    writer.write(poblacion.toString() + "\n");
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Se ha guardado el experimento en el archivo " + experimentoActual.getNombreArchivo());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el experimento.");
            }
        }
    }

    private void guardarComo() {
        if (experimentoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay experimento para guardar.");
            return;
        }

        if (experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El experimento no contiene poblaciones para guardar.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como...");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoGuardar = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(archivoGuardar);
                for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
                    writer.write(poblacion.toString() + "\n");
                }
                writer.close();
                experimentoActual.setNombreArchivo(archivoGuardar.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Se ha guardado el experimento en el archivo " + archivoGuardar.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el experimento.");
            }
        }
    }


    private void calcularEstadisticas() {
        if (poblaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay poblaciones para calcular estadísticas.");
            return;
        }

        // Calcular estadísticas sobre el número de bacterias
        ArrayList<Integer> numBacteriasList = new ArrayList<>();
        for (PoblacionBacterias poblacion : poblaciones) {
            numBacteriasList.add(poblacion.getNumBacterias());
        }

        double media = calcularMedia(numBacteriasList);
        double desviacionEstandar = calcularDesviacionEstandar(numBacteriasList);

        // Mostrar las estadísticas
        JOptionPane.showMessageDialog(this,
                "Estadísticas sobre el número de bacterias:\n" +
                        "Media: " + media + "\n" +
                        "Desviación estándar: " + desviacionEstandar,
                "Estadísticas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private double calcularMedia(ArrayList<Integer> valores) {
        double suma = 0;
        for (int valor : valores) {
            suma += valor;
        }
        return suma / valores.size();
    }

    private double calcularDesviacionEstandar(ArrayList<Integer> valores) {
        double media = calcularMedia(valores);
        double sumaDiferenciasCuadrado = 0;
        for (int valor : valores) {
            sumaDiferenciasCuadrado += Math.pow(valor - media, 2);
        }
        return Math.sqrt(sumaDiferenciasCuadrado / valores.size());
    }


    private double calcularTasaCrecimiento(int numBacteriasInicial, int numBacteriasFinal, int tiempoTranscurrido) {
        double tasaCrecimiento;
        double lnNumBacteriasInicial = Math.log(numBacteriasInicial);
        double lnNumBacteriasFinal = Math.log(numBacteriasFinal);

        tasaCrecimiento = (lnNumBacteriasFinal - lnNumBacteriasInicial) / tiempoTranscurrido;

        return tasaCrecimiento;
    }


    private void abrirBlocDeNotas() {
        try {
            // Se ejecuta el comando para abrir el bloc de notas
            Runtime.getRuntime().exec("notepad.exe");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al abrir el Bloc de Notas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cambiarIdioma() {
        int selectedIndex = languageComboBox.getSelectedIndex();

        switch (selectedIndex) {
            case 0:
                cambiarIdiomaEspañol();
                break;
            case 1:
                cambiarIdiomaIngles();
                break;
        }
    }

    private void cambiarIdiomaEspañol() {
        setTitle("Gestor de Experimentos");
        changeLanguageButton.setText("Cambiar Idioma");
    }

    private void cambiarIdiomaIngles() {
        setTitle("Experiment Manager");
        changeLanguageButton.setText("Change Language");
    }
    private void habilitarFuncionalidades() {
        btnAbrirArchivo.setEnabled(true);
        btnCrearExperimento.setEnabled(true);
        btnCrearPoblacion.setEnabled(true);
        btnVisualizarPoblaciones.setEnabled(true);
        btnBorrarPoblacion.setEnabled(true);
        btnVerInfo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnGuardarComo.setEnabled(true);
    }

    private void verGraficas() {

        if (experimentoActual != null && !experimentoActual.getPoblaciones().isEmpty()) {
            JFrame frame = new JFrame("Gráficas de Poblaciones");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Crear un conjunto de datos para las gráficas
            XYSeriesCollection dataset = new XYSeriesCollection();
            for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
                XYSeries series = new XYSeries(poblacion.getNombre());
                for (int i = 0; i < poblacion.getDatosPoblacion().size(); i++) {
                    series.add( (double) i, (Number) poblacion.getDatosPoblacion().get(i) );
                }
                dataset.addSeries(series);
            }

            // Crear el gráfico
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Gráficas de Poblaciones", // Título del gráfico
                    "Días", // Etiqueta del eje X
                    "Número de Bacterias", // Etiqueta del eje Y
                    dataset // Conjunto de datos
            );

            // Crear el panel de gráfico y agregarlo al marco
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel, BorderLayout.CENTER);

            // Mostrar la ventana
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No hay experimento o poblaciones para mostrar gráficas.");
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestorExperimentos::new);
    }
}
