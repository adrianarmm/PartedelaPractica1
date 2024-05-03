package EXPERIMENTO;

import java.util.ArrayList;
import java.util.List;

public class CultivoDeBacterias {
    private String nombre;
    private int cantidad;

    public CultivoDeBacterias(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}

