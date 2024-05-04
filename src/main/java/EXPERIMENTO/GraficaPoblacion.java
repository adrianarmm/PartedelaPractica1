package EXPERIMENTO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class GraficaPoblacion extends JFrame {
    private PoblacionBacterias poblacion;

    public GraficaPoblacion(PoblacionBacterias poblacion) {
        this.poblacion = poblacion;
        setTitle("Gráfica de Población de Bacterias");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear y personalizar la serie de datos
        XYSeries seriePoblacion = new XYSeries("Población de Bacterias");
        for (int i = 0; i < poblacion.getDatosPoblacion().size(); i++) {
            seriePoblacion.add( (double) i, (Number) poblacion.getDatosPoblacion().get(i) );
        }

        // Agregar la serie de datos al conjunto de datos
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriePoblacion);

        // Crear la gráfica
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Población de Bacterias",
                "Días",
                "Número de Bacterias",
                dataset
        );

        // Personalizar la apariencia de la gráfica
        chart.setBackgroundPaint(Color.white);

        // Mostrar la gráfica en un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
        setVisible(true);
    }
}
