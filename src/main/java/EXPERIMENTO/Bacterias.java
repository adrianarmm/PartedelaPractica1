package EXPERIMENTO;

import java.io.Serializable;
import java.util.List;

public class Bacterias implements Serializable {
    private String colonia;
    private String genotipo;
    private List<String> plasmidos;

    public Bacterias(String colonia, String genotipo, List<String> plasmidos) {
        this.colonia = colonia;
        this.genotipo = genotipo;
        this.plasmidos = plasmidos;
    }

    // Getters y setters
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

    public List<String> getPlasmidos() {
        return plasmidos;
    }

    public void setPlasmidos(List<String> plasmidos) {
        this.plasmidos = plasmidos;
    }
}