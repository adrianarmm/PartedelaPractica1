package EXPERIMENTO;
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

    @Override
    public String toString() {
        return "CultivoDeBacterias{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static void main(String[] args) {
        CultivoDeBacterias cultivoDeBacterias = new CultivoDeBacterias("Bacteria 1", 100);
        System.out.println(cultivoDeBacterias);
    }

    public String getCantidadInicial() {
        return null;
    }
}


