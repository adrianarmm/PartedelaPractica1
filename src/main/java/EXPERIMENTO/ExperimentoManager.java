package EXPERIMENTO;
import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class ExperimentoManager {

    public static experimentos abrirExperimento ( String fileName ) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream ( new FileInputStream ( fileName ) )) {
            return (experimentos) ois.readObject ();
        }
    }

    public experimentos crearNuevoExperimento () {
        return new experimentos ();
    }

    public void agregarCultivoDeBacterias ( experimentos experimento, CultivoDeBacterias bacteriaCulture ) {
        if (experimento == null || bacteriaCulture == null) {
            throw new IllegalArgumentException ( "El experimento o el cultivo de bacterias no pueden ser nulos." );
        }
        experimento.agregarCultivoDeBacterias ( bacteriaCulture );
    }

    public void verNombresDeCultivosDeBacterias ( experimentos experimento ) {
        if (experimento == null) {
            throw new IllegalArgumentException ( "El experimento no puede ser nulo." );
        }
        experimento.mostrarCultivosDeBacterias ();
    }

    public void eliminarCultivoDeBacterias ( experimentos experimento, String nombreCultivo ) {
        if (experimento == null || nombreCultivo == null || nombreCultivo.isEmpty ()) {
            throw new IllegalArgumentException ( "El experimento o el nombre del cultivo no pueden ser nulos o vacíos." );
        }
        // Buscar el cultivo de bacterias por su nombre
        Optional<CultivoDeBacterias> cultivoOptional = experimento.obtenerDetallesCultivo ( nombreCultivo );
        // Verificar si se encontró el cultivo
        if (cultivoOptional.isPresent ()) {
            // Si se encontró, eliminarlo del experimento
            experimento.eliminarCultivoDeBacterias ( String.valueOf ( cultivoOptional.get () ) );
            System.out.println ( "Cultivo de bacterias eliminado: " + nombreCultivo );
        } else {
            System.out.println ( "No se encontró el cultivo de bacterias: " + nombreCultivo );
        }
    }
    public void guardarExperimento ( experimentos experimento, String fileName ) throws IOException {
        if (experimento == null) {
            throw new IllegalArgumentException ( "No se puede guardar un experimento nulo." );
        }
        try (ObjectOutputStream oos = new ObjectOutputStream ( new FileOutputStream ( fileName ) )) {
            oos.writeObject ( experimento );
        }
    }

    public static void main ( String[] args ) {
        // Crear una instancia de ExperimentoManager
        ExperimentoManager experimentoManager = new ExperimentoManager ();

        // Crear un nuevo experimento
        experimentos experimento = experimentoManager.crearNuevoExperimento ();

        // Agregar cultivos de bacterias al experimento
        experimentoManager.agregarCultivoDeBacterias ( experimento, new CultivoDeBacterias ( "Bacteria 1", LocalDate.now (), LocalDate.now ().plusDays ( 10 ), 100, 25.0, "Alta", 50, 5, 10, 200 ) );
        experimentoManager.agregarCultivoDeBacterias ( experimento, new CultivoDeBacterias ( "Bacteria 2", LocalDate.now (), LocalDate.now ().plusDays ( 15 ), 150, 30.0, "Media", 60, 6, 12, 250 ) );
        experimentoManager.agregarCultivoDeBacterias ( experimento, new CultivoDeBacterias ( "Bacteria 3", LocalDate.now (), LocalDate.now ().plusDays ( 20 ), 200, 35.0, "Baja", 70, 7, 14, 300 ) );

        // Ver los nombres de los cultivos de bacterias
        experimentoManager.verNombresDeCultivosDeBacterias ( experimento );

        // Eliminar un cultivo de bacterias
        experimentoManager.eliminarCultivoDeBacterias ( experimento, "Bacteria 2" );

        // Ver los nombres de los cultivos de bacterias después de la eliminación
        experimentoManager.verNombresDeCultivosDeBacterias ( experimento );

        // Ver información detallada de un cultivo de bacterias
        experimentoManager.verInformacionDetalladaDeCultivoDeBacterias ( experimento, "Bacteria 1" );

        // Guardar el experimento
        try {
            experimentoManager.guardarExperimento ( experimento, "experimento.ser" );
            System.out.println ( "Experimento guardado con éxito." );
        } catch (IOException e) {
            System.out.println ( "Error al guardar el experimento: " + e.getMessage () );
        }
    }

    public void verInformacionDetalladaDeCultivoDeBacterias ( experimentos experimento, String nombreCultivo ) {
        if (experimento == null || nombreCultivo == null || nombreCultivo.isEmpty ()) {
            throw new IllegalArgumentException ( "El experimento o el nombre del cultivo no pueden ser nulos o vacíos." );
        }
        StringWriter detallesArea = new StringWriter ();
        verInformacionDetalladaDeCultivoDeBacterias ( experimento, nombreCultivo, detallesArea );
        System.out.println ( detallesArea.toString () );
    }

    private void verInformacionDetalladaDeCultivoDeBacterias(experimentos experimento, String nombreCultivo, StringWriter detallesArea) {
        // Obtener el cultivo de bacterias del experimento utilizando el nombre proporcionado
        Optional<CultivoDeBacterias> cultivoOptional = experimento.obtenerDetallesCultivo(nombreCultivo);

        // Verificar si se encontró el cultivo de bacterias
        if (cultivoOptional.isPresent()) {
            // Obtener el cultivo de bacterias
            CultivoDeBacterias cultivo = cultivoOptional.get();

            // Mostrar la información detallada del cultivo de bacterias
            detallesArea.append("Nombre: " + cultivo.getNombre() + "\n");
            detallesArea.append("Fechas: " + cultivo.getFechaInicio() + " - " + cultivo.getFechaFin() + "\n");
            detallesArea.append("Cantidad inicial: " + cultivo.getCantidad() + "\n");
            detallesArea.append("Temperatura: " + cultivo.getTemperatura() + "\n");
            detallesArea.append("Luminosidad: " + cultivo.getLuminosidad() + "\n");
            detallesArea.append("Dosis de comida:\n");
            detallesArea.append(" - Inicial: " + cultivo.getDosisComida().getComidaInicial() + "\n");
            detallesArea.append(" - Incremento en día " + cultivo.getDosisComida().getDiaIncremento() + ": " +
                    cultivo.getDosisComida().getComidaIncremento() + "\n");
            detallesArea.append(" - Final: " + cultivo.getDosisComida().getComidaFinal() + "\n");
        } else {
            // Mostrar un mensaje indicando que no se encontró el cultivo de bacterias
            detallesArea.append("No se encontró el cultivo de bacterias: " + nombreCultivo + "\n");
        }
    }
}