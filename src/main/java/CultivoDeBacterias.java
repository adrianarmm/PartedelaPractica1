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
}
