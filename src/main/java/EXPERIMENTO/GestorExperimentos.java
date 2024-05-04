package EXPERIMENTO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static javax.swing.UIManager.setLookAndFeel;

public class GestorExperimentos extends JFrame implements ActionListener {

    private JButton btnVerGraficas, btnBlocDeNotas, btnCalcularTasaCrecimiento, btnCalcularEstadisticas;
    private JButton btnAbrirArchivo, btnCrearExperimento, btnCrearPoblacion, btnVisualizarPoblaciones, btnBorrarPoblacion, btnVerInfo, btnGuardar, btnGuardarComo;
    private JComboBox<String> languageComboBox;
    private JButton changeLanguageButton;
    private static final String CONTRASENA_ESPERADA = "filetedelomo";
    private HashMap<String, String> usuariosRegistrados;
    private Experimento experimentoActual;
    private ArrayList<PoblacionBacterias> poblaciones;
    private JTextArea textAreaCentral;

    public GestorExperimentos() {
        setTitle("Gestor de Experimentos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        inicializarComponentes();
        poblaciones = new ArrayList<>();
        setVisible(true);
    }

    private void inicializarComponentes() {
        add(crearPanelAcciones(), BorderLayout.WEST);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelIdioma(), BorderLayout.NORTH);
        add(crearPanelInicioSesion(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelAcciones() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btnCrearExperimento = crearBoton("Crear experimento", this, new Color(0, 128, 0));  // Verde
        btnCrearPoblacion = crearBoton("Crear población", this, new Color(0, 128, 0));     // Verde
        btnVisualizarPoblaciones = crearBoton("Visualizar poblaciones", this, Color.BLUE); // Azul
        btnBorrarPoblacion = crearBoton("Borrar población", this, Color.BLUE);             // Azul
        btnVerGraficas = crearBoton("Ver Gráficas", this, Color.RED);                      // Rojo
        btnVerInfo = crearBoton("Ver información detallada", this, Color.RED);             // Rojo
        btnAbrirArchivo = crearBoton("Abrir archivo", this, new Color(128, 0, 128));       // Morado
        btnGuardar = crearBoton("Guardar", this, new Color(128, 0, 128));                  // Morado
        btnGuardarComo = crearBoton("Guardar como", this, new Color(128, 0, 128));         // Morado
        btnCalcularTasaCrecimiento = crearBoton("Calcular Tasas de Crecimiento", this, new Color(255, 105, 180)); // Rosa
        btnCalcularEstadisticas = crearBoton("Calcular Estadísticas", this, new Color(255, 105, 180)); // Rosa

        btnAbrirArchivo = crearBoton("Abrir archivo", this, new Color(128, 0, 128));
        btnGuardar = crearBoton("Guardar", this, new Color(128, 0, 128));
        btnGuardarComo = crearBoton("Guardar como", this, new Color(128, 0, 128));

        panel.add(btnCrearExperimento);
        panel.add(btnCrearPoblacion);
        panel.add(btnVisualizarPoblaciones);
        panel.add(btnBorrarPoblacion);
        panel.add(btnVerGraficas);
        panel.add(btnVerInfo);
        panel.add(btnAbrirArchivo);
        panel.add(btnGuardar);
        panel.add(btnGuardarComo);
        panel.add(btnCalcularTasaCrecimiento);
        panel.add(btnCalcularEstadisticas);

        return panel;
    }

    private JButton crearBoton(String texto, ActionListener listener, Color color) {
        JButton boton = new JButton(texto);
        boton.addActionListener(listener);
        return boton;
    }

    private JScrollPane crearPanelCentral() {
        textAreaCentral = new JTextArea();
        textAreaCentral.setEditable(false);
        return new JScrollPane(textAreaCentral);
    }

    private JPanel crearPanelIdioma() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        languageComboBox = new JComboBox<>(new String[]{"Español", "English"});
        languageComboBox.addActionListener(this);

        changeLanguageButton = new JButton("Cambiar Idioma");
        changeLanguageButton.addActionListener(this);

        panel.add(languageComboBox);
        panel.add(changeLanguageButton);
        return panel;
    }

    private JPanel crearPanelInicioSesion() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField(15);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(e -> {
            char[] input = txtContraseña.getPassword();
            String contraseñaIngresada = new String(input);
            if (contraseñaIngresada.equals(CONTRASENA_ESPERADA)) {
                habilitarFuncionalidades();
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Inténtelo de nuevo.");
            }
            txtContraseña.setText("");
        });

        panel.add(lblContraseña);
        panel.add(txtContraseña);
        panel.add(btnIniciarSesion);
        return panel;
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

    // Otros métodos como actionPerformed y métodos específicos de funcionalidades
    // (verGraficas, cambiarIdioma, abrirArchivo, etc.) siguen aquí...

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerGraficas) {
            verGraficas();
        } else if (e.getSource() == changeLanguageButton) {
            cambiarIdioma();
        } else if (e.getSource() == btnAbrirArchivo) {
            abrirArchivo();
        } else if (e.getSource() == btnCrearExperimento) {
            crearExperimento();
        } else if (e.getSource() == btnCrearPoblacion) {
            crearPoblacion();
        } else if (e.getSource() == btnVisualizarPoblaciones) {
            visualizarPoblaciones();
        } else if (e.getSource() == btnBorrarPoblacion) {
            borrarPoblacion();
        } else if (e.getSource() == btnVerInfo) {
            verInformacionDetallada();
        } else if (e.getSource() == btnGuardar) {
            guardarExperimento();
        } else if (e.getSource() == btnGuardarComo) {
            guardarExperimentoComo();
        } else if (e.getSource() == languageComboBox) {
            cambiarIdioma();

        } else if (e.getSource() == btnCalcularTasaCrecimiento) {
            calcularTasasDeCrecimiento();
        } else if (e.getSource() == btnCalcularEstadisticas) {
            calcularEstadisticasGenerales();
        }
    }


    private void verGraficas() {
        if (experimentoActual != null) {
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Tasa de Crecimiento de Bacterias",
                    "Día",
                    "Número de Bacterias",
                    crearDataset(),
                    org.jfree.chart.plot.PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            JOptionPane.showMessageDialog(this, chartPanel, "Gráfica de Tasa de Crecimiento", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay experimento cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void calcularTasasDeCrecimiento() {
        if (experimentoActual == null || experimentoActual.getPoblaciones().isEmpty()) {
            textAreaCentral.setText("No hay datos suficientes para calcular la tasa de crecimiento.");
            return;
        }

        try {
            // Assuming that getFechaInicio() and getFechaFin() return dates in "dd/MM/yyyy" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            PoblacionBacterias poblacionInicial = experimentoActual.getPoblaciones().get(0);
            PoblacionBacterias poblacionFinal = experimentoActual.getPoblaciones().get(experimentoActual.getPoblaciones().size() - 1);

            LocalDate startDate = LocalDate.parse(poblacionInicial.getFechaInicio().trim(), formatter);
            LocalDate endDate = LocalDate.parse(poblacionFinal.getFechaFin().trim(), formatter);
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            if (daysBetween == 0) {
                textAreaCentral.setText("El período de tiempo para calcular la tasa de crecimiento es demasiado corto.");
                return;
            }

            double tasaCrecimiento = Math.log((double)poblacionFinal.getNumBacterias() / poblacionInicial.getNumBacterias()) / daysBetween;
            textAreaCentral.setText("Tasa de crecimiento: " + tasaCrecimiento + " por día");
        } catch (Exception e) {
            textAreaCentral.setText("Error al calcular la tasa de crecimiento: " + e.getMessage());
        }
    }


    private void calcularEstadisticasGenerales() {
        if (experimentoActual == null || experimentoActual.getPoblaciones().isEmpty()) {
            textAreaCentral.setText("No hay datos suficientes para calcular estadísticas.");
            return;
        }

        ArrayList<Integer> numBacterias = new ArrayList<>();
        for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
            numBacterias.add(poblacion.getNumBacterias());
        }

        double media = numBacterias.stream().mapToInt(val -> val).average().orElse(0.0);
        double sd = Math.sqrt(numBacterias.stream().mapToDouble(num -> Math.pow(num - media, 2)).average().orElse(0.0));

        textAreaCentral.setText("Media de bacterias: " + media + "\nDesviación estándar: " + sd);
    }

    private XYSeriesCollection crearDataset() {
        if (poblaciones == null || poblaciones.isEmpty()) {
            return new XYSeriesCollection(); // Devolver una colección vacía si no hay datos
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Tasa de Crecimiento");
        for (PoblacionBacterias poblacion : poblaciones) {
            series.add(poblacion.getDiaIncrementoComida(), poblacion.getComidaDiaIncremento());
        }
        dataset.addSeries(series);
        return dataset;
    }



    private void cambiarIdioma() {
        String idiomaSeleccionado = (String) languageComboBox.getSelectedItem();
        if (idiomaSeleccionado.equals("Español")) {
            cambiarIdioma("es");
        } else if (idiomaSeleccionado.equals("English")) {
            cambiarIdioma("en");
        }
    }

    private void cambiarIdioma(String idioma) {
        // Lógica para cambiar el idioma de la interfaz

    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            // Lógica para leer el archivo y cargar los datos
        }
    }

    private void crearExperimento() {
        // Crear un diálogo para que el usuario ingrese el nombre del experimento
        String nombreExperimento = JOptionPane.showInputDialog(this, "Ingrese el nombre del experimento:");

        // Validar que el nombre no sea nulo o vacío
        if (nombreExperimento != null && !nombreExperimento.trim().isEmpty()) {
            // Inicializar el experimento actual con el nombre proporcionado
            experimentoActual = new Experimento(nombreExperimento);

            // Configurar detalles adicionales del experimento si es necesario
            // Por ejemplo, definir una fecha de inicio, notas asociadas, etc.
            String detallesExperimento = "Experimento creado el: " + new Date().toString();
            experimentoActual.equals (detallesExperimento);

            // Agregar el experimento a alguna estructura de datos si estás manejando múltiples experimentos
            // experimentosList.add(experimentoActual);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Nuevo experimento creado con éxito: " + nombreExperimento, "Experimento Creado", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la interfaz si es necesario, por ejemplo, actualizar listados o habilitar botones
            actualizarInterfazPostCreacion();
        } else {
            // Informar al usuario que el nombre del experimento es necesario
            JOptionPane.showMessageDialog(this, "El nombre del experimento no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarInterfazPostCreacion() {
        // Habilitar botones o realizar acciones específicas una vez creado el experimento
        btnCrearPoblacion.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnGuardarComo.setEnabled(true);
        // Otras actualizaciones según la lógica de la aplicación
    }


    private void crearPoblacion() {
        if (experimentoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero debe crear un experimento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtNombre = new JTextField(10);
        JTextField txtFechaInicio = new JTextField(10);
        JTextField txtFechaFin = new JTextField(10);
        JTextField txtNumBacterias = new JTextField(10);
        JTextField txtTemperatura = new JTextField(10);
        String[] luminosidadOptions = {"Alta", "Media", "Baja"};
        JComboBox<String> cmbLuminosidad = new JComboBox<>(luminosidadOptions);
        JTextField txtDosisComidaInicial = new JTextField(10);
        JTextField txtDiaIncrementoComida = new JTextField(10);
        JTextField txtComidaDiaIncremento = new JTextField(10);
        JTextField txtComidaDiaFinal = new JTextField(10);

        panel.add(new JLabel("Nombre:"));
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

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Población de Bacterias", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
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

                PoblacionBacterias nuevaPoblacion = new PoblacionBacterias(nombre, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad, dosisComidaInicial, diaIncrementoComida, comidaDiaIncremento, comidaDiaFinal);
                experimentoActual.agregarPoblacion(nuevaPoblacion);
                JOptionPane.showMessageDialog(this, "Población creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error en los datos numéricos, por favor revisa la información ingresada.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void visualizarPoblaciones() {
        // Verificar si hay un experimento activo y si contiene poblaciones
        if (experimentoActual == null || experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay poblaciones para mostrar. Por favor, crea un experimento y añade poblaciones primero.", "Sin Poblaciones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Poblaciones en el experimento '").append(experimentoActual.getNombreArchivo()).append("':\n\n");

        // Listar cada población con su información básica
        for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
            sb.append("Nombre: ").append(poblacion.getNombre()).append("\n");
            sb.append("Fecha de inicio: ").append(poblacion.getFechaInicio()).append("\n");
            sb.append("Fecha de fin: ").append(poblacion.getFechaFin()).append("\n");
            sb.append("Número de bacterias iniciales: ").append(poblacion.getNumBacterias()).append("\n");
            sb.append("Temperatura: ").append(poblacion.getTemperatura()).append("°C\n");
            sb.append("Luminosidad: ").append(poblacion.getLuminosidad()).append("\n");
            sb.append("Dosis de comida inicial: ").append(poblacion.getDosisComida()).append("\n");
            sb.append("\n"); // Espacio entre entradas
        }

        // Mostrar un diálogo con la información de todas las poblaciones
        JOptionPane.showMessageDialog(this, sb.toString(), "Vista de Poblaciones", JOptionPane.INFORMATION_MESSAGE);
    }


    private void borrarPoblacion() {
        // Verificar si hay un experimento y si tiene poblaciones
        if (experimentoActual == null || experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay poblaciones para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un array con los nombres de las poblaciones para mostrar en un diálogo de selección
        ArrayList<PoblacionBacterias> poblaciones = experimentoActual.getPoblaciones();
        String[] nombresPoblaciones = new String[poblaciones.size()];
        for (int i = 0; i < poblaciones.size(); i++) {
            nombresPoblaciones[i] = poblaciones.get(i).getNombre();
        }

        // Mostrar un diálogo para que el usuario seleccione la población a borrar
        String poblacionSeleccionada = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona una población para borrar:",
                "Borrar Población",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresPoblaciones,
                nombresPoblaciones[0]);

        // Procesar la eliminación si se seleccionó una población
        if (poblacionSeleccionada != null) {
            boolean removed = false;
            for (int i = 0; i < poblaciones.size(); i++) {
                if (poblaciones.get(i).getNombre().equals(poblacionSeleccionada)) {
                    poblaciones.remove(i);
                    removed = true;
                    break;
                }
            }

            // Mostrar un mensaje dependiendo del resultado de la operación
            if (removed) {
                JOptionPane.showMessageDialog(this, "Se ha borrado la población seleccionada.", "Población Borrada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la población seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // El usuario canceló la operación
            JOptionPane.showMessageDialog(this, "Borrado cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void verInformacionDetallada() {
        // Verificar si hay un experimento y si tiene poblaciones
        if (experimentoActual == null || experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay poblaciones para mostrar información.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un array con los nombres de las poblaciones para mostrar en un diálogo de selección
        ArrayList<PoblacionBacterias> poblaciones = experimentoActual.getPoblaciones();
        String[] nombresPoblaciones = new String[poblaciones.size()];
        for (int i = 0; i < poblaciones.size(); i++) {
            nombresPoblaciones[i] = poblaciones.get(i).getNombre();
        }

        // Mostrar un diálogo para que el usuario seleccione la población de la cual desea ver la información
        String poblacionSeleccionada = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona una población para ver información detallada:",
                "Información Detallada",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresPoblaciones,
                nombresPoblaciones[0]);

        // Procesar la visualización de la información si se seleccionó una población
        if (poblacionSeleccionada != null) {
            PoblacionBacterias poblacion = poblaciones.stream()
                    .filter(p -> p.getNombre().equals(poblacionSeleccionada))
                    .findFirst()
                    .orElse(null);

            if (poblacion != null) {
                String info = String.format(
                        "Nombre: %s\nFecha de inicio: %s\nFecha de fin: %s\nNúmero de bacterias iniciales: %d\n" +
                                "Temperatura: %d°C\nLuminosidad: %s\nDosis de comida inicial: %d\nDía de incremento de comida: %d\n" +
                                "Comida en el día de incremento: %d\nComida en el último día: %d",
                        poblacion.getNombre(),
                        poblacion.getFechaInicio(),
                        poblacion.getFechaFin(),
                        poblacion.getNumBacterias(),
                        poblacion.getTemperatura(),
                        poblacion.getLuminosidad(),
                        poblacion.getDosisComidaInicial(),
                        poblacion.getDiaIncrementoComida(),
                        poblacion.getComidaDiaIncremento(),
                        poblacion.getComidaDiaFinal());

                JOptionPane.showMessageDialog(this, info, "Información de Población", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la población seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // El usuario canceló la operación
            JOptionPane.showMessageDialog(this, "Visualización cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void guardarExperimento() {
        if (experimentoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay experimento para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El experimento no contiene poblaciones para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Experimento Como...");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        // Si el experimento ya tiene un archivo asociado, sugerimos el mismo nombre de archivo
        if (experimentoActual.getNombreArchivo() != null) {
            fileChooser.setSelectedFile(new File(String.valueOf(experimentoActual.getNombreArchivo())));
        }

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Asegurarse de que el archivo tenga la extensión correcta
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave + ".txt");
            }

            try (FileWriter fileWriter = new FileWriter(fileToSave)) {
                fileWriter.write("Experimento: " + experimentoActual.getNombreArchivo() + "\n");
                for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
                    fileWriter.write("Población: " + poblacion.getNombre() + "\n");
                    fileWriter.write("Inicio: " + poblacion.getFechaInicio() + ", Fin: " + poblacion.getFechaFin() + "\n");
                    fileWriter.write("Bacterias Iniciales: " + poblacion.getNumBacterias() + ", Temperatura: " + poblacion.getTemperatura() + "\n");
                    fileWriter.write("Luminosidad: " + poblacion.getLuminosidad() + ", Comida Inicial: " + poblacion.getDosisComidaInicial() + "\n\n");
                }
                experimentoActual.setNombreArchivo(fileToSave.getAbsolutePath());  // Actualizar el nombre de archivo en el objeto experimento
                JOptionPane.showMessageDialog(this, "Experimento guardado en " + fileToSave.getAbsolutePath(), "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error de Escritura", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Guardado cancelado por el usuario.", "Guardado Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void guardarExperimentoComo() {
        if (experimentoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay experimento para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (experimentoActual.getPoblaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El experimento no contiene poblaciones para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Como...");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Asegurarse de que el archivo tenga la extensión correcta
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave + ".txt");
            }

            try (FileWriter fileWriter = new FileWriter(fileToSave)) {
                fileWriter.write("Nombre del Experimento: " + experimentoActual.getNombreArchivo() + "\n");
                for (PoblacionBacterias poblacion : experimentoActual.getPoblaciones()) {
                    fileWriter.write("Población: " + poblacion.getNombre() + "\n");
                    fileWriter.write("Fecha de inicio: " + poblacion.getFechaInicio() + ", Fecha de fin: " + poblacion.getFechaFin() + "\n");
                    fileWriter.write("Número de bacterias iniciales: " + poblacion.getNumBacterias() + ", Temperatura: " + poblacion.getTemperatura() + "°C\n");
                    fileWriter.write("Luminosidad: " + poblacion.getLuminosidad() + ", Dosis de comida inicial: " + poblacion.getDosisComidaInicial() + "\n\n");
                }
                experimentoActual.setNombreArchivo(fileToSave.getAbsolutePath());  // Actualizar el nombre de archivo en el objeto experimento
                JOptionPane.showMessageDialog(this, "Experimento guardado como '" + fileToSave.getAbsolutePath() + "'.", "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error de Escritura", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Guardado cancelado por el usuario.", "Guardado Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private static void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Nimbus L&F not available");
            // Si Nimbus no está disponible, puedes establecer otro o dejar el predeterminado
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setLookAndFeel();
            new GestorExperimentos();
        });
    }
}
