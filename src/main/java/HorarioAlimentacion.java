public class HorarioAlimentacion {
    private int cantidadInicialDeAlimento;
    private int diaHastaElCualAumentaLaComida;
    private int cantidadDeComidaEnElDiaFinal;

    public HorarioAlimentacion(int cantidadInicialDeAlimento, int diaHastaElCualAumentaLaComida, int cantidadDeComidaEnElDiaFinal) {
        this.cantidadInicialDeAlimento = cantidadInicialDeAlimento;
        this.diaHastaElCualAumentaLaComida = diaHastaElCualAumentaLaComida;
        this.cantidadDeComidaEnElDiaFinal = cantidadDeComidaEnElDiaFinal;
    }

    public int getCantidadInicialDeAlimento() {
        return cantidadInicialDeAlimento;
    }

    public void setCantidadInicialDeAlimento(int cantidadInicialDeAlimento) {
        this.cantidadInicialDeAlimento = cantidadInicialDeAlimento;
    }

    public int getDiaHastaElCualAumentaLaComida() {
        return diaHastaElCualAumentaLaComida;
    }

    public void setDiaHastaElCualAumentaLaComida(int diaHastaElCualAumentaLaComida) {
        this.diaHastaElCualAumentaLaComida = diaHastaElCualAumentaLaComida;
    }

    public int getCantidadDeComidaEnElDiaFinal() {
        return cantidadDeComidaEnElDiaFinal;
    }

    public void setCantidadDeComidaEnElDiaFinal(int cantidadDeComidaEnElDiaFinal) {
        this.cantidadDeComidaEnElDiaFinal = cantidadDeComidaEnElDiaFinal;
    }

    public int cantidadDeComida(int dia) {
        if (dia <= diaHastaElCualAumentaLaComida) {
            return cantidadInicialDeAlimento;
        } else {
            return cantidadDeComidaEnElDiaFinal;
        }
    }


    public static void main(String[] args) {
        HorarioAlimentacion horarioAlimentacion = new HorarioAlimentacion(100, 3, 200);
        System.out.println("Cantidad de comida en el día 1: " + horarioAlimentacion.cantidadDeComida(1));
        System.out.println("Cantidad de comida en el día 2: " + horarioAlimentacion.cantidadDeComida(2));
        System.out.println("Cantidad de comida en el día 3: " + horarioAlimentacion.cantidadDeComida(3));
        System.out.println("Cantidad de comida en el día 4: " + horarioAlimentacion.cantidadDeComida(4));
    }

}