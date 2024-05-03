package EXPERIMENTO;

import java.util.ArrayList;

public class Bacterias {
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
