package EXPERIMENTO;

public class Cultivo {
    private String nombre;
    private int cantidad;
    private Bacterias bacterias;

    public Cultivo(String nombre, int cantidad, Bacterias bacterias) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.bacterias = bacterias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bacterias getBacterias() {
        return bacterias;
    }

    public void setBacterias(Bacterias bacterias) {
        this.bacterias = bacterias;
    }
}
