package EXPERIMENTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class PoblacionBacterias {
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private int numBacteriasIniciales;
    private String temperatura;

    private String luminosidad;
    private int[] dosisComida;

    public PoblacionBacterias(String nombre, String fechaInicio, String fechaFin, int numBacteriasIniciales, String temperatura, String luminosidad, int[] dosisComida) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacteriasIniciales = numBacteriasIniciales;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public int getNumBacteriasIniciales() {
        return numBacteriasIniciales;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getLuminosidad() {
        return luminosidad;
    }

    public int[] getDosisComida() {
        return dosisComida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setNumBacteriasIniciales(int numBacteriasIniciales) {
        this.numBacteriasIniciales = numBacteriasIniciales;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setLuminosidad(String luminosidad) {
        this.luminosidad = luminosidad;
    }

    public void setDosisComida(int[] dosisComida) {
        this.dosisComida = dosisComida;
    }

    public static void main(String[] args) {
        int[] dosisComida = {10, 20, 30, 40, 50};
        PoblacionBacterias poblacionBacterias = new PoblacionBacterias("Bacteria 1", "01/01/2021", "01/02/2021", 100, "25°C", "Alta", dosisComida);
        System.out.println(poblacionBacterias);
    }

    @Override

    public String toString() {
        return "PoblacionBacterias{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", numBacteriasIniciales=" + numBacteriasIniciales +
                ", temperatura='" + temperatura + '\'' +
                ", luminosidad='" + luminosidad + '\'' +
                ", dosisComida=" + Arrays.toString(dosisComida) +
                '}';
    }

}

