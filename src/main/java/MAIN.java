package EXPERIMENTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class CultivoDeBacterias {
    private String nombre;
    private int cantidad;

    public CultivoDeBacterias(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CultivoDeBacterias{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public static int crecimientoBacterias(int poblacionInicial, double tasaCrecimiento) {
        Random rand = new Random();
        int nuevaPoblacion = (int) (poblacionInicial * (1 + tasaCrecimiento));
        int variacion = rand.nextInt(11) - 5; // Variación aleatoria de +/- 5%
        nuevaPoblacion += (nuevaPoblacion * variacion) / 100;
        return nuevaPoblacion;
    }

    public static void imprimirDetalles(JFrame frame, int poblacion, double tasaCrecimiento) {
        int nuevaPoblacion = crecimientoBacterias(poblacion, tasaCrecimiento);
        int diferenciaCrecimiento = nuevaPoblacion - poblacion;
        double porcentajeCambio = ((double) diferenciaCrecimiento / poblacion) * 100;

        JOptionPane.showMessageDialog(frame,
                "<html>Información detallada de la población de bacterias:<br>" +
                        "Población inicial: " + poblacion + "<br>" +
                        "Tasa de crecimiento: " + (tasaCrecimiento * 100) + "%" + "<br>" +
                        "---- Simulación de crecimiento ----<br>" +
                        "Población después del crecimiento: " + nuevaPoblacion + "<br>" +
                        "Aumento de la población: " + diferenciaCrecimiento + "<br>" +
                        "Porcentaje de cambio: " + String.format("%.2f", porcentajeCambio) + "%" + "</html>");
    }