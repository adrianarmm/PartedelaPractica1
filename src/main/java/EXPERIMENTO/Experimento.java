package EXPERIMENTO;

import java.io.File;
import java.util.ArrayList;

public class Experimento {
    private String nombreArchivo;
    private ArrayList<PoblacionBacterias> poblaciones;

    public Experimento(String absolutePath) {
        this.nombreArchivo = absolutePath;
        this.poblaciones = new ArrayList<>();
    }

    public Experimento() {
        this.poblaciones = new ArrayList<>();
    }

    public ArrayList<PoblacionBacterias> getPoblaciones() {
        return poblaciones;
    }

    public void agregarPoblacion(PoblacionBacterias poblacion) {
        poblaciones.add(poblacion);
    }

    public void setNombreArchivo(String absolutePath) {
        this.nombreArchivo = absolutePath;
    }

    public File getNombreArchivo() {
        if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
            return new File(nombreArchivo);
        } else {
            return null;
        }
    }
}

