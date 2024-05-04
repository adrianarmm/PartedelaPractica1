package EXPERIMENTO;

import java.util.Map;

public class PoblacionBacterias {
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private int numBacterias;
    private int temperatura;
    private String luminosidad;
    private int dosisComidaInicial;
    private int diaIncrementoComida;
    private int comidaDiaIncremento;
    private int comidaDiaFinal;

    public PoblacionBacterias(String nombre, String fechaInicio, String fechaFin, int numBacterias, int temperatura, String luminosidad, int dosisComidaInicial, int diaIncrementoComida, int comidaDiaIncremento, int comidaDiaFinal) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacterias = numBacterias;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComidaInicial = dosisComidaInicial;
        this.diaIncrementoComida = diaIncrementoComida;
        this.comidaDiaIncremento = comidaDiaIncremento;
        this.comidaDiaFinal = comidaDiaFinal;
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

    public int getNumBacterias() {
        return numBacterias;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public String getLuminosidad() {
        return luminosidad;
    }

    public int getDosisComidaInicial() {
        return dosisComidaInicial;
    }

    public int getDiaIncrementoComida() {
        return diaIncrementoComida;
    }

    public int getComidaDiaIncremento() {
        return comidaDiaIncremento;
    }

    public int getComidaDiaFinal() {
        return comidaDiaFinal;
    }

    public String getDosisComida () {
        return "Dosis de comida: " + dosisComidaInicial + " - " + diaIncrementoComida + " - " + comidaDiaIncremento + " - " + comidaDiaFinal;
    }

    public Map<Object, Object> getDatosPoblacion () {
        return Map.of(
                "Nombre", nombre,
                "Fecha de inicio", fechaInicio,
                "Fecha de fin", fechaFin,
                "Número de bacterias", numBacterias,
                "Temperatura", temperatura,
                "Luminosidad", luminosidad,
                "Dosis de comida", getDosisComida()
        );
    }
}

