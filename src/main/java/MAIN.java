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