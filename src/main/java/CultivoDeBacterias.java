import java.time.LocalDate;

public class CultivoDeBacterias {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int contadorInicialDeBacterias;
    private int temperatura;
    private String condicionDeLuz;
    private HorarioAlimentacion horarioAlimentacion;

    public CultivoDeBacterias(String nombre, LocalDate fechaInicio, LocalDate fechaFin, int contadorInicialDeBacterias,
            int temperatura, String condicionDeLuz, HorarioAlimentacion horarioAlimentacion) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.contadorInicialDeBacterias = contadorInicialDeBacterias;
        this.temperatura = temperatura;
        this.condicionDeLuz = condicionDeLuz;
        this.horarioAlimentacion = horarioAlimentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getContadorInicialDeBacterias() {
        return contadorInicialDeBacterias;
    }

    public void setContadorInicialDeBacterias(int contadorInicialDeBacterias) {
        this.contadorInicialDeBacterias = contadorInicialDeBacterias;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }



    public String getCondicionDeLuz() {
        return condicionDeLuz;
    }

    public void setCondicionDeLuz(String condicionDeLuz) {
        this.condicionDeLuz = condicionDeLuz;
    }

    public HorarioAlimentacion getHorarioAlimentacion() {
        return horarioAlimentacion;
    }

    public void setHorarioAlimentacion(HorarioAlimentacion horarioAlimentacion) {
        this.horarioAlimentacion = horarioAlimentacion;
    }

    @Override
    public String toString() {
        return "CultivoDeBacterias [condicionDeLuz=" + condicionDeLuz + ", contadorInicialDeBacterias="
                + contadorInicialDeBacterias + ", fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio
                + ", horarioAlimentacion=" + horarioAlimentacion + ", nombre=" + nombre + ", temperatura=" + temperatura
                + "]";
    }

    public static void main(String[] args) {
        HorarioAlimentacion horarioAlimentacion = new HorarioAlimentacion("08:00", "12:00", "16:00");
        CultivoDeBacterias cultivoDeBacterias = new CultivoDeBacterias("Cultivo 1", LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 31), 100, 25, "Luz artificial", horarioAlimentacion);
        System.out.println(cultivoDeBacterias);
    }


    public static class HorarioAlimentacion {
        private String desayuno;
        private String almuerzo;
        private String cena;

        public HorarioAlimentacion(String desayuno, String almuerzo, String cena) {
            this.desayuno = desayuno;
            this.almuerzo = almuerzo;
            this.cena = cena;
        }

        public String getDesayuno() {
            return desayuno;
        }

        public void setDesayuno(String desayuno) {
            this.desayuno = desayuno;
        }

        public String getAlmuerzo() {
            return almuerzo;
        }

        public void setAlmuerzo(String almuerzo) {
            this.almuerzo = almuerzo;
        }

        public String getCena() {
            return cena;
        }

        public void setCena(String cena) {
            this.cena = cena;
        }

        @Override
        public String toString() {
            return "HorarioAlimentacion [almuerzo=" + almuerzo + ", cena=" + cena + ", desayuno=" + desayuno + "]";
        }
    }

}